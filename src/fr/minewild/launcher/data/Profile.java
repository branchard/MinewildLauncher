package fr.minewild.launcher.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import fr.minewild.launcher.Main;
import fr.minewild.launcher.utils.ConnectionUtils;
import fr.minewild.launcher.utils.Utils;
import fr.minewild.launcher.utils.SystemManager.OS;

public class Profile
{
	private String	username;
	private char[]	password;
	private String 	uuid;
	private boolean isWhiteListed;
	private File	gameDirectory;
	private File    assetsDirectory;
	private String	version;
	private File 	versionDirectory;
	
	private int minimumLauncherVersion;
	private String assetsIndex;
	
	public Profile(String username, char[] password)
	{
		this.username = username;
		this.password = password;
		Tuple<String, Boolean> pseudoRes = ConnectionUtils.getUUIDfromServer(username);
		uuid = pseudoRes.getPrimary();
		isWhiteListed = pseudoRes.getSecond().booleanValue();
		gameDirectory = Main.system.getMinewildDirectory();
		assetsDirectory = new File(gameDirectory.getPath() + Constants.ASSETS_SUFFIX);
		version = Utils.getCurrentVersion();
		versionDirectory = new File(gameDirectory.getPath() + File.separator + "versions" + File.separator + version);
		final Gson gson = new Gson();
		MinecraftVersionJsonObject versionObj = null;
		try
		{
			versionObj = gson.fromJson(Utils.getFileContent(new File(versionDirectory.getPath() + File.separator + version + ".json"), null), MinecraftVersionJsonObject.class);
		}
		catch(JsonSyntaxException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		minimumLauncherVersion = versionObj.minimumLauncherVersion;
		assetsIndex = versionObj.assets;
	}
	
	public String getAssetsIndex()
	{
		return assetsIndex;
	}

	public String getUsername()
	{
		return username;
	}
	
	public char[] getPassword()
	{
		return password;
	}
	
	public String getUUID()
	{
		return uuid;
	}
	
	public boolean isWhiteListed()
	{
		return isWhiteListed;
	}
	
	public File getGameDirectory()
	{
		return gameDirectory;
	}
	
	public File getAssetsDirectory()
	{
		return assetsDirectory;
	}
	
	public String getVersion()
	{
		return version;
	}
	
	public int getMinimumLauncherVersion()
	{
		return minimumLauncherVersion;
	}

	private class MinecraftVersionJsonObject
	{
		private String id;
		private String time;
		private String releaseTime;
		private String type;
		private String minecraftArguments;
		private int minimumLauncherVersion;
		private String assets;
		private List<Library> libraries;
		private String mainClass;
	}
	
	private class Library
	{
		public String					name;
		public HashMap<String, String>	natives;
		public List<DLRule>				rules;
		public ExtractRules				extract;
		
		public final boolean isDisallowed(final OS os)
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
		
		private class DLRule {

			public String action;
			public HashMap<String, String> os;

		}

		private class ExtractRules {

			public List<String> exclude;
		}
	}
}
