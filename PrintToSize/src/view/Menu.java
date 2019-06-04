package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public Menu() {
		super();
		setBackground(new Color(0x474747));
		setPreferredSize(new Dimension(0, 35));
		
		UIManager.put("Menu.foreground", new Color(0xd5d5d5));
		UIManager.put("Menu.font", new Font("Arial", Font.PLAIN, 13));
		UIManager.put("MenuItem.font", new Font("Arial", Font.PLAIN, 13));
		
		
//		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		
		createMenuBar();

	}
	

	private void createMenuBar() {

		var fileMenu = new JMenu("File");
		var toolsMenu = new JMenu("Tools");
		var helpMenu = new JMenu("Help");

		// Files Menu
		var newMenuItem = createMenuItem(new Actions.NewFileAction());
		var saveMenuItem = createMenuItem(new Actions.SaveAction());
		var saveAsMenuItem = createMenuItem(new Actions.SaveAsAction());
		var openMenuItem = createMenuItem(new Actions.OpenAction());
		var printMenuItem = createMenuItem(new Actions.PrintAction());
		var exitMenuItem = createMenuItem(new Actions.ExitAction());
		
		// Tools Menu
		var zoomInMenuItem = createMenuItem(new Actions.ZoomInAction());
		var zoomOutMenuItem = createMenuItem(new Actions.ZoomOutAction());
		var moveMenuItem = createMenuItem(new Actions.MoveAction());
		var rulerMenuItem = createMenuItem(new Actions.RulerAction());
		var setScaleMenu = new JMenu("Set Scale");
		var lineScaleMenuItem = createMenuItem(new Actions.LineScaleAction());
		var areaScaleMenuItem = new JMenuItem("By Area");
		
		// Help Menu
		var aboutMenuItem = createMenuItem(new Actions.AboutAction());
		
		
		

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
		
		
		toolsMenu.add(moveMenuItem);
		toolsMenu.add(zoomInMenuItem);
		toolsMenu.add(zoomOutMenuItem);
		toolsMenu.add(rulerMenuItem);
		toolsMenu.addSeparator();
		toolsMenu.add(setScaleMenu);
		setScaleMenu.add(lineScaleMenuItem);
		setScaleMenu.add(areaScaleMenuItem);

		helpMenu.add(aboutMenuItem);

		add(fileMenu);
		add(toolsMenu);
		add(helpMenu);

	}
	
	private JMenuItem createMenuItem(Action a) {
		var item = new JMenuItem(a);
		item.setToolTipText(null);
		return item;
	}


}
