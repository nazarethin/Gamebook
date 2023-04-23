package util;

import java.awt.Frame;
import javax.swing.SwingWorker;
import javax.swing.JPanel;

import livre.*;
import view.*;

public class GameControlleur implements Listener {

    private MethodesLancement launcher;
    private Page pageActuelle;
    private EcranJeu ecranJeu;

    public GameControlleur(MethodesLancement launcher, Menu frame) {
        this.launcher = launcher;
        this.pageActuelle = null/* launcher.createPage(1) */;
        EcranMenu menu = new EcranMenu(frame);
        ecranJeu = new EcranJeu(frame, menu, "json");
        frame.set(ecranJeu);
        launcher.addListener(this);
        launcher.addListener(ecranJeu);

        Thread t = new Thread(() -> {
            launcher.jeu();
        });
        t.start();
    }

    @Override
    public void update(Object o) {
        System.out.println("Update gamecontrolleur");
        this.pageActuelle = launcher.getPageActuelle();
    }
}
