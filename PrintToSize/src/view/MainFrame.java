package view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.MainController;

public class MainFrame {

	private MainController controller;

	private JFrame frame;
	private JPanel panel;

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
		toolBar = new ToolBar(controller);
		
		aboutDialog = new AboutDialog(frame);
		

		imagePanel = new ImagePanel(this);
		imagePanelScrollPane = new JScrollPane(imagePanel);
		imagePanelScrollPane.getVerticalScrollBar().setUnitIncrement(16);
//		imagePanel.addMouseWheelListener(e -> {
//			if (e.isAltDown()) {
//				double oldZoom = picturePanel.scale;
//				double amount = Math.pow(1.02, e.getScrollAmount());
//				if (e.getWheelRotation() > 0) {
//					// zoom in (amount)
//					picturePanel.setScale(oldZoom * amount);
//				}
//				else {
//					// zoom out (amount)
//					picturePanel.setScale(oldZoom / amount);
//				}
//			}
//			else {
//				// if alt isn't down then propagate event to scrollPane
//
//				// dispatchEvent(e);
//			}
//		});

//		ImageZoom zoom = new ImageZoom(picturePanel);
//		panel.add(zoom.getUIPanel(), "West");

		panel.add(toolBar, "North");
		panel.add(imagePanelScrollPane, BorderLayout.CENTER);

		frame.getContentPane().add(panel);

	}

	public JFrame createFrame() {

		var frame = new JFrame("Print to size");

		frame.setJMenuBar(menu);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setResizable(false);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		return frame;

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
	
	

}
