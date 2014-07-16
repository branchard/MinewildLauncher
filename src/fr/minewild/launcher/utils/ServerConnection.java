package fr.minewild.launcher.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

import fr.minewild.launcher.data.Constants;

public class ServerConnection
{
	private static Boolean serverIsOnline;
	
	public static final void updateServerStatus()
	{
		serverIsOnline = Boolean.valueOf(isOnline());
	}
	
	public static final boolean getServerStatus()
	{
		if(serverIsOnline == null)
			updateServerStatus();
		return serverIsOnline.booleanValue();
	}
	
	private static final boolean isOnline()// check minewild server communication
	{
		Socket socket = new Socket();
		try
		{
			socket.connect(new InetSocketAddress(Constants.MINEWILD_SERVER_ADDRESS, Constants.MINEWILD_SERVER_PORT), Constants.SERVER_TIMEOUT);
			socket.close();
			LogUtils.log(Level.INFO, Constants.CONNECTION_PREFIX + "connection test - ok");
			return true;
		}
		catch(SocketTimeoutException | ConnectException e)// timeout or ConnectException
		{
			try
			{
				socket.close();
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
				System.exit(1);// error
			}
			LogUtils.log(Level.WARNING, Constants.CONNECTION_PREFIX + "connection test - timeout");
			return false;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);// error
		}
		return false;
	}
}
