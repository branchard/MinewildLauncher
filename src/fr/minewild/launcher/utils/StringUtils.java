package fr.minewild.launcher.utils;

import java.util.List;

public class StringUtils
{
	public static String join(final List<String> list, final char separator)
	{
		return join(list, new Character(separator).toString());
	}
	
	public static String join(final List<String> list, final String separator)//TODO
	{
		if(list.size() > 0)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			for(String i : list)
			{
				stringBuilder.append(i);
				stringBuilder.append(separator);
			}
			String result = stringBuilder.toString();
			return result.substring(0 ,result.length() - separator.length());
		}
		return "";
	}
}
