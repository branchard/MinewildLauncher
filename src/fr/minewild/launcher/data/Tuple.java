package fr.minewild.launcher.data;

public class Tuple<A, B>
{
	private A primary;
	private B second;
	
	public Tuple(A primary, B second)
	{
		this.primary = primary;
		this.second = second;
	}

	public A getPrimary()
	{
		return primary;
	}

	public void setPrimary(A primary)
	{
		this.primary = primary;
	}

	public B getSecond()
	{
		return second;
	}

	public void setSecond(B second)
	{
		this.second = second;
	}
}
