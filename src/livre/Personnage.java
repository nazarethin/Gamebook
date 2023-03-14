package livre;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Personnage {
    protected int argent;
    protected int combatSkill;
    protected int endurance; 
    protected int chance;
    protected ArrayList<String> kaiDisciplines;
    protected Map<String, Integer> inventaire;
    
    
    public Personnage(){
        this.argent = 0;
        this.combatSkill = 10 ;
        this.endurance = 10 ;
        this.chance = 0;
        this.kaiDisciplines = new ArrayList<String>();
        this.inventaire = new HashMap<>();
    }

    public int getArgent(){
        return this.argent;
    }
    
    /* Ajout servira a la fois a ajouter et retirer de l'argent.
    Il suffira d'ajouter une valeur négative dans le cas ou ont veut retirer de l'argent. */
    public void setArgent(int ajout){
        this.argent += ajout;
    }

    public int getCombatSkill(){
        return this.combatSkill;
    }

    public void setCombatSkill(int ajout){
        this.combatSkill += ajout ;
    }

    public int getEndurance(){
        return this.endurance;
    }

    public void setEndurance(int ajout){
        this.endurance += ajout;
    }

    public int getChance(){
        return this.chance;
    }

    public void setChance(int ajout){
        this.chance += ajout;
    }

    public ArrayList<String> getKaiDisciplines(){
        return this.kaiDisciplines;
    }

    public void addKaiDisciplines(String newDisciplines){
        this.kaiDisciplines.add(newDisciplines);
    }

    public void removeKaiDisciplines(String formerDisciplines){
        this.kaiDisciplines.remove(formerDisciplines);
    }

    public Map<String, Integer> getInventaire(){
        return this.inventaire;
    }

    public void addInventaire(String newObject){
        if (inventaire.containsKey(newObject)) {
            int ancienneQuantite = inventaire.get(newObject);
            inventaire.put(newObject, ancienneQuantite + 1);
        } else {
            inventaire.put(newObject, 1);
        }
    } 

    public void removeInventaire(String formerObject, int quantite) {
        if (inventaire.containsKey(formerObject)) {
            int ancienneQuantite = inventaire.get(formerObject);
            if (quantite >= ancienneQuantite) {
                inventaire.remove(formerObject);
            } else {
                inventaire.put(formerObject, ancienneQuantite - quantite);
            }
            System.out.println(quantite + " " + formerObject + " ont été retirés de l'inventaire.");
        } else {
            System.out.println(formerObject + " n'est pas présent dans l'inventaire.");
        }
    }

    public String toString() {
        return
        "\n Or : "+getArgent()+
        "\n Combat skill :"+getCombatSkill()+
        "\n Endurance :"+getEndurance()+
        "\n Kai Disiplines : " + getKaiDisciplines()+
        "\n Inventaire :" + getInventaire();
    };

    public void changeStats(String statToChange, Integer valeur){
        if (statToChange.equals("habilete")) {
            setCombatSkill(valeur);
        }
        else if (statToChange.equals("endurance")) {
            setEndurance(valeur);
        }
        else if (statToChange.equals("chance")){
            setChance(valeur);
        }
        else {
            System.out.println("La stat : \"" + statToChange + "\" de valeur : " + valeur + " n'est pas valide\n" );
        }
    }

    public boolean containAbility(String maString) {
        ArrayList<String> tmp = this.getKaiDisciplines();
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).equals(maString)) {
                return true;
            }
        }
        return false;
    }
}
