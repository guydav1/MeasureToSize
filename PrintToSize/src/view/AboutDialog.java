package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutDialog extends JDialog{

	private static final long serialVersionUID = 1L;

	public AboutDialog(JFrame owner) {
		super(owner, "About", true);
		setSize(400,400);
		setLocationRelativeTo(owner);
		
		
		add(new JLabel("<html>" + 
		
				"<h1 style=\"color: #5e9ca0;\">This is the About page</h1>\n" + 
				"<h2 style=\"color: #2e6c80;\">How to use the page:</h2>\n" + 
				"<p>read the fucking text...&nbsp;</p>\n" + 
				"<p>Click the <span style=\"background-color: #F5FF9F; color: #fff; display: inline-block; padding: 3px 10px; font-weight: bold; border-radius: 5px;\">OK</span> button close it dumbass.</p>\n" + 
				"<h2 style=\"color: #2e6c80;\">Some useful information:</h2>\n" + 
				"<ol style=\"list-style: none; font-size: 14px; line-height: 32px; font-weight: bold;\">\n" + 
				"<li style=\"clear: both;\"> You are a piece of shit</li>\n" + 
				"<li style=\"clear: both;\">You deserve nothing</li>\n" + 
				"<li style=\"clear: both;\"> Nobody loves you</li>\n" + 
				"</ol>" +
			
				"</html>"), BorderLayout.NORTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(e-> setVisible(false));
		
		add(okButton, BorderLayout.SOUTH);
		
	}

}
