package util;

import java.awt.Frame;
import javax.swing.SwingWorker;
import javax.swing.JPanel;

import livre.*;
import view.*;

public class GameControlleur implements Listener {

    private MethodesLancement launcher;
    private Page pageActuelle;
    private int nbChoix;
    private EcranJeu ecranJeu;

    public GameControlleur(MethodesLancement launcher, Menu frame) {
        this.launcher = launcher;
        this.pageActuelle = null/* launcher.createPage(1) */;
        this.nbChoix = 2;
        EcranMenu menu = new EcranMenu(frame);
        ecranJeu = new EcranJeu(frame, menu, "json", this.nbChoix);
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
        this.pageActuelle = launcher.getPageActuelle();
        this.nbChoix = launcher.getNbChoix();
        ecranJeu.setNbChoix(this.nbChoix);
    }
}
