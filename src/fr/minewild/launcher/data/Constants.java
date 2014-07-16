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
	public static final String		LAUNCHER_NAME					= "MinewildLauncher", LAUNCHER_VERSION = "0.1", LAUNCHER_STATUS = "BETA";
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
	
	////////////////////////////////
	//                            //
	//      SERVER CONNECTION     //
	//                            //
	////////////////////////////////
	public static final String		CONNECTION_PREFIX				= "[CONNECTION] ";
	public static final String		MINEWILD_SERVER_ADDRESS			= "188.165.164.162";
	public static final int			MINEWILD_SERVER_PORT			= 25752, MINEWILD_GET_UUID_PORT = 8072, SERVER_TIMEOUT = 6000;
	
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
