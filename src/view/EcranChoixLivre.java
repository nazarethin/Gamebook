package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.BorderFactory;

import util.*;
import livre.*;

public class EcranChoixLivre extends JPanel implements ActionListener {

    private JPanel nextPanel;
    private Menu frame;
    private EcranMenu menu;
    private JButton retourB = new JButton("Retour");

    private JButton b1 = new JButton("FotW (JSON)");
    private JButton b2 = new JButton("Le labyrinthe de la mort (Txt)");
    private Image img_background;

    public EcranChoixLivre(Menu frame, EcranMenu menu/* , JPanel nextPanel */) {
        super(new BorderLayout());
        this.frame = frame;
        this.menu = menu;
        /* this.nextPanel = nextPanel */;

        this.setOpaque(true);
        img_background = new ImageIcon("images/background.jpg").getImage();

        // Pannel du du titre + bouton
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        retourB.setPreferredSize(new Dimension(100, 50));
        retourB.addActionListener(this);

        JLabel title = new JLabel("<html><font color='CC0000'>Choix du livre</font></html>",
                SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));

        EcranMenu.createEmptySpace(-75, title, "left");
        // Sert a décaler le titre vers la gauche pour le "recentrer"

        headerPanel.add(retourB, BorderLayout.WEST);
        headerPanel.add(title, BorderLayout.CENTER);

        // Pannel du contenu
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(400, 250));

        EcranMenu.createEmptySpace(100, contentPanel, "top");

        b1.addActionListener(this);
        b2.addActionListener(this);

        b1.setPreferredSize(new Dimension(200, 200));
        b1.setMinimumSize(new Dimension(200, 100));

        b2.setPreferredSize(new Dimension(200, 200));
        b2.setMinimumSize(new Dimension(200, 100));

        contentPanel.add(b1);
        contentPanel.add(b2);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.revalidate();
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

        if (source == b1) {
            System.out.println("Choix effectué");
            MethodesLancement launcher = new MethodesLancement(frame, menu, "json");
            new GameControlleur(launcher, frame);
        }

        if (source == b2) {
            System.out.println("Choix effectué");
            MethodesLancement launcher = new MethodesLancement(frame, menu, "txt");
            new GameControlleur(launcher, frame);
        }
    }
}
