package livre;

import java.util.ArrayList;

public class Page {
    private int section;
    private String texte;
    private ArrayList<Choix> choix;
    private boolean goodEnding;
    private boolean isFight;
    private boolean modifieInventaire;
    private boolean modifieStats;

    public Page(int section, String texte) {
        this.section = section;
        this.texte = texte;
        this.choix = new ArrayList<Choix>();
        this.isFight = false;
        this.modifieInventaire = false;
        this.modifieStats = false;
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

    public void setIsFight(boolean setter) {
        this.isFight = setter;
    }

    public boolean isFight() {
        return this.isFight;
    }

    public void setModifieInventaire() {
        this.modifieInventaire = true;
    }

    public boolean modifieInventaire() {
        return this.modifieInventaire;
    }

    public void setModifieStats() {
        this.modifieStats = true;
    }

    public boolean modifieStats() {
        return this.modifieStats;
    }

    public String toString(){
        return "" + this.getSection();
    }
}
