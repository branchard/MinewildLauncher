package fr.minewild.launcher.data;

import fr.minewild.launcher.Main;
import fr.minewild.launcher.utils.SystemManager.OS;
import fr.minewild.launcher.utils.SystemManager.Platform;
import fr.minewild.launcher.utils.Utils;
import fr.minewild.launcher.utils.WhitelistManager;

public class Profile
{
	private String			username;
	private char[]			password;
	private String			uuid;
	private boolean			isWhiteListed;
	private String			version;
	private OS				os;
	private String			arch;
	
	//private int				minimumLauncherVersion;
	//private String			assetsIndex;
	//private List<Library>	libraries;
	
	public Profile(String username, char[] password)
	{
		this.username = username;
		this.password = password;
		Tuple<String, Boolean> pseudoRes = WhitelistManager.getUUIDfromServer(username);
		uuid = pseudoRes.getPrimary();
		isWhiteListed = pseudoRes.getSecond().booleanValue();
		
		version = Utils.getCurrentVersion();
		
		final Platform platform = Main.system.getPlatform();
		os = platform.getOS();
		arch = platform.getArch().getName().replace("x", "").replace("86", "32");
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

	public String getVersion()
	{
		return version;
	}

	public OS getOs()
	{
		return os;
	}

	public String getArch()
	{
		return arch;
	}
	
}
