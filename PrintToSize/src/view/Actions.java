package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import controller.MainController;

public class Actions {
	
	private static MainController controller;
	
	public static void setController(MainController controller) {
		Actions.controller = controller;
	}
	
	public static class OpenAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public OpenAction() {
			super("Open File", new ImageIcon("src/resources/open_16px.png"));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
			putValue(MNEMONIC_KEY, (int)'O');
			putValue(SHORT_DESCRIPTION, "Open File");

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			controller.openFile();
			
		}
		
	}
	
	public static class SaveAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public SaveAction() {
			super("Save", new ImageIcon("src/resources/save_16px.png"));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
			putValue(MNEMONIC_KEY, (int)'S');
			putValue(SHORT_DESCRIPTION, "Save File");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO		
		}
		
	}
	
	public static class NewFileAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public NewFileAction() {
			super("New File", new ImageIcon("src/resources/file_16px.png"));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
			putValue(SHORT_DESCRIPTION, "New File");
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.newFile();
			
		}
		
	}
	
	public static class PrintAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public PrintAction() {
			super("Print", new ImageIcon("src/resources/print_16px.png"));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl P"));
			putValue(MNEMONIC_KEY, (int)'P');
			putValue(SHORT_DESCRIPTION, "Print");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class SaveAsAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public SaveAsAction() {
			super("Save As...");
			putValue(SHORT_DESCRIPTION, "Save as...");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.saveFile();				
		}
		
	}
	
	public static class ExitAction extends AbstractAction {

		
		
		private static final long serialVersionUID = 1L;
		
		public ExitAction() {
			super("Exit");
			putValue(SHORT_DESCRIPTION, "Exit");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
//			System.exit(0);
			controller.callExit();
			
		}
		
	}
	
	public static class AboutAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public AboutAction() {
			super("About", new ImageIcon("src/resources/about_16px.png"));
			putValue(SHORT_DESCRIPTION, "About");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.openAboutDialog();
			
		}
		
	}
	
	public static class ZoomInAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public ZoomInAction() {
			super("Zoom In", new ImageIcon("src/resources/zoom_in_20px.png"));
			putValue(SHORT_DESCRIPTION, "Zoom In");
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.zoom(0.1);
			
		}
		
	}
	public static class ZoomOutAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public ZoomOutAction() {
			super("Zoom Out", new ImageIcon("src/resources/zoom_out_20px.png"));
			putValue(SHORT_DESCRIPTION, "Zoom Out");
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.zoom(-0.1);
			
		}
		
	}
	
	public static class MoveAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public MoveAction() {
			super("Move", new ImageIcon("src/resources/move_20px.png"));
			putValue(SHORT_DESCRIPTION, "Move");
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.setCursor(Cursor.MOVE_CURSOR);
			controller.move();
			
		}
		
	}
	
	
	public static class RulerAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public RulerAction() {
			super("Ruler", new ImageIcon("src/resources/ruler_20px.png"));
			putValue(SHORT_DESCRIPTION, "Measure distance");
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.measure(false);
		}
		
	}
	
	public static class LineScaleAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public LineScaleAction() {
			super("By Line");
			putValue(SHORT_DESCRIPTION, "Set the distance using a line");
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift T"));
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.measure(true);
		}
		
	}
	
	
	
	
	
}
