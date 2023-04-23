package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.peer.CheckboxMenuItemPeer;
import java.util.ArrayList;

import util.*;
import livre.*;

public class EcranJeu extends JPanel implements ActionListener, Listener {

	private Image img_background;

	private MethodesLancement launcher;
	private String format;
	private Menu frame;
	private EcranMenu menu;
	private int nbChoix;
	private JButton retourB = new JButton("Retour");
	/*
	 *
	 * private JButton choix1 = new JButton();*
	 * private JButton choix2 = new JButton();*
	 * private JButton choix3 = new JButton();*
	 * private JButton choix4 = new JButton();
	 */
	// private String texte;
	private JTextArea texteArea = new JTextArea(0, 20);

	// private int width = (int)
	// Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	public EcranJeu(Menu frame, EcranMenu menu, String format, int nbChoix) {
		super(new BorderLayout());
		this.format = format;
		this.launcher = launcher;
		this.nbChoix = nbChoix;
		this.launcher = new MethodesLancement(frame, menu, this.format);
		this.frame = frame;
		this.menu = menu;
		this.setOpaque(true);

		img_background = new ImageIcon("images/background.jpg").getImage();

		// Pannel du du titre + bouton
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setOpaque(false);
		retourB.setPreferredSize(new Dimension(100, 50));
		retourB.addActionListener(this);

		/*
		 * choix1.setPreferredSize(new Dimension(100, 50));
		 * choix1.addActionListener(this);
		 * 
		 * choix2.setPreferredSize(new Dimension(100, 50));
		 * choix2.addActionListener(this);
		 * 
		 * choix3.setPreferredSize(new Dimension(100, 50));
		 * choix3.addActionListener(this);
		 * 
		 * choix4.setPreferredSize(new Dimension(100, 50));
		 * choix4.addActionListener(this);
		 */
		JLabel title = new JLabel("<html><font color='CC0000'>Livre dont vous etes le heros</font></html>",
				SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 50));

		EcranMenu.createEmptySpace(-75, title, "left");
		// Sert a décaler le titre vers la gauche pour le "recentrer"

		headerPanel.add(retourB, BorderLayout.WEST);
		headerPanel.add(title, BorderLayout.CENTER);

		// Pannel du contenu
		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setPreferredSize(new Dimension(400, 250)); // Dimension ajustable choisir de maniere arbitraire
		// contentPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel jeuP = new JPanel();

		JScrollPane scroll = new JScrollPane(texteArea);
		scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		jeuP.setLayout(new BoxLayout(jeuP, BoxLayout.PAGE_AXIS));
		jeuP.setPreferredSize(new Dimension(1600, 500));

		EcranMenu.createEmptySpace(75, contentPanel, "top");

		jeuP.add(scroll, BorderLayout.WEST);

		JPanel choixP = new JPanel();
		choixP.setLayout(new BoxLayout(choixP, BoxLayout.LINE_AXIS));
		// choixP.setBorder(BorderFactory.createLineBorder(Color.black));
		choixP.setAlignmentX(Component.CENTER_ALIGNMENT);
		choixP.setPreferredSize(new Dimension(800, 50));
		choixP.setOpaque(false);

		setChoiceButoon(choixP);

		contentPanel.add(jeuP, BorderLayout.NORTH);
		contentPanel.add(choixP, BorderLayout.SOUTH);

		this.add(headerPanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		// this.revalidate();
		// lancerJeu();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img_background, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == retourB) {
			System.out.println("Retour menu");
			this.frame.set(menu);

		} /*
			 * if (source == choix1) {
			 * System.out.println("Choix 1");
			 * this.launcher.setInputChoice(0);
			 * }
			 * 
			 * if (source == choix2) {
			 * System.out.println("Choix 2");
			 * this.launcher.setInputChoice(1);
			 * }
			 * 
			 * if (source == choix3) {
			 * System.out.println("Choix 3");
			 * this.launcher.setInputChoice(2);
			 * }
			 * 
			 * if (source == choix4) {
			 * System.out.println("Choix 4");
			 * this.launcher.setInputChoice(3);
			 * }
			 */
	}

	@Override
	public void update(Object o) {
		this.launcher = (MethodesLancement) o;
		Page pageActuelle = this.launcher.getPageActuelle();
		System.out.println("nbChoix = " + this.nbChoix);
		String texte = pageActuelle.getTexte();
		this.texteArea.setText(texte);
		this.texteArea.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setNbChoix(this.launcher.getNbChoix());

		/*
		 * this.choix1.setText(choix.get(0).getIntitule());
		 * this.choix2.setText(choix.get(1).getIntitule());
		 */
		this.revalidate();
		this.repaint();
	}

	public void lancerJeu() {
		this.launcher = new MethodesLancement(frame, menu, this.format);
		new GameControlleur(launcher, frame);
	}

	public void setChoiceButoon(JPanel choixP) {
		for (int i = 0; i < this.nbChoix; i++) {
			JButton button = new JButton("choix " + (i + 1));
			button.setPreferredSize(new Dimension(100, 50));
			button.addActionListener(this);
			button.setFont(new Font("Serif", Font.PLAIN, 30));
			final int index = i;
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Choix " + index + 1);
					launcher.setInputChoice(index);
				}
			});
			choixP.add(Box.createHorizontalStrut(10));
			choixP.add(button);
		}
	}

	public int getNbChoix() {
		return this.nbChoix;
	}

	public void setNbChoix(int val) {
		this.nbChoix = val;
		JPanel choixP = (JPanel) ((Container) this.getComponent(1)).getComponent(1);
		/*
		 * afin de recuperer choixP on part de la frame pour obtenir son composant 1 à
		 * savoir donc le second composant (le premier etant headerPanel).
		 * EcranJeu -> ContentPanel Puis on le convertit en container avec un recast.
		 * puis on recupere son composant 1 à savoir choixP.
		 */
		choixP.removeAll();
		setChoiceButoon(choixP);
		choixP.revalidate();
		choixP.repaint();
	}

}