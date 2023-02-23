package livre;

import java.util.ArrayList;

public class Page {
    private int section;
    private String texte;
    private ArrayList<Choix> choix;
    private boolean goodEnding;

    public Page(int section, String texte) {
        this.section = section;
        this.texte = texte;
        this.choix = new ArrayList<Choix>();
    }

    public int getSection() {
        return this.section;
    }

    public String getTexte() {
        return this.texte;
    }

    public ArrayList<Choix> getChoix() {
        return this.choix;
    }

    public void ajouterChoix(Choix monChoix) {
        this.choix.add(monChoix);
    }

    public boolean getGoodEnding() {
        return this.goodEnding;
    }

    public boolean setGoodEnding(boolean monBool) {
        return this.goodEnding = monBool;
    }

    public boolean isEnding() {
        return choix.isEmpty();
    }
}