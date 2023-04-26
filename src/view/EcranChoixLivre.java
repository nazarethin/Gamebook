package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.BorderFactory;

import util.*;
import livre.*;

public class EcranChoixLivre extends JPanel implements ActionListener {

    private Menu frame;
    private EcranMenu menu;
    private String nextPanel;
    private JButton retourB = new JButton("Retour");

    private JButton b1 = new JButton("<html>FotW <br>(JSON)</html>");
    private JButton b2 = new JButton("<html>Le labyrinthe de la mort <br>(Txt)</html>");
    private Image img_background;

    public EcranChoixLivre(Menu frame, EcranMenu menu, String nextPanel) {
        super(new BorderLayout());
        this.frame = frame;
        this.menu = menu;
        this.nextPanel = nextPanel;

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
            this.frame.set(menu);
        }

        if (source == b1) { //LIVRE JSON
            MethodesLancement launcher = new MethodesLancement(frame, menu, "json");
            if (this.nextPanel == "livre"){
                new GameControlleur(launcher, frame);
            }
            else if (this.nextPanel == "metrique"){
                Lecteur lecteur = launcher.getLecteur();
                this.frame.set(new EcranMetrique(frame, menu, lecteur));
            }
            
        }

        if (source == b2) { //LIVRE TXT
            MethodesLancement launcher = new MethodesLancement(frame, menu, "txt");
            if (this.nextPanel == "livre"){
                new GameControlleur(launcher, frame);
            }
            else if (this.nextPanel == "metrique"){
                Lecteur lecteur = launcher.getLecteur();
                this.frame.set(new EcranMetrique(frame, menu, lecteur));
            }
        }
    }
}
