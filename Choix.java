package livre;

public class Choix {
    private String intitule;
    private int suivant;

    public Choix(String intitule, int suivant){
        this.intitule = intitule;
        this.suivant = suivant;
    }

    public String getIntitule(){
        return this.intitule;
    }

    public int getSuivant(){
        return this.suivant;
    }
}