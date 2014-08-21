package fr.minewild.launcher.tasks;

import java.util.List;

import fr.minewild.launcher.data.Profile;
import fr.minewild.launcher.frames.MainFrame;
import fr.minewild.launcher.utils.LastLoginSaveManager;
import fr.minewild.launcher.utils.ServerConnection;

public class PlayTask extends Thread
{
	private MainFrame mainFrame;
	private Profile profile;
	
	public PlayTask(final MainFrame mainFrame, final Profile profile)
	{
		this.mainFrame = mainFrame;
		this.profile = profile;
	}
	
	public void run()
	{
		mainFrame.lock(true);
		//check pswd
		if(profile.getPassword() != null)//if premium
		{
			mainFrame.btnPlaySetText("Vérification du mot de passe ...");
		}
		//check whitelist
		if(!profile.isWhiteListed() && ServerConnection.getServerStatus())
			mainFrame.dispFiveSecMessageOnPlayButton("Vous n'êtes pas whitelisté sur Minewild");
		LastLoginSaveManager.saveContents(profile.getUsername(), profile.getPassword()!=null);
		mainFrame.btnPlaySetText("Vérification des fichiers de jeu ...");
		//TODO: il faudrait s'assurer que tous les fichiers de jeux soient téléchargées pour pouvoir passer à la suite
		//boucle qui dl les games files
		//verifier que la version requise existe (dossier + fichiers)
		mainFrame.btnPlaySetText("Lancement du jeu" + " en solo" + " ...");
		final List<String> args = ArgumentsManager.getMinecraftArgs(profile);
		mainFrame.setVisible(false);//TODO waring
	}
}
