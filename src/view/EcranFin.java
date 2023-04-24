package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class EcranFin extends JPanel implements ActionListener {

    private Image img_background;

    private Menu frame;
    private EcranMenu menu;
    private JButton retourB = new JButton("Retour");

    public EcranFin(Menu frame, EcranMenu menu) {

        super(new BorderLayout());
        this.frame = frame;
        this.menu = menu;
        this.setOpaque(true);
        img_background = new ImageIcon("images/background.jpg").getImage();

        // Pannel du du titre + bouton
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        retourB.setPreferredSize(new Dimension(100, 50));
        retourB.addActionListener(this);

        headerPanel.add(retourB, BorderLayout.WEST);

        // Pannel du contenu
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(400, 250)); // Dimension ajustable choisir de maniere arbitraire
        // contentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel endLabel = new JLabel("<html><font color='CC0000'>Felicitations</font></html>", SwingConstants.CENTER);
        endLabel.setFont(new Font("Arial", Font.BOLD, 80));
        JLabel commentaire = new JLabel(
                "vous avez fini cette aventure bravo ! \n Vous pouvez retournez au menu principal à l'aide du bouton retour en haut a gauche");
        EcranMenu.createEmptySpace(200, endLabel, "top");
        contentPanel.add(endLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        /*
         * File f = new File("analyseur-ldveh-bbn/images");
         * System.out.println("Chemin absolu : " + f.getAbsolutePath());
         * System.out.println(f.exists());
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
        if (source == retourB) {
            System.out.println("Retour menu");
            this.frame.set(menu);
        }
    }
}