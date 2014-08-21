package fr.minewild.launcher.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;

import fr.minewild.launcher.data.Constants;
import fr.minewild.launcher.data.Tuple;

public class ConnectionUtils
{
	public static Tuple<String, Boolean> getUUIDfromServer(String username)
	{
		String result = null;
		Boolean isWhiteListed = Boolean.FALSE;
		if(ServerConnection.getServerStatus())
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
				result = input.readLine();
				socket.close();
			}
			catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if((!ServerConnection.getServerStatus()) || result.equals("null"))
		{
			LogUtils.log(Level.INFO, Constants.CONNECTION_PREFIX + "Server is offline, randomUUID generated");
			result = UUID.randomUUID().toString().replaceAll("-", "");
		}
		else
			isWhiteListed = Boolean.TRUE;
		LogUtils.log(Level.INFO, Constants.CONNECTION_PREFIX + "UUID: " + result);
		return new Tuple<String, Boolean>(result, isWhiteListed);
	}
}
