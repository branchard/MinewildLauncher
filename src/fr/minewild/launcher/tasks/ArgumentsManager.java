package fr.minewild.launcher.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import fr.minewild.launcher.data.Constants;
import fr.minewild.launcher.data.Profile;
import fr.minewild.launcher.utils.LogUtils;
import fr.minewild.launcher.utils.ConnectionUtils;

public final class ArgumentsManager
{
	public static final List<String> getMinecraftArgs(final Profile profile, String assetsIndex)
	{
		//Ex: --username Skymix --version 1.7.9 --gameDir /home/benoit/.minecraft --assetsDir /home/benoit/.minecraft/assets --assetIndex 1.7.4 --uuid 9c24a91ae1fb3704aa45a8263f48f7ad --accessToken 0 --userProperties [] --userType mojang --server 188.165.164.162 --port 25752
		List<String> args = new ArrayList<String>();
		args.add("username " + profile.getUsername());
		args.add("version " + profile.getVersion());
		args.add("gameDir " + profile.getGameDirectory().getPath());
		args.add("assetsDir " + profile.getAssetsDirectory().getPath());
		args.add("assetIndex " + assetsIndex);
		args.add("uuid " + profile.getUUID());
		args.add("accessToken " + "0");//TODO
		args.add("userProperties " + "[]");
		args.add("userType " + "mojang");
		if(ConnectionUtils.getServerStatus() && profile.isWhiteListed())// if the server is online and player is whitelisted
		{
			args.add("server " + Constants.MINEWILD_SERVER_ADDRESS);
			args.add("port" + Constants.MINEWILD_SERVER_PORT);
		}
		for(int i = 0; i < args.size(); i++)
			args.set(i, Constants.MINECRAFT_ARGS_PREFIXE + args.get(i));
		LogUtils.log(Level.INFO, Constants.PLAY_TASK_PREFIX + "minecraft args: " + args.toString().replace(",", ""));
		return args;
	}
}
