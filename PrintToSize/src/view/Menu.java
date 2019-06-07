package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private Color textColor = new Color(0xd5d5d5);

	public Menu() {
		super();
		setBackground(new Color(0x474747));
		setPreferredSize(new Dimension(0, 35));

		UIManager.put("Menu.foreground", textColor);
		UIManager.put("Menu.font", new Font("Arial", Font.PLAIN, 13));
		UIManager.put("MenuItem.font", new Font("Arial", Font.PLAIN, 13));

		createMenuBar();

		var ma = new MouseAdapter() {
			private Point origin;

			@Override
			public void mousePressed(MouseEvent e) {
				origin = e.getLocationOnScreen();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				origin = null;
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int x = getTopLevelAncestor().getLocationOnScreen().x;
				int y = getTopLevelAncestor().getLocationOnScreen().y;

				var p = e.getLocationOnScreen();

				int dx = p.x - origin.x;
				int dy = p.y - origin.y;
				origin = e.getLocationOnScreen();

				getTopLevelAncestor().setLocation(x + dx, y + dy);
			}

		};

		addMouseListener(ma);
		addMouseMotionListener(ma);

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

		var minimizeButton = createFrameButton("-");
		var exitButton = createFrameButton("X");

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
		add(Box.createHorizontalGlue());
		add(minimizeButton);
		add(exitButton);

	}

	private JMenuItem createMenuItem(Action a) {
		var item = new JMenuItem(a);
		item.setToolTipText(null);
		return item;
	}

	private JButton createFrameButton(String a) {


		var border1 = BorderFactory.createEmptyBorder(3, 17, 3, 17);
		var border2 = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white),
				BorderFactory.createEmptyBorder(2, 16, 2, 16));

		var button = new JButton(a);
		button.setForeground(textColor);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorder(border1);
		button.setFont(new Font("Arial", Font.PLAIN, 15));
		button.getModel().addChangeListener(e -> {
			if (button.getModel().isRollover()) {
				button.setBorder(border2);
			}
			else {
				button.setBorder(border1);
			}
			if (button.getModel().isPressed()) {
				button.setBorder(border1);
			}
		});
		return button;

	}

}
