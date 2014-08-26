package fr.minewild.launcher.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

import fr.minewild.launcher.data.Constants;

//TODO: Il faudrait une variable lastUpdate, et lancer une update si le serveur n'a pas été check depuis 10 sec
public class ConnectionUtils
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
			LogUtils.log(Level.INFO, Constants.CONNECTION_PREFIX + "connection check - ok");
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
				
			}
			LogUtils.log(Level.INFO, Constants.CONNECTION_PREFIX + "connection check - timeout");
			return false;
		}
		catch(IOException e)
		{
			
		}
		LogUtils.log(Level.WARNING, Constants.CONNECTION_PREFIX + "connection error");
		return false;
	}
}
