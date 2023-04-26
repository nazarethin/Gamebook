package experimentations;

import livre.*;
import metrique.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Comparator;

public class WeightCalculator {
    protected Lecteur monLecteur;
    protected ArrayList<Page> livre;
    public WeightCalculator(Lecteur monLecteur){
        this.monLecteur = monLecteur;
		this.livre = this.monLecteur.getLivre();
    }
    public int getWeight(Page livre) {
        VictoireMort vm = new VictoireMort(monLecteur);
        int weight = 0;

        for(int i=0; i<this.livre.size(); i++){
			if(this.livre.get(i).getGoodEnding()){
				weight = 1;
			}
            //else {
            //weight = vm.getNbCombats() + 1;
            //}
        }
         
        return weight;
    }
    
}
