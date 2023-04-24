
package view.kk;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Fenetre extends JFrame {

	public Fenetre(JPanel panel) {
		super("Graphe fen");
		this.set(panel);
	}

	// ------------------------------------------------------------------//

	public void set(JPanel panel) {
		super.setContentPane(panel);
		super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
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
