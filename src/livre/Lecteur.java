package livre;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

public abstract class Lecteur {

    public Lecteur() {
    }

    public abstract Page createPage(int numSection);

    public abstract Page createIntro();

    public abstract Page createSetup(Personnage monPerso);

    public abstract ArrayList<Page> getLivre();

    public abstract void createLivre();

    public abstract ArrayList<String> itemToAdd(String section, Page maPage);

    public abstract ArrayList<Map<Integer, String>> statsToChange(String section, Page maPage);
}