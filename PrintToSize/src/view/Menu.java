package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	private static final long serialVersionUID = 1L;

	public Menu() {
		super();
		
		createMenuBar();
	}
	private void createMenuBar() {
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

		var fileMenu = new JMenu("File");
		var helpMenu = new JMenu("Help");


		// Init menu items
		var newMenuItem = new JMenuItem(new Actions.NewFileAction());
		var saveMenuItem = new JMenuItem(new Actions.SaveAction());
		var saveAsMenuItem = new JMenuItem(new Actions.SaveAsAction());
		var openMenuItem = new JMenuItem(new Actions.OpenAction());
		var printMenuItem = new JMenuItem(new Actions.PrintAction());
		var exitMenuItem = new JMenuItem(new Actions.ExitAction());
		var aboutMenuItem = new JMenuItem(new Actions.AboutAction());

		
		// Other stuff with menu items

		saveMenuItem.setEnabled(false);

		// Add menu items
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(printMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		helpMenu.add(aboutMenuItem);

		add(fileMenu);
		add(helpMenu);

		
	}

}
