package livre;

public class Choix {
    private String intitule;
    private int suivant;
    private boolean requireAbility;

    public Choix(String intitule, int suivant) {
        this.intitule = intitule;
        this.suivant = suivant;
        this.requireAbility = false;
    }

    public String getIntitule() {
        return this.intitule.replace("\n", " ");
    }

    public int getSuivant() {
        return this.suivant;
    }

    public boolean getRequireAbility() {
        return this.requireAbility;
    }

    public boolean setRequireAbility (boolean monBool){
        return this.requireAbility = monBool ;
    }
}