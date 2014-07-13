package fr.minewild.launcher.frames;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class ImagePanel extends JPanel
{
	private static final long	serialVersionUID	= 1L;
	private Image				image;
	
	public ImagePanel(Image image)
	{
		super();
		this.image = image;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}