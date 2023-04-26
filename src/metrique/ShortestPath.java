package metrique;

import livre.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Stack;

public class ShortestPath {


    protected ArrayList<Page> livre;
    protected Lecteur monLecteur;
    protected ArrayList<Choix> choix;
    protected Page startPage;
    protected Page victoryPage;
    protected List<Page> shortToVic;
    protected List<Page> shortToDeath;

    public ShortestPath(Lecteur monLecteur) {
        this.monLecteur = monLecteur;
        this.livre = monLecteur.getLivre();
        this.choix = new ArrayList<Choix>();
        this.startPage = livre.get(1); // On choisit la deuxième page comme page de départ
        this.victoryPage = livre.get(livre.size() - 1);// On choisit la dernière page comme page de la victoire
        this.shortToVic = ShortestPathToVictory(startPage, victoryPage);
        this.shortToDeath = ShortestPathToDeath(startPage, livre);
    }

public String shortToVicToString(){
    String res = "";
    for (int i = 1; i < this.shortToVic.size() -1; i++){
        res += this.shortToVic.get(i-1) + " > ";        
    }
    res = res.substring(0, res.length() - 2);
    return res;
} 

public List<Page> getShortToVic(){
    return this.shortToVic;
}

public String getShortToDeathToString(){
    String res = "";
    for (int i = 0; i < this.shortToDeath.size() -1; i++){
        res += this.shortToDeath.get(i) + " > ";        
    }
    res = res.substring(0, res.length() - 2);
    return res;
}

public List<Page> getShortToDeath(){
    return this.shortToDeath;
}

// Méthode qui renvoie le chemin le plus court entre une page de départ et une page de victoire
public List<Page> ShortestPathToVictory(Page startPage, Page victoryPage) {

    List<Page> visitedPages = new ArrayList<>();// Liste des pages visitées

    Queue<List<Page>> pathsQueue = new LinkedList<>();// File des chemins possibles

    pathsQueue.add(Collections.singletonList(startPage)); // Ajout de la page de départ

    boolean victoryReached = false;

    List<Page> shortestPath = new ArrayList<>();

    // Tant qu'il reste des chemins à explorer et que la page de fin victorieuse n'a pas été atteinte
    while (!pathsQueue.isEmpty() && !victoryReached) {
        List<Page> currentPath = pathsQueue.remove();
        Page currentPage = currentPath.get(currentPath.size() - 1);
        if (currentPage == victoryPage) {
            victoryReached = true;
            shortestPath = currentPath;
        } else {// Sinon, on continue d'explorer les choix possibles à partir de la page courante
            List<Choix> choices = currentPage.getChoix();
            for (Choix choix : choices) {
                Page nextPage = choix.getNextPage(livre);
                if (!visitedPages.contains(nextPage)) {
                    List<Page> newPath = new ArrayList<>(currentPath);// On crée un nouveau chemin à partir du chemin courant
                    newPath.add(nextPage);// On ajoute la page suivante au nouveau chemin
                    pathsQueue.add(newPath);// On ajoute le nouveau chemin à la file des chemins possibles
                    visitedPages.add(nextPage);// On marque la page suivante comme visitée
                }
            }
        }

    }
    if(shortestPath.isEmpty())
    {
        System.out.println("No path found from start page to victory page");
    }
    
    return shortestPath;

     
}



public List<Page> ShortestPathToDeath(Page startPage, ArrayList<Page> livre) {
    List<Page> visitedPages = new ArrayList<>();
    Queue<List<Page>> pathsQueue = new LinkedList<>();
    pathsQueue.add(Collections.singletonList(startPage));
    boolean deathReached = false;
    List<Page> shortestPath = new ArrayList<>();
    while (!pathsQueue.isEmpty() && !deathReached) {
        List<Page> currentPath = pathsQueue.remove();
        Page currentPage = currentPath.get(currentPath.size() - 1);
        if (currentPage.isEnding() && !currentPage.getGoodEnding()) { // Vérifier si la page actuelle est une page de mort
            deathReached = true;
            shortestPath = currentPath;
        } else {
            List<Choix> choices = currentPage.getChoix();
            for (Choix choix : choices) {
                Page nextPage = choix.getNextPage(livre);
                if (!visitedPages.contains(nextPage)) {
                    List<Page> newPath = new ArrayList<>(currentPath);
                    newPath.add(nextPage);
                    pathsQueue.add(newPath);
                    visitedPages.add(nextPage);
                }
            }
        }

    }
    if(shortestPath.isEmpty())
    {
        System.out.println("No path found from start page to death page");
    }
    
    return shortestPath;
    
}




}
