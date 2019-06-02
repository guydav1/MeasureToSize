package driver;

import java.awt.EventQueue;

import controller.MainController;
import view.Actions;
import view.MainFrame;

public class Main {

	private static MainFrame frame;
	private static MainController controller;
	public static void main(String[] args) {
		

		EventQueue.invokeLater(() -> {
			init();
		});
	}

	private static void init() {
		controller = new MainController();
		Actions.setController(controller);
		frame = new MainFrame(controller);
		
		controller.addFrame(frame);
	}
	
	

}
