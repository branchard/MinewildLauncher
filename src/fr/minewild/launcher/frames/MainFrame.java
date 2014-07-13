package fr.minewild.launcher.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.minewild.launcher.data.Constants;
import fr.minewild.launcher.utils.Utils;

public class MainFrame extends JFrame implements ActionListener
{
	private static final long	serialVersionUID	= 7479325856400574176L;

	private final JLabel lblMinewildLogo = new JLabel(new ImageIcon(Constants.LAUNCHER_IMAGE));
	
	private final JLabel lblMinewildServer = new JLabel("Serveur Minewild :")
	{
		private static final long serialVersionUID = 1L;
		{
			setMaximumSize(new Dimension(195, 20));
			setForeground(Constants.LAUCNHER_FONT_COLOR);
			setFont(getFont().deriveFont(Font.BOLD));
		}
	};
	
	private final JLabel lblMinewildServerStatus = new JLabel("Please wait...")
	{

		private static final long serialVersionUID = 1L;
		{
			setFont(getFont().deriveFont(Font.ITALIC));
			setForeground(Constants.LAUCNHER_FONT_COLOR);
		}
	};
	
	private final JLabel lblPseudo = new JLabel("Pseudo :")
	{
		private static final long serialVersionUID = 1L;
		{
			setMaximumSize(new Dimension(200, 20));
			setForeground(Constants.LAUCNHER_FONT_COLOR);
			setFont(getFont().deriveFont(Font.BOLD));
		}
	};
	
	private final JTextField txtfldPseudo = new JTextField()
	{
		private static final long serialVersionUID = 1L;
		{
			setMaximumSize(new Dimension(280, 20));
		}
	};
	
	private final JLabel lblPremium = new JLabel("Compte premium :")
	{
		private static final long serialVersionUID = 1L;
		{
			setMaximumSize(new Dimension(193, 20));
			setForeground(Constants.LAUCNHER_FONT_COLOR);
			setFont(getFont().deriveFont(Font.BOLD));
		}
	};
	
	private final JCheckBox cbxPremium = new JCheckBox()
	{
		private static final long serialVersionUID = 1L;
		{
			setMaximumSize(new Dimension(20, 20));
			setOpaque(false);
		}
	};
	
	private final JLabel lblPassword = new JLabel("Mot de passe :")
	{
		private static final long serialVersionUID = 1L;
		{
			setMaximumSize(new Dimension(200, 20));
			setForeground(Constants.LAUCNHER_FONT_COLOR);
			setFont(getFont().deriveFont(Font.BOLD));
			setEnabled(false);
		}
	};
	
	private final JPasswordField pswfldPassword = new JPasswordField()
	{
		private static final long serialVersionUID = 1L;
		{
			setMaximumSize(new Dimension(280, 20));
			setEnabled(false);
		}
	};
	
	private final JButtonWrapper btnPlay = new JButtonWrapper("Jouer !", Constants.LAUNCHER_PLAYBTN_ICON, 430, 40);
	
	private final JButtonWrapper btnClose = new JButtonWrapper(Constants.LAUNCHER_CLOSEBTN_ICON, 40, 40);
	
	public MainFrame()
	{
		////////////////////////////////
		//                            //
		//    MAIN FRAME FORMATTING   //
		//                            //
		////////////////////////////////
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(Utils.buildTitle());
		this.setIconImage(Constants.LAUNCHER_ICON);
		this.setType(Type.NORMAL);
		this.setPreferredSize(new Dimension(540, 330));
		this.setResizable(false);
		this.setContentPane(new ImagePanel(Constants.LAUNCHER_BACKGROUND));
		
		////////////////////////////////
		//                            //
		//          LISTENER          //
		//                            //
		////////////////////////////////
		txtfldPseudo.getDocument().addDocumentListener(new DocumentListener()
			{
				public void changedUpdate(DocumentEvent e)
				{
					warn();
				}
				
				public void removeUpdate(DocumentEvent e)
				{
					warn();
				}
				
				public void insertUpdate(DocumentEvent e)
				{
					warn();
				}
				
				public void warn()
				{
					updatePlayButton();
				}
			});
		cbxPremium.addItemListener(new ItemListener()
			{
				
				public void itemStateChanged(ItemEvent e)
				{
					updatePlayButton();
				}
			});
		cbxPremium.addActionListener(this);
		pswfldPassword.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent e)
			{
				warn();
			}
			
			public void removeUpdate(DocumentEvent e)
			{
				warn();
			}
			
			public void insertUpdate(DocumentEvent e)
			{
				warn();
			}
			
			public void warn()
			{
				updatePlayButton();
			}
		});
		btnClose.addActionListener(this);
				
		////////////////////////////////
		//                            //
		//       ADD COMPONNENTS      //
		//                            //
		////////////////////////////////
		final JPanel line1 = new MotionPanel(this);
		line1.setLayout(new BoxLayout(line1, BoxLayout.LINE_AXIS));
		line1.add(lblMinewildLogo);
		line1.setOpaque(false);
		
		final JPanel line2vert2 = new JPanel();
		line2vert2.setMaximumSize(new Dimension(285, 30));
		line2vert2.setLayout(new FlowLayout(FlowLayout.LEFT));
		line2vert2.add(lblMinewildServerStatus);
		line2vert2.setOpaque(false);
		final JPanel line2 = new JPanel();
		line2.setLayout(new BoxLayout(line2, BoxLayout.LINE_AXIS));
		line2.add(lblMinewildServer);
		line2.add(line2vert2);
		line2.setOpaque(false);
		
		final JPanel line3 = new JPanel();
		line3.setLayout(new BoxLayout(line3, BoxLayout.LINE_AXIS));
		line3.add(lblPseudo);
		line3.add(txtfldPseudo);
		line3.setOpaque(false);
		
		final JPanel line4vert2 = new JPanel();
		line4vert2.setMaximumSize(new Dimension(287, 30));
		line4vert2.setLayout(new FlowLayout(FlowLayout.LEFT));
		line4vert2.add(cbxPremium);
		line4vert2.setOpaque(false);
		final JPanel line4 = new JPanel();
		line4.setLayout(new BoxLayout(line4, BoxLayout.LINE_AXIS));
		line4.add(lblPremium);
		line4.add(line4vert2);
		line4.setOpaque(false);
		
		final JPanel line5 = new JPanel();
		line5.setLayout(new BoxLayout(line5, BoxLayout.LINE_AXIS));
		line5.add(lblPassword);
		line5.add(pswfldPassword);
		line5.setOpaque(false);
		
		final JPanel line6 = new JPanel();
		line6.setLayout(new BoxLayout(line6, BoxLayout.LINE_AXIS));
		line6.add(Box.createRigidArea(new Dimension(480, 10)));
		line6.setOpaque(false);
		
		final JPanel line7 = new JPanel();
		line7.setLayout(new BoxLayout(line7, BoxLayout.LINE_AXIS));
		btnPlay.setEnabled(false);
		line7.add(btnPlay);
		line7.add(Box.createRigidArea(new Dimension(10, 40)));
		line7.add(btnClose);
		line7.setOpaque(false);
		
		final JPanel vert1 = new JPanel();
		vert1.setLayout(new BoxLayout(vert1, BoxLayout.PAGE_AXIS));
		vert1.add(line1);
		vert1.add(line2);
		vert1.add(line3);
		vert1.add(line4);
		vert1.add(line5);
		vert1.add(line6);
		vert1.add(line7);
		vert1.setOpaque(false);
		
		this.getContentPane().add(vert1);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void setOnline(boolean isOnline)
	{
		if(isOnline)
		{
			lblMinewildServerStatus.setText("EN LIGNE");
		}
		else
		{
			lblMinewildServerStatus.setText("HORS LIGNE");
		}
	}
	
	public void updatePlayButton()
	{
		if(txtfldPseudo.getText().length() >= 3 && (cbxPremium.isSelected() ? pswfldPassword.getPassword().length >= 3 : true))
			btnPlay.setEnabled(true);
		else
			btnPlay.setEnabled(false);
	}
	
	public void updatePasswordField()
	{
		if(cbxPremium.isSelected())
		{
			pswfldPassword.setEnabled(true);
			lblPassword.setEnabled(true);
		}
		else
		{
			pswfldPassword.setEnabled(false);
			lblPassword.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == btnClose)
			System.exit(0);
		if(arg0.getSource() == cbxPremium)
			updatePasswordField();			
	}
}