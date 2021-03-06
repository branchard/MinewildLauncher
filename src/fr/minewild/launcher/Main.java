package fr.minewild.launcher;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.pagosoft.plaf.PgsLookAndFeel;

import fr.minewild.launcher.data.Constants;
import fr.minewild.launcher.frames.ConsoleFrame;
import fr.minewild.launcher.frames.LauncherTheme;
import fr.minewild.launcher.frames.MainFrame;
import fr.minewild.launcher.utils.LogUtils;
import fr.minewild.launcher.utils.SystemManager;
import fr.minewild.launcher.utils.Utils;

public class Main
{
	public static final SystemManager	system	= new SystemManager();
	public static ConsoleFrame			console;
	public static MainFrame				mainFrame;
	public static boolean				serverIsOnline;
	
	public static void main(String[] args)
	{
		try
		{
			final File mwDir = system.getMinewildDirectory();
			mwDir.mkdirs();
			final List<String> argsList = Arrays.asList(args);
			PgsLookAndFeel.setCurrentTheme(new LauncherTheme());
			UIManager.setLookAndFeel(new PgsLookAndFeel());
			Utils.setUIFont(new FontUIResource(Constants.LAUNCHER_FONT));
			if(argsList.contains("-console"))
			{
				console = new ConsoleFrame();
				console.setVisible(true);
			}
			LogUtils.log(Level.INFO, Constants.LAUNCHER_PREFIX + Utils.buildTitle());
			LogUtils.log(null, null);//blank line
			mainFrame = new MainFrame();
			mainFrame.setVisible(true);
		}
		catch(Exception e)
		{
			LogUtils.log(e.getStackTrace());
			System.exit(1);
		}
	}
}

// TODO LIST
// Adopter le pattern MVC
// Forcer les gens à utiliser ce launcher