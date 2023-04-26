package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
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

	private JTextArea texteArea = new JTextArea(0, 20);


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

		JLabel title = new JLabel("<html><font color='CC0000'>Livre dont vous etes le heros</font></html>",
				SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 50));

		EcranMenu.createEmptySpace(-75, title, "left");

		headerPanel.add(retourB, BorderLayout.WEST);
		headerPanel.add(title, BorderLayout.CENTER);

		// Pannel du contenu
		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setPreferredSize(new Dimension(400, 250)); 

		JPanel jeuP = new JPanel();

		JScrollPane scroll = new JScrollPane(texteArea);
		scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		jeuP.setLayout(new BoxLayout(jeuP, BoxLayout.PAGE_AXIS));
		jeuP.setPreferredSize(new Dimension(1600, 500));

		EcranMenu.createEmptySpace(75, contentPanel, "top");

		jeuP.add(scroll, BorderLayout.WEST);

		JPanel choixP = new JPanel();
		choixP.setLayout(new BoxLayout(choixP, BoxLayout.LINE_AXIS));
		choixP.setAlignmentX(Component.CENTER_ALIGNMENT);
		choixP.setPreferredSize(new Dimension(800, 50));
		choixP.setOpaque(false);

		setChoiceButoon(choixP);

		contentPanel.add(jeuP, BorderLayout.NORTH);
		contentPanel.add(choixP, BorderLayout.SOUTH);

		this.add(headerPanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		this.revalidate();
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
			this.frame.set(menu);

		} 

	}

	@Override
	public void update(Object o) {
		this.launcher = (MethodesLancement) o;
		Page pageActuelle = this.launcher.getPageActuelle();
		String texte = pageActuelle.getTexte();
		this.texteArea.setText(texte);
		this.texteArea.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setNbChoix(this.launcher.getNbChoix());

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
