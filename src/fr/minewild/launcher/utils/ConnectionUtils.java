package fr.minewild.launcher.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;

import fr.minewild.launcher.data.Constants;

public class ConnectionUtils
{
	public static String getUUIDfromServer(String username)
	{
		LogUtils.log(Level.INFO, Constants.CONNECTION_PREFIX + "Getting UUID from " + Constants.MINEWILD_SERVER_ADDRESS + "...");
		PrintWriter out = null;
		BufferedReader input = null;
		Socket socket = null;
		try
		{
			socket = new Socket(Constants.MINEWILD_SERVER_ADDRESS, Constants.MINEWILD_GET_UUID_PORT);
			socket.setSoTimeout(Constants.SERVER_TIMEOUT);
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(username);
			String result = input.readLine();
			LogUtils.log(Level.INFO, Constants.LAUNCHER_PREFIX + "UUID: " + result);
			socket.close();
			return result;
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UUID.randomUUID().toString().replaceAll("-", "");// TODO gérer les premiums
	}
}
