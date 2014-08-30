package fr.minewild.launcher.tasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import fr.minewild.launcher.Main;
import fr.minewild.launcher.data.Constants;
import fr.minewild.launcher.data.Profile;
import fr.minewild.launcher.frames.MainFrame;
import fr.minewild.launcher.utils.LastLoginSaveManager;
import fr.minewild.launcher.utils.LogUtils;
import fr.minewild.launcher.utils.ConnectionUtils;
import fr.minewild.launcher.utils.SystemManager.OS;
import fr.minewild.launcher.utils.Utils;

public class PlayTask extends Thread
{
	private MainFrame	mainFrame;
	private Profile		profile;
	
	public PlayTask(Profile profile, MainFrame mainFrame)
	{
		this.profile = profile;
		this.mainFrame = mainFrame;
	}
	
	public void run()
	{
		mainFrame.lock(true);
		final String osMinecraftName = profile.getOs().getMinecraftName();
		LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "Debug infos :");
		LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "OS : " + profile.getOs().getName());
		LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "Architecture : " + profile.getArch());
		LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "Java version : " + System.getProperty("java.version"));
		//
		final File gameDirectory = Main.system.getMinewildDirectory();
		final File assetsDirectory = new File(gameDirectory.getPath() + Constants.ASSETS_SUFFIX);
		final File versionDirectory = new File(gameDirectory.getPath() + Constants.VERSIONS_SUFFIX);
		final File librariesDirectory = new File(gameDirectory.getPath() + Constants.LIBS_SUFFIX);
		//
		//check pswd
		if(profile.getPassword() != null)//if premium
		{
			mainFrame.btnPlaySetText("Vérification du mot de passe ...");
		}
		//check whitelist
		if(!profile.isWhiteListed() && ConnectionUtils.getServerStatus())
			mainFrame.dispFiveSecMessageOnPlayButton("Vous n'êtes pas whitelisté sur Minewild");
		LastLoginSaveManager.saveContents(profile.getUsername(), profile.getPassword()!=null);
		mainFrame.btnPlaySetText("Vérification des fichiers de jeu ...");
		//TODO: il faudrait s'assurer que tous les fichiers de jeux soient téléchargées pour pouvoir passer à la suite
		//boucle qui dl les games files
		//verifier que la version requise existe (dossier + fichiers)
		mainFrame.btnPlaySetText("Lancement du jeu" + (profile.isWhiteListed() ? "" : " en solo") + " ...");
		final Gson gson = new Gson();
		MinecraftVersionJsonObject versionObj = null;
		try
		{
			versionObj = gson.fromJson(Utils.getFileContent(new File(versionDirectory.getPath() + File.separator + profile.getVersion() + ".json"), null), MinecraftVersionJsonObject.class);
		}
		catch(JsonSyntaxException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> libraries;
		
		final List<String> args = ArgumentsManager.getMinecraftArgs(profile, versionObj.assets, gameDirectory, assetsDirectory);
		LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "Checking libraries...");
		final List<String> librariesPaths = new ArrayList<String>();
		for(final Library library : versionObj.libraries) {
			if(library.isDisallowed(profile.getOs())) {
				break;
			}
			final String[] libData = library.name.split(":");
			final String libName = libData[1];
			final String libVersion = libData[2];
			String libFileName = libName + "-" + libVersion;
			boolean hasNative = false;
			if(library.natives != null) {
				final String nativeOs = library.natives.get(osMinecraftName);
				if(nativeOs != null) {
					libFileName += "-" + library.natives.get(osMinecraftName).replace("${arch}", profile.getArch());
					hasNative = true;
				}
			}
			libFileName += ".jar";
			final String libPath = "/" + libData[0].replace(".", "/") + "/" + libName + "/" + libVersion + "/" + libFileName;
			final File libFile = new File(librariesDirectory, libPath.replace("/", File.separator));
			final String libUrl = Constants.LIBS_URL + libPath;
			if(!libFile.exists() && !(library.natives != null && !hasNative) && !fixFile(libFile, libUrl, FileType.LIBRARY, FixMode.MISSING)) {
				return;
			}
			final File sha1File = new File(libFile + ".sha");
			if(!sha1File.exists() && !fixFile(sha1File, libUrl + ".sha1", FileType.HASH, FixMode.MISSING)) {
				LogUtils.log(Level.WARNING, Constants.PLAY_TASK_PREFIX + "Cannot download the SHA1 file, it has been ignored.");
			}
			if(sha1File.exists() && !Utils.getFileContent(sha1File, null).equals(Utils.getFileChecksum(libFile, sha1)) && !fixFile(libFile, libUrl, FileType.LIBRARY, FixMode.INVALID)) {
				return;
			}
			if(hasNative) {
				LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "Unzipping native...");
				if(!Utils.unzipJar(nativesDir, libFile, library.extract.exclude)) {
					LogUtils.log(Level.SEVERE, Constants.PLAY_TASK_PREFIX + "Unable to unzip the native " + libFile.getName() + ". Please unzip it manually and place it into " + nativesDir.getName() + ".");
					return;
				}
			}
			else {
				librariesPaths.add(libFile.getAbsolutePath());
			}
		}
		LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "Done.");
		final List<String> command = new ArrayList<String>();
		LogUtils.log(Level.INFO, "Executing command : " + StringUtils.join(command, ' '));
		final Process process = new ProcessBuilder(command.toArray(new String[command.size()])).directory(gameDirectory).start();
		LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "Done.");
		mainFrame.setVisible(false);//TODO waring
	}
	
	@SuppressWarnings("unused")
	private class MinecraftVersionJsonObject
	{
		private String			id;
		private String			time;
		private String			releaseTime;
		private String			type;
		private String			minecraftArguments;
		private int				minimumLauncherVersion;
		private String			assets;
		private List<Library>	libraries;
		private String			mainClass;
	}
	
	private class Library
	{
		private String					name;
		private HashMap<String, String>	natives;
		private List<DLRule>			rules;
		private ExtractRules			extract;
		
		private final boolean isDisallowed(final OS os)
		{
			if(rules != null)
			{
				final String osName = os.getMinecraftName();
				for(final DLRule rule : rules)
				{
					if(rule.os != null) 
					{
						final String currentOs = rule.os.get("name");
						if(rule.action.equals("disallow") && currentOs.equals(osName))
						{
							return true;
						}
						else if(rule.action.equals("allow") && !currentOs.equals(osName))
						{ //TODO: Short this.
							return true;
						}
					}
				}
			}
			return false;
		}
		
		private class DLRule
		{
			
			private String					action;
			private HashMap<String, String>	os;
			
		}
		
		private class ExtractRules
		{
			
			private List<String>	exclude;
		}
	}
	
	private enum FileType {
		
		FILE("file"),
		LIBRARY("library"),
		ASSET("asset"),
		HASH("hash");

		private final String name;

		FileType(final String name) {
			this.name = name;
		}
		
	}

	private enum FixMode {
		
		MISSING("Missing"),
		INVALID("Invalid");

		private final String name;

		FixMode(final String name) {
			this.name = name;
		}
		
	}
}
