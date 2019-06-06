package controller;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.ImagePanel;
import view.MainFrame;

public class MainController {

	private final String DEFAULT_TITLE = "Print To Size";

	private Cursor cursor;
	private File file;

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
		if (file == null) chooser.showSaveDialog(frame.getFrame());
	}

	public void openFile() {
		chooser.showOpenDialog(frame.getFrame());
		File pic = chooser.getSelectedFile();
		file = pic;
		frame.getFrame().setTitle(DEFAULT_TITLE + " - (" + pic.getName() + ")");
		((ImagePanel) frame.getImagePanel()).loadImage(pic);
	}

	public void openAboutDialog() {
		frame.getAboutDialog().setVisible(true);
	}

	public void callExit() {
		System.exit(0);
	}

	public void newFile() {
		if (file == null) return;
		frame.getFrame().setTitle(DEFAULT_TITLE);
		file = null;
		var image = frame.getImagePanel();
		var newImage = new ImagePanel(frame);
		frame.setImagePanel(newImage);
		var panel = frame.getImagePanelScrollPane();
		panel.getViewport().remove(image);
		panel.getViewport().add(newImage);
		panel.revalidate();

	}

	public void zoom(double amount) {
		if (frame.getImagePanel().getImage() == null) return;

		frame.getImagePanel().setScale(frame.getImagePanel().getScale() + amount);

	}

	public void measure() {
		var imagePanel = frame.getImagePanel();
		imagePanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		MouseAdapter f = new MouseAdapter() {
			private Point origin;
			private Point delta;
			double distance;
			double distanceInPixels;

			@Override
			public void mousePressed(MouseEvent e) {
				origin = e.getPoint();
				imagePanel.setLineOrigin(origin);
				frame.getFooterLabel().setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				setCursor(Cursor.DEFAULT_CURSOR);
				imagePanel.removeMouseListener(this);
				imagePanel.removeMouseMotionListener(this);
				imagePanel.setLineEnd(null);
				setScale(distanceInPixels);

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (origin != null) {

					int deltaX = origin.x - e.getX();
					int deltaY = origin.y - e.getY();
					distanceInPixels = Math.sqrt((double)(deltaX * deltaX) + (deltaY * deltaY));

					distance = distanceInPixels * (imagePanel.getRealScale() / imagePanel.getScale());

					imagePanel.setLineEnd(e.getPoint());
					imagePanel.repaint();

					frame.getFooterLabel().setText(String.format("%.2f", distance));
				}
			}
		};

		imagePanel.addMouseListener(f);
		imagePanel.addMouseMotionListener(f);

	}

	public void setScale(double pixels) {

		String inputS = JOptionPane.showInputDialog(frame.getFrame(), "Measure in cm");
		double input = (inputS == null) ? 0 : Double.parseDouble(inputS);

		frame.getImagePanel().setRealScale((input * frame.getImagePanel().getScale()) / pixels);

	}

	public void setCursor(int c) {
		cursor = new Cursor(c);
		frame.getImagePanel().setCursor(cursor);

	}

	public void move() {
		if (cursor.getType() != Cursor.MOVE_CURSOR) return;

		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click");
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		};

		frame.getPanel().addMouseListener(ma);
	}
}
