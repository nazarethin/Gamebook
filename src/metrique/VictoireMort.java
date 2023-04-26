package metrique;
import livre.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class VictoireMort{
	protected int nbMorts;
	protected int nbWins;
	protected int nbCombats;
	protected int nbFins;
	protected Lecteur monLecteur;
	ArrayList<Page> livre;
	protected Page mesChoix;
	ArrayList<Choix> choix;
	
	public VictoireMort(Lecteur monLecteur){
		this.nbMorts = 0;
		this.nbWins = 0;
		this.nbCombats = 0;
		this.nbFins = 0;
		this.monLecteur = monLecteur;
		this.monLecteur.createLivre();
		this.livre = this.monLecteur.getLivre();
		nbFins();
		wins();
		nbMorts();
		nbCombats();
	}

	public int getNbCombats() {
		return this.nbCombats;
	}
	
	public int getNbWins() {
		return this.nbWins;
	}
	
	public int getNbFins(){
		return this.nbFins;
	}
	
	public int getNbMorts(){
		return this.nbMorts=nbFins-nbWins;
	}
	
	//CALCUL DES NOMBRES DES FINS
	public void nbFins(){
		for (int i=0; i<this.livre.size(); i++){
			if(this.livre.get(i).isEnding()){
				this.nbFins++;
			}		
		}	
	}
	//CALCUL DES NOMBRES DES MORTS
	public void nbMorts(){
		for(int i=0; i<nbMorts; i++){
			nbMorts++;
		}
	}
	//CALCUL DES NOMBRES DES VICTOIRES
	public void wins(){
		for(int i=0; i<this.livre.size(); i++){
			if(this.livre.get(i).getGoodEnding()){
				this.nbWins++;
			}
		}
	}
	//CALCUL DES NOMBRES DES COMBATS
	public void nbCombats(){
		for(int i=0; i<this.livre.size(); i++){
			if(this.livre.get(i).isFight()){
				this.nbCombats++;
			}
		}
	}
	
	
}

