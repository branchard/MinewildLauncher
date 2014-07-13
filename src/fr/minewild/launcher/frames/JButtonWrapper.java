package fr.minewild.launcher.frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.minewild.launcher.Main;

public class JButtonWrapper extends JButton
{
	private static final long	serialVersionUID	= 7260031944199523692L;
	
	private String				iconName;
	private String				iconPath;
	private String 				iconType;
	
	public JButtonWrapper(String text, String icon, int width, int height)
	{
		super(text, new ImageIcon(loadImageFromRes(icon)));
		iconType = icon.substring(icon.lastIndexOf('.'));
		iconName = icon.substring(icon.lastIndexOf('/')+1).replaceAll(iconType, "");
		iconPath = icon.replaceAll(iconName + iconType, "");
		super.setDisabledIcon(new ImageIcon(loadImageFromRes(iconPath + iconName + "-disable" + iconType)));
		super.setMaximumSize(new Dimension(width, height));
		super.setPreferredSize(new Dimension(width, height));
		super.setHorizontalTextPosition(CENTER);
		super.setBorderPainted(false);
		super.setFocusPainted(false);
		super.setContentAreaFilled(false);
		super.addMouseListener(new MouseListener()
			{
				public void mouseClicked(MouseEvent e)
				{
					
				}
				
				public void mouseEntered(MouseEvent e)
				{
					JButtonWrapper source = (JButtonWrapper) e.getSource();
					source.setIcon("-hover");
				}
				
				public void mousePressed(MouseEvent e)
				{
					JButtonWrapper source = (JButtonWrapper) e.getSource();
					source.setIcon("-onclic");
				}
				
				public void mouseReleased(MouseEvent e)
				{
					JButtonWrapper source = (JButtonWrapper) e.getSource();
					source.setIcon("-hover");
				}
				
				public void mouseExited(MouseEvent e)
				{
					JButtonWrapper source = (JButtonWrapper) e.getSource();
					source.setIcon("");
				}
			});
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.setForeground(new Color(226, 226, 226));
	}
	
	public JButtonWrapper(String icon, int width, int height)
	{
		this("", icon, width, height);
	}
	
	public String getIconName()
	{
		return iconName;
	}
	
	public void setIconName(String name)
	{
		iconName = name;
		
		setIcon("");
	}
	
	public void setIcon(String name)
	{
		super.setIcon(new ImageIcon(loadImageFromRes(iconPath + iconName + name + iconType)));
	}
	
	private static final Image loadImageFromRes(final String path)
	{
		return Toolkit.getDefaultToolkit().getImage(Main.class.getResource(path));
	}
}