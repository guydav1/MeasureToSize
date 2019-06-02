package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ImagePanel extends JPanel implements ImageConsumer {

	private static final long serialVersionUID = 1L;
	BufferedImage image;


	double scale;
	MainFrame main;

	public ImagePanel(MainFrame mainf) {

		scale = 1.0;
		setBackground(Color.black);
		main = mainf;
//		new ImageLoader(this, new File("src/resources/test_image.jpg")).execute();
//		new ImageLoader(this, new File("src/resources/save_16px.png")).execute();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (image != null) {
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			int w = getWidth();
			int h = getHeight();
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();
			double x = (w - scale * imageWidth) / 2;
			double y = (h - scale * imageHeight) / 2;
			AffineTransform at = AffineTransform.getTranslateInstance(x, y);

			at.scale(scale, scale);
			g2.drawRenderedImage(image, at);
		}
		else {
			g2.setPaint(Color.white);

			String loadString = "No image";

			g2.drawString(loadString, getWidth() / 2 - loadString.length() * 4, getHeight() / 2);
		}
	}

	public Dimension getPreferredSize() {
		int w = 1;
		int h = 1;
		if (image != null) {
			w = (int) (scale * image.getWidth());
			h = (int) (scale * image.getHeight());
		}

		return new Dimension(w, h);
	}

	public void setScale(double s) {
		scale = s;
		revalidate(); // update the scroll pane
		repaint();
	}

	public void loadImage(File fileName) {

		new ImageLoader(this, fileName).execute();
	}

	@Override
	public void imageLoaded(BufferedImage image) {
		if (image.getWidth() > 800) scale = 1.0 / (image.getWidth() / 800); // HARD CODED
		else scale = 1.0;
		this.image = image;
		revalidate();
		repaint();

	}
	
	public BufferedImage getImage() {
		return image;
	}

	public double getScale() {
		return scale;
	}

	protected class ImageLoader extends SwingWorker<BufferedImage, BufferedImage> {

		private ImageConsumer consumer;
		private File file;

		public ImageLoader(ImageConsumer consumer, File file) {
			this.consumer = consumer;
			this.file = file;
		}

		@Override
		protected BufferedImage doInBackground() throws IOException {

			BufferedImage picture = ImageIO.read(file);
			return picture;

		}

		protected void done() {
			try {
				BufferedImage img = get();
				consumer.imageLoaded(img);
			}
			catch (Exception exp) {
				exp.printStackTrace();
			}
		}
	}

}
