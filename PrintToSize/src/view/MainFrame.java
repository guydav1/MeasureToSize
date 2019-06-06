package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.MainController;

public class MainFrame implements Serializable{

	private static final long serialVersionUID = 1L;

	private MainController controller;

	private JFrame frame;
	private JPanel panel;
	
	private JPanel footer;
	private JLabel footerLabel;
	
	
	private Menu menu;
	private JDialog aboutDialog;
	private ToolBar toolBar;
	private ImagePanel imagePanel;
	private JScrollPane imagePanelScrollPane;

	public MainFrame(MainController controller) {

		this.controller = controller;

		frame = createFrame();
		
		menu = new Menu();
		frame.setJMenuBar(menu);

		panel = new JPanel(new BorderLayout());
		
		footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footer.setBorder(BorderFactory.createEmptyBorder());
		footer.setBackground(new Color(0x474747));
		footer.setPreferredSize(new Dimension(0,25));
		footerLabel = new JLabel();
		footerLabel.setForeground(Color.white);
		footer.add(footerLabel);
		
		
		
		toolBar = new ToolBar(controller);
		
		aboutDialog = new AboutDialog(frame);
		

		imagePanel = new ImagePanel(this);
		imagePanelScrollPane = new JScrollPane(imagePanel);
		imagePanelScrollPane.setBorder(BorderFactory.createEmptyBorder());
		imagePanelScrollPane.getVerticalScrollBar().setUnitIncrement(16);


		panel.add(toolBar, "North");
		panel.add(imagePanelScrollPane, BorderLayout.CENTER);
		panel.add(footer, "South");

		frame.getContentPane().add(panel);

	}

	public JFrame createFrame() {

		var f = new JFrame("Print to size");
		f.setJMenuBar(menu);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 800);
		f.setResizable(false);

		f.setLocationRelativeTo(null);
		f.setVisible(true);

		return f;

	}

	public JFrame getFrame() {
		return frame;
	}
	
	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public JDialog getAboutDialog() {
		return aboutDialog;
	}

	public JScrollPane getImagePanelScrollPane() {
		return imagePanelScrollPane;
	}

	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JPanel getFooter() {
		return footer;
	}

	public JLabel getFooterLabel() {
		return footerLabel;
	}
	
	
	
	

}
