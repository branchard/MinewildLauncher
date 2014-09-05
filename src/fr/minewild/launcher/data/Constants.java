package fr.minewild.launcher.data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import fr.minewild.launcher.Main;

public class Constants
{
	////////////////////////////////
	//                            //
	//          LAUNCHER          //
	//                            //
	////////////////////////////////
	public static final String		LAUNCHER_PREFIX					= "[LAUNCHER] ";
	public static final String		LAUNCHER_NAME					= "MinewildLauncher", LAUNCHER_VERSION = "0.0.1", LAUNCHER_STATUS = "ALPHA";
	public static final String[]	LAUNCHER_AUTHORS				= new String[]{"Skymix"};
	public static final Image		LAUNCHER_ICON					= loadImageFromRes("/fr/minewild/launcher/res/icon.png"), CONSOLE_ICON = loadImageFromRes("/fr/minewild/launcher/res/console.png"),
									LAUNCHER_IMAGE 					= loadImageFromRes("/fr/minewild/launcher/res/minewild_logo.png");
	public static final Image		LAUNCHER_BACKGROUND				= loadImageFromRes("/fr/minewild/launcher/res/frame_bg.png");
	public static final Color		LAUNCHER_BACKGROUND_TRANSPARENT	= new Color(0, 0, 0, 0);
	public static final Font		LAUNCHER_FONT					= loadFontFromRes(Font.TRUETYPE_FONT, "/fr/minewild/launcher/res/Minecraftia.ttf", 12f);
	public static final Color       LAUCNHER_FONT_COLOR             = new Color(134, 134, 134);
	public static final String		LAUNCHER_PLAYBTN_ICON			= "/fr/minewild/launcher/res/playButton.png";
	public static final String		LAUNCHER_CLOSEBTN_ICON			= "/fr/minewild/launcher/res/closeButton.png";
	public static final String		MINEWILD_DIR_NAME				= "minewild";
	public static final String		LAUCNHER_SAVE_FILE_NAME			= "launcher-save";
	/* Client options. */

	public static final String 		CLIENT_TOKEN 					= "MinewildLauncher";
	public static final int 		CLIENT_VERSION 					= 14;
	
	////////////////////////////////
	//                            //
	//      SERVER CONNECTION     //
	//                            //
	////////////////////////////////
	public static final String		CONNECTION_PREFIX				= "[CONNECTION] ";
	public static final String		MINEWILD_SERVER_ADDRESS			= "371.59.252.148";
	public static final int			MINEWILD_SERVER_PORT			= 25565, MINEWILD_GET_UUID_PORT = 8072, SERVER_TIMEOUT = 6000;
	
	////////////////////////////////
	//                            //
	//           TASKS            //
	//                            //
	////////////////////////////////
	public static final String		PLAY_TASK_PREFIX				= "[PLAY TASK] ";
	public static final String		DEFAULT_MINECRAFT_VERSION		= "1.7.9";
	public static final String      MINECRAFT_ARGS_PREFIXE			= "--";
	public static final String 		LIBS_SUFFIX 					= "/libraries";
	public static final String 		NATIVES_SUFFIX 					= "/natives";
	public static final String 		LIBS_URL 						= "https:/" + LIBS_SUFFIX + ".minecraft.net";
	public static final String 		MINECRAFT_AWS_URL 				= "http://s3.amazonaws.com/Minecraft.Download";
	public static final String 		MINECRAFT_RES_URL 				= "http://resources.download.minecraft.net";
	public static final String 		VERSIONS_SUFFIX 				= "/versions";
	public static final String 		ASSETS_SUFFIX 					= "/assets";
	public static final String 		ASSETS_OBJECTS_SUFFIX 			= "/objects";
	public static final String 		ASSETS_INDEXES_SUFFIX 			= "/indexes";
	public static final String 		ASSETS_VIRTUAL_SUFFIX 			= "/virtual";
	public static final String 		ASSETS_LEGACY_SUFFIX 			= "/legacy";
	
	////////////////////////////////
	//                            //
	//           OTHERS           //
	//                            //
	////////////////////////////////
	private static final Font loadFontFromRes(final int format, final String path, final float size)
	{
		Font font = null;
		try
		{
			font = Font.createFont(format, Main.class.getResourceAsStream(path)).deriveFont(size);
		}
		catch(final Exception ex)
		{
			ex.printStackTrace();
		}
		return font;
	}
	
	private static final Image loadImageFromRes(final String path)
	{
		try
		{
			return Toolkit.getDefaultToolkit().getImage(Main.class.getResource(path));
		}
		catch(final Exception e)
		{
			
		}
		return null;
	}
}
