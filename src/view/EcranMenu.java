package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

import view.kk.*;
import kk.calcul.*;
import kk.*;

public class EcranMenu extends JPanel implements ActionListener {

	private Menu frame;
	private JButton jouerB = new JButton("Jouer");
	private JButton graphB = new JButton("Graphe");
	private JButton metriqueB = new JButton("Métrique");
	private Image img_background;

	public EcranMenu(Menu frame) {
		super(new GridLayout(2, 1));
		this.frame = frame;
		this.setOpaque(true);
		img_background = new ImageIcon("images/background.jpg").getImage();

		JLabel titre = new JLabel("<html><font color='CC0000'>Analyseur de livre dont vous êtes le héros</font></html>",
				SwingConstants.CENTER);
		titre.setFont(new Font("Arial", Font.BOLD, 40));
		this.add(titre);

		JPanel content = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
		content.setOpaque(false);

		/* ---------------------------------- */
		jouerB.setPreferredSize(new Dimension(200, 80));
		jouerB.setFont(new Font("Arial", Font.PLAIN, 30));
		jouerB.addActionListener(this);
		jouerB.setOpaque(false);
		// jouerB.setContentAreaFilled(false);
		content.add(jouerB);

		graphB.setFont(new Font("Arial", Font.PLAIN, 30));
		graphB.setPreferredSize(new Dimension(200, 80));
		graphB.addActionListener(this);
		content.add(graphB);

		metriqueB.setFont(new Font("Arial", Font.PLAIN, 30));
		metriqueB.setPreferredSize(new Dimension(200, 80));
		metriqueB.addActionListener(this);
		content.add(metriqueB);

		this.add(content);
		this.revalidate();

		/*
		File f = new File("images");
		System.out.println("Chemin absolu : " + f.getAbsolutePath());
		System.out.println(f.exists());
		*/

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img_background, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == jouerB) {
			System.out.println("Lancer jeu");
			this.frame.set(new EcranChoixLivre(frame, this));

		} else if (source == graphB) {
			System.out.println("lancer graph");
			//this.frame.set(new EcranGraph(frame, this));
			//this.frame.set(new PanneauGraphe(frame, this));
			this.frame.set(new PanneauChoix(frame, this));
			
		} else if (source == metriqueB) {
			System.out.println("lancer metrique");
			this.frame.set(new EcranMetrique(frame, this));
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public static void createEmptySpace(int size, JComponent component, String position) {
		/*
		 * Créer un espace vide simulant un décalage en faire une fonction a part
		 * entiere évite la répition de code et rends l'utilisation tres simple.
		 */
		Insets insets = component.getInsets();
		switch (position) {
			case "top":
				insets.top = size;
				break;
			case "left":
				insets.left = size;
				break;
			case "right":
				insets.right = size;
				break;
			case "bottom":
				insets.bottom = size;
				break;
			default:
				throw new IllegalArgumentException("Invalid position: " + position);
		}
		component.setBorder(new EmptyBorder(insets));
	}
}
