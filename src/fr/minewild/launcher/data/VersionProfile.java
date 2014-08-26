package fr.minewild.launcher.data;

import java.io.File;
import java.util.List;

public class VersionProfile
{
	private String			id;
	private String			time;
	private String			releaseTime;
	private String			type;
	private String			minecraftArguments;
	private int				minimumLauncherVersion;
	private String			assetIndex;
	private List<String>	libraries;
	private String			mainClass;
	
	private File			gameDirectory;
	private File			assetsDirectory;
	private File			versionDirectory;
	private File 			librariesDirectory;
	
	public VersionProfile(String id, String time, String releaseTime, String type, String minecraftArguments, int minimumLauncherVersion, String assetIndex, List<String> libraries, String mainClass, File gameDirectory, String version)
	{
		this.id = id;
		this.time = time;
		this.releaseTime = releaseTime;
		this.type= type;
		this.minecraftArguments = minecraftArguments;
		this.minimumLauncherVersion = minimumLauncherVersion;
		this.assetIndex = assetIndex;
		this.libraries = libraries;
		this.mainClass = mainClass;
		this.gameDirectory = gameDirectory;
		assetsDirectory = new File(gameDirectory.getPath() + Constants.ASSETS_SUFFIX);
		versionDirectory = new File(gameDirectory.getPath() + File.separator + "versions" + File.separator + version);
		librariesDirectory = new File(gameDirectory.getPath() + File.separator + "libraries");
	}

	public String getId()
	{
		return id;
	}

	public String getTime()
	{
		return time;
	}

	public String getReleaseTime()
	{
		return releaseTime;
	}

	public String getType()
	{
		return type;
	}

	public String getMinecraftArguments()
	{
		return minecraftArguments;
	}

	public int getMinimumLauncherVersion()
	{
		return minimumLauncherVersion;
	}

	public String getAssetIndex()
	{
		return assetIndex;
	}

	public List<String> getLibraries()
	{
		return libraries;
	}

	public String getMainClass()
	{
		return mainClass;
	}

	public File getGameDirectory()
	{
		return gameDirectory;
	}

	public File getAssetsDirectory()
	{
		return assetsDirectory;
	}

	public File getVersionDirectory()
	{
		return versionDirectory;
	}

	public File getLibrariesDirectory()
	{
		return librariesDirectory;
	}
	
}
