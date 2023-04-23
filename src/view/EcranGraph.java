package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class EcranGraph extends JPanel implements ActionListener {

    private Image img_background;

    private Menu frame;
    private EcranMenu menu;
    private JButton retourB = new JButton("Retour");

    public EcranGraph(Menu frame, EcranMenu menu) {

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

        JLabel title = new JLabel("<html><font color='CC0000'>Graphique</font></html>", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));

        Insets insets = title.getInsets();
        insets.left = -75;
        title.setBorder(new EmptyBorder(insets));// Sert a décaler le titre vers la gauche pour le "recentrer"

        headerPanel.add(retourB, BorderLayout.WEST);
        headerPanel.add(title, BorderLayout.CENTER);

        // Pannel du contenu
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(400, 250)); // Dimension ajustable choisir de maniere arbitraire
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
            System.out.println("Retour menu");
            this.frame.set(menu);
        }
    }
}