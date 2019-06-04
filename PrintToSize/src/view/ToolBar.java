package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

import controller.MainController;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	MainController controller;

	public ToolBar(MainController controller) {
		super("Tools");
		this.controller = controller;
		setBackground(new Color(0x2c3135));
		setBorder(BorderFactory.createEmptyBorder());


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
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.darkGray), BorderFactory.createEmptyBorder(6, 6, 6, 6)));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBorderPainted(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBorderPainted(false);
			}
		});
		return button;
	}

	private void addAll(Component... comp) {
		for (Component c : comp) {
			add(c);
		}
	}

}
