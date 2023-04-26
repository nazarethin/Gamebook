package livre;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Choix {
    private String intitule;
    private int suivant;
    private boolean requireAbility;
    private ArrayList<Page> livre;


    protected String nom;
    protected String description;
    protected int abilityValue;
    protected int healthPoints;
    protected String abilityName;






    public Choix(String intitule, int suivant) {
        this.intitule = intitule;
        this.suivant = suivant;
        this.requireAbility = false;  


        this.nom = nom;
        this.description = description;
        this.abilityValue = abilityValue;
        this.healthPoints = healthPoints;
        this.abilityName = abilityName;
    }
    public String getIntitule() {
        return this.intitule.replace("\n", " ");
    }

    public int getSuivant() {
        return this.suivant;
    }



     public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getAbilityValue() {
        return abilityValue;
    }

    public void setAbilityValue(int abilityValue) {
        this.abilityValue = abilityValue;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
  

    public Page getNextPage(ArrayList<Page> pages) {
        // Get the ID of the next page based on the given choice
        int nextPageId = this.suivant;

        // Find the Page object with the corresponding ID
        for (Page page : pages) {
            if (page.getSection() == nextPageId) {
                return page;
            }
        }

        // If no Page object is found with the given ID, return null
        return null;
    }


    public boolean getRequireAbility() {
        return this.requireAbility;
    }

    public boolean setRequireAbility (boolean monBool){
        return this.requireAbility = monBool ;
    }
}