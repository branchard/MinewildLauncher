package fr.minewild.launcher;

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
import fr.minewild.launcher.utils.ConnectionUtils;
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
			final List<String> argsList = Arrays.asList(args);
			PgsLookAndFeel.setCurrentTheme(new LauncherTheme());
			UIManager.setLookAndFeel(new PgsLookAndFeel());
			Utils.setUIFont(new FontUIResource(Constants.LAUNCHER_FONT));
			if(argsList.contains("-console"))// -> !
			{
				console = new ConsoleFrame();
				console.setVisible(true);
			}
			LogUtils.log(Level.INFO, Constants.LAUNCHER_PREFIX + Utils.buildTitle());
			LogUtils.log(null, null);
			mainFrame = new MainFrame();
			mainFrame.setVisible(true);
			
			serverIsOnline = ConnectionUtils.isOnline();
			mainFrame.setOnline(serverIsOnline);
			mainFrame.updatePlayButton();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}
