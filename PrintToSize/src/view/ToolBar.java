package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.MainController;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	MainController controller;

	public ToolBar(MainController controller) {
		super("Tools");
		this.controller = controller;
		setBackground(new Color(0x2c3135));


		createToolBar();
	}

	private void createToolBar() {

		// Make Buttons

		var printButton = createButton(new Actions.PrintAction());
		var openButton = createButton(new Actions.OpenAction());
		var fileButton = createButton(new Actions.NewFileAction());
		var zoomInButton = createButton(new Actions.ZoomInAction());
		var zoomOutButton = createButton(new Actions.ZoomOutAction());
		var moveButton = createButton(new Actions.MoveAction());
		var rulerButton = createButton(new Actions.RulerAction());
		
		
		addAll(fileButton, openButton, printButton);
		addSeparator();
		addAll(zoomInButton, zoomOutButton, moveButton, rulerButton);

	}

	private JButton createButton(Action action) {
		var button = new JButton(action);
		button.setHideActionText(true);
		button.setFocusPainted(false);
		//button.setContentAreaFilled(false);
		return button;
	}

	private void addAll(Component... comp) {
		for (Component c : comp) {
			add(c);
		}
	}

}
