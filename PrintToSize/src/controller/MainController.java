package controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.ImagePanel;
import view.MainFrame;

public class MainController {

	private MainFrame frame;
	private JFileChooser chooser = new JFileChooser(new File("."));
	
	
	public MainController() {
		chooser.setFileFilter(new FileNameExtensionFilter("Image files", "gif", "jpg", "jpeg", "png", "bmp"));
		chooser.setAcceptAllFileFilterUsed(false);
	}


	public void addFrame(MainFrame frame) {
		this.frame = frame;
	}
	
	public void saveFile() {
		chooser.showSaveDialog(frame.getFrame());
	}

	public void openFile() {
		chooser.showOpenDialog(frame.getFrame());
		File pic = chooser.getSelectedFile();
		((ImagePanel) frame.getImagePanel()).loadImage(pic);
	}
	
	public void openAboutDialog() {
		frame.getAboutDialog().setVisible(true);
	}

	public void callExit() {
		System.exit(0);
	}
	
	public void newFile() {
		if(frame.getImagePanel().getImage() == null) return;
		var image = frame.getImagePanel();
		var newImage = new ImagePanel(frame);
		frame.setImagePanel(newImage);
		var panel = frame.getImagePanelScrollPane();
		panel.getViewport().remove(image);
		panel.getViewport().add(newImage);
		panel.revalidate();
	}
}
