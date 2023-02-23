package livre;
import java.util.ArrayList;

public class Personnage {
    protected String nom;
    protected int argent;
    protected int combatSkill;
    protected int endurance; 
    protected ArrayList<String> kaiDisciplines;
    protected ArrayList<String> inventaire;
    
    
    public Personnage(){
        this.nom="";
        this.argent = 0;
        this.combatSkill = 1 ;
        this.endurance = 1 ;
        this.kaiDisciplines = new ArrayList<String>();
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String newNom){
        this.nom = newNom;
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

    public ArrayList<String> getKaiDisciplines(){
        return this.kaiDisciplines;
    }

    public void addKaiDisciplines(String newDisciplines){
        this.kaiDisciplines.add(newDisciplines);
    }

    public void removeKaiDisciplines(String formerDisciplines){
        this.kaiDisciplines.remove(formerDisciplines);
    }

    public ArrayList<String> getInventaire(){
        return this.inventaire;
    }

    public void addInventaire(String newObject){
        this.inventaire.add(newObject);
    } 

    public void removeInventaire(String formerObject){
        this.inventaire.remove(formerObject);
    }

    public String toString() {
        return
        "Vous êtes : "+getNom()+
        "\n Or : "+getArgent()+
        "\n Combat skill :"+getCombatSkill()+
        "\n Endurance :"+getEndurance()+
        "\n Kai Disiplines : " + getKaiDisciplines()+
        "\n Inventaire :" + getInventaire();
    };
}