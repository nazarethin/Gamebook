package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

import util.*;
import livre.*;

public class EcranJeu extends JPanel implements ActionListener, Listener {

	private Image img_background;

	private MethodesLancement launcher;
	private String format;
	private Menu frame;
	private EcranMenu menu;
	private JButton retourB = new JButton("Retour");
	private JButton choix1 = new JButton("                       Choix 1                       ");
	private JButton choix2 = new JButton("                       Choix 2                       ");
	private JButton choix3 = new JButton("                       Choix 3                       ");
	private JButton choix4 = new JButton("                       Choix 4                       ");
	// private String texte;
	private JTextArea texteArea = new JTextArea(0, 20);

	// private int width = (int)
	// Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	public EcranJeu(Menu frame, EcranMenu menu, String format) {
		super(new BorderLayout());
		this.format = format;
		this.launcher = launcher;
		this.launcher = new MethodesLancement(frame, menu, this.format);
		this.frame = frame;
		this.menu = menu;
		this.setOpaque(true);

		Font buttonFont = new Font("Serif", Font.PLAIN, 30);

		img_background = new ImageIcon("images/background.jpg").getImage();

		// Pannel du du titre + bouton
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setOpaque(false);
		retourB.setPreferredSize(new Dimension(100, 50));
		retourB.addActionListener(this);

		choix1.setPreferredSize(new Dimension(100, 50));
		choix1.addActionListener(this);

		choix2.setPreferredSize(new Dimension(100, 50));
		choix2.addActionListener(this);

		choix3.setPreferredSize(new Dimension(100, 50));
		choix3.addActionListener(this);

		choix4.setPreferredSize(new Dimension(100, 50));
		choix4.addActionListener(this);

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
		choixP.setLayout(new BoxLayout(choixP, BoxLayout.PAGE_AXIS));
		// choixP.setBorder(BorderFactory.createLineBorder(Color.black));
		choixP.setAlignmentX(Component.CENTER_ALIGNMENT);
		choixP.setPreferredSize(new Dimension(496, 195));

		choix1.setFont(buttonFont);
		choix2.setFont(buttonFont);
		choix3.setFont(buttonFont);
		choix4.setFont(buttonFont);

		choixP.add(choix1);
		choixP.add(choix2);
		choixP.add(choix3);
		choixP.add(choix4);

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
		}
		if (source == choix1) {
			System.out.println("Choix 1");
			this.launcher.setInputChoice(0);
		}

		if (source == choix2) {
			System.out.println("Choix 2");
			this.launcher.setInputChoice(1);
		}

		if (source == choix3) {
			System.out.println("Choix 3");
			this.launcher.setInputChoice(2);
		}

		if (source == choix4) {
			System.out.println("Choix 4");
			this.launcher.setInputChoice(3);
		}
	}

	@Override
	public void update(Object o) {
		this.launcher = (MethodesLancement) o;
		Page pageActuelle = this.launcher.getPageActuelle();
		String texte = pageActuelle.getTexte();
		this.texteArea.setText(texte);
		this.texteArea.setFont(new Font("Arial", Font.PLAIN, 20));
		this.revalidate();
		this.repaint();
	}

	public void lancerJeu() {
		this.launcher = new MethodesLancement(frame, menu, this.format);
		new GameControlleur(launcher, frame);
	}

}