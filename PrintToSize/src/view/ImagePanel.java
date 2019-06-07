package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

public class ImagePanel extends JPanel implements ImageConsumer {

	private static final long serialVersionUID = 1L;
	private transient BufferedImage image;

	private Point lineOrigin; // used to draw measure line.
	private Point lineEnd;
	private Point zoomPosition;

	private double scale;
	private double realScale;

	MainFrame main;

	public ImagePanel(MainFrame mainf) {
		super();

		scale = 1.0;
		setBackground(Color.black);
		main = mainf;
		var zoomAdapter = new ZoomListener();
		addKeyListener(zoomAdapter);
		addMouseListener(zoomAdapter);
		addMouseMotionListener(zoomAdapter);
		new ImageLoader(this, new File("src/resources/ruler.png")).execute();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (image != null) {
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			int w = getWidth();
			int h = getHeight();
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();
//			double x = (w - scale * imageWidth) / 2;
//			double y = (h - scale * imageHeight) / 2;
			double x, y;
			if (zoomPosition == null) {
				x = 0;
				y = 0;
			} else {
				x = (w - scale * zoomPosition.x)/2;
				y = (h - scale * zoomPosition.y)/2;
			}
			AffineTransform at = AffineTransform.getTranslateInstance(x, y);

			at.scale(scale, scale);
			g2.drawRenderedImage(image, at);
		}
		else {
			g2.setPaint(Color.white);

			String loadString = "No image";

			g2.drawString(loadString, getWidth() / 2 - loadString.length() * 4, getHeight() / 2);
		}

		if (lineOrigin != null && lineEnd != null) {
			g.setColor(Color.red);
			g.drawLine(lineOrigin.x, lineOrigin.y, lineEnd.x, lineEnd.y);
		}
	}

	@Override
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

		if (s < 0.1) return;
		scale = s;
		revalidate(); // update the scroll pane
		repaint();
	}

	public void loadImage(File fileName) {

		new ImageLoader(this, fileName).execute();
	}

	@Override
	public void imageLoaded(BufferedImage image) {
		if (image.getWidth() > 800) scale = 1.0 / (image.getWidth() / 800.0); // TODO FIX HARD CODED
		else
			scale = 1.0;
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

	public Point getLineOrigin() {
		return lineOrigin;
	}

	public void setLineOrigin(Point lineOrigin) {
		this.lineOrigin = lineOrigin;
	}

	public Point getLineEnd() {
		return lineEnd;
	}

	public void setLineEnd(Point lineEnd) {
		this.lineEnd = lineEnd;
	}

	public double getRealScale() {
		return realScale;
	}

	public void setRealScale(double realScale) {
		this.realScale = realScale;
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

			return ImageIO.read(file);

		}

		@Override
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

	private class ZoomListener extends MouseAdapter implements KeyListener {
		private Point origin;
		private Cursor zoomInCursor = createCursor("src/resources/zoom_in_cursor.png");
		private Cursor zoomOutCursor = createCursor("src/resources/zoom_out_cursor.png");
		private boolean zoomIn = false;
		private boolean zoomOut = false;
		private boolean space = false;

		@Override
		public void mouseClicked(MouseEvent e) {

			if (zoomIn) {
				setScale(getScale() + getScale() / 5);
			}
			else if (zoomOut) {
				setScale(getScale() - getScale() / 5);
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			requestFocus();
			if (zoomIn || zoomOut) {
				origin = e.getPoint();
				zoomPosition = origin;
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (origin != null && (zoomIn || zoomOut)) {
				int xDrag = origin.x - e.getX();
				origin = e.getPoint();
				if (xDrag < 0) setCursor(zoomOutCursor);
				else {
					setCursor(zoomInCursor);
				}
				setScale(getScale() + (double) xDrag / Math.max(image.getWidth(), image.getHeight()));
			}

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) space = true;
			if (e.isControlDown() && space && e.isAltDown()) {

				setCursor(zoomOutCursor);
				zoomOut = true;
				zoomIn = false;
			}
			else if (e.isControlDown() && space) {
				setCursor(zoomInCursor);
				zoomIn = true;
				zoomOut = false;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_SPACE) space = false;

			if (e.isControlDown() && space) {
				setCursor(zoomInCursor);
				zoomIn = true;
				zoomOut = false;
			}
			else {
				setCursor(Cursor.getDefaultCursor());
				zoomOut = zoomIn = false;
			}

		}

		private Cursor createCursor(String path) {
			Toolkit t1 = Toolkit.getDefaultToolkit();
			Image img = t1.getImage(path);
			Point point = new Point(16, 16);

			return t1.createCustomCursor(img, point, "Cursor");
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// do nothing
		}

	}

}
