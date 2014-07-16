package fr.minewild.launcher.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;

import fr.minewild.launcher.Main;
import fr.minewild.launcher.data.Constants;

public class LastLoginSaveManager
{	
	public static String[] loadContents()
	{
		String[] result = new String[2];
		try
		{
			InputStream ips = new FileInputStream(Main.system.getMinewildDirectory() + File.separator + Constants.LAUCNHER_SAVE_FILE_NAME);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String[] decoupe = br.readLine().split(":");// TODO: test if not enough ":"
			result[0] = decoupe[1];
			result[1] = decoupe[2];
			br.close();
		}
		catch(Exception e)
		{
			result[0] = "";
			result[1] = "false"; 
		}
		LogUtils.log(Level.INFO, Constants.LAUNCHER_PREFIX + "lastlogin file load: " + result[0] + " " + result[1]);
		return result;
	}
	
	public static void saveContents(String pseudoFld, boolean cbxCheck)
	{
		StringBuilder result = new StringBuilder("lastLogin");
		result.append(":");
		result.append(pseudoFld);
		result.append(":");
		result.append(cbxCheck);
		createFile(result.toString());
	}
	
	private static void createFile(String get)
	{
		try
		{
			PrintWriter out = new PrintWriter(new FileWriter(Main.system.getMinewildDirectory() + File.separator + Constants.LAUCNHER_SAVE_FILE_NAME));
			out.print(get);
			out.close();
			LogUtils.log(Level.INFO, Constants.LAUNCHER_PREFIX + "lastlogin file updated:" + Main.system.getMinewildDirectory() + Constants.LAUCNHER_SAVE_FILE_NAME);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
