package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

import livre.*;
import metrique.*;

public class EcranMetrique extends JPanel implements ActionListener {

    private Image img_background;

    private Menu frame;
    private EcranMenu menu;
    private Lecteur lecteur;
    private JButton retourB = new JButton("Retour");

    public EcranMetrique(Menu frame, EcranMenu menu, Lecteur lecteur) {

        super(new BorderLayout());
        this.frame = frame;
        this.menu = menu;
        this.lecteur = lecteur;
        this.setOpaque(true);
        img_background = new ImageIcon("images/background.jpg").getImage();

        // Pannel du du titre + bouton
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        retourB.setPreferredSize(new Dimension(100, 50));
        retourB.addActionListener(this);

        JLabel title = new JLabel("<html><font color='CC0000'>Métrique</font></html>", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));

        EcranMenu.createEmptySpace(-75, title, "left");

        headerPanel.add(retourB, BorderLayout.WEST);
        headerPanel.add(title, BorderLayout.CENTER);

        // Pannel du contenu
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(400, 250)); // Dimension ajustable choisir de maniere arbitraire
        //LongestPath lg = new LongestPath
        LongestPath lp = new LongestPath(lecteur);
        ShortestPath sp = new ShortestPath(lecteur);
        VictoireMort vm = new VictoireMort(lecteur);
        int nbVictoires = vm.getNbWins();
        int nbFins = vm.getNbFins();
        int nbMorts = vm.getNbMorts();
        int nbCombat = vm.getNbCombats();

        int cheminPlusCourtVictory = sp.getShortToVic().size();
        int cheminPlusCourtMort = sp.getShortToDeath().size();
        String cheminRapideVic = sp.shortToVicToString();
        String cheminRapideMort = sp.getShortToDeathToString();

        int cheminPlusLongVictory = lp.getLongestVic().size();
        int cheminPlusLongDeath = lp.getLongestDeath().size();
        JLabel texte = new JLabel(
                "<html>Nombre de Victoires : " + nbVictoires +
                        "<br>Nombre de fins : " + nbFins +
                        "<br>Nombre de pages de mort : " + nbMorts +
                        "<br>Nombre de pages de combat : " + nbCombat +
                        "<br>" + 

                        "<br>Nombre de pages du chemin le plus court vers une victoire : " + cheminPlusCourtVictory +
                        "<br>Chemin le plus rapide vers une victoire : " /*+ cheminRapideVic */+
                        "<br>" + 

                        "<br>Nombre de pages du chemin le plus court vers une mort : " + cheminPlusCourtMort +
                        "<br>Chemin le plus rapide vers une mort : " + cheminRapideMort +
                        "<br>" + 

                        "<br>Nombre de pages du chemin le plus long vers une victoire : " + cheminPlusLongVictory +
                        "<br>Nombre de pages du chemin le plus long vers une mort : " + cheminPlusLongDeath +
                        "</html>",
                SwingConstants.CENTER);
        texte.setFont(new Font("Arial", Font.BOLD, 20));
        contentPanel.add(texte);
        // contentPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

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
}