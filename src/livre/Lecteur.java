package livre;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Lecteur {

    public Lecteur() {
    }

    public abstract Page createPage(int numSection);
    public abstract Page createIntro();
    public abstract Page createSetup();
    public abstract ArrayList<Page> getLivre();
    public abstract void createLivre();
}