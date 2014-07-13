package fr.minewild.launcher.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.minewild.launcher.data.Constants;
import fr.minewild.launcher.utils.LogUtils;

public class ConsoleFrame extends JFrame
{
	private static final long	serialVersionUID	= 1L;
	
	public ConsoleFrame()
	{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(Constants.CONSOLE_ICON);
		this.setTitle("Console");
		this.setType(Type.UTILITY);
		this.setPreferredSize(new Dimension(510, 330));
		this.setLocation(0, 0);// TODO
		final JTextArea txtrLogs = new JTextArea();
		txtrLogs.setEditable(false);
		txtrLogs.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		txtrLogs.setBackground(Color.BLACK);
		txtrLogs.setForeground(Color.WHITE);
		txtrLogs.setWrapStyleWord(true);
		this.getContentPane().add(new JScrollPane(txtrLogs), BorderLayout.CENTER);
		LogUtils.setTextArea(txtrLogs);
		this.pack();
	}
}
