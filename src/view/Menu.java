package view;

import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {

	public Menu() {
		super("Analyseur de livre dont vous êtes le héro");
		this.set(new EcranMenu(this));
	}

	public void set(JPanel panel) {
		super.setContentPane(panel);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
		super.setVisible(true);
	}

	@Override
	public Dimension getPreferredSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
}
