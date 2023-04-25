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

public class LongestPath {


    protected ArrayList<Page> livre;
    protected Lecteur monLecteur;
    protected ArrayList<Choix> choix;
    protected Page startPage;
    protected Page victoryPage;
    protected List<Page> longestPathVic;
    protected List<Page> longestPathDeath;

    public LongestPath(Lecteur monLecteur) {
        this.monLecteur = monLecteur;
        this.monLecteur.createLivre();
        this.livre = monLecteur.getLivre();
        this.choix = new ArrayList<Choix>();
        this.startPage = livre.get(1);
        this.victoryPage = livre.get(livre.size() - 1);
        this.longestPathVic = longestPathToVictory(startPage, victoryPage);
        this.longestPathDeath = longestPathToDeath(startPage, livre);
    }

    public List<Page> getLongestVic(){
        return this.longestPathVic;
    }

    public List<Page> getLongestDeath(){
        return this.longestPathDeath;
    }

    

    // Renvoie le plus long chemin menant à la page de victoire
    public List<Page> longestPathToVictory(Page startPage, Page victoryPage) {

    List<Page> visitedPages = new ArrayList<>();

    Stack<List<Page>> pathsStack = new Stack<>();// Pile des chemins à explorer

    pathsStack.push(Collections.singletonList(startPage));// Ajoute le chemin de départ à la pile

    List<Page> longestPath = new ArrayList<>();

    // Tant qu'il reste des chemins à explorer
    while (!pathsStack.isEmpty()) {

        List<Page> currentPath = pathsStack.pop(); // Récupère le chemin courant depuis la pile

        Page currentPage = currentPath.get(currentPath.size() - 1); // Récupère la dernière page du chemin courant

         // Si la dernière page est la page de victoire et que le chemin est plus long que le plus long chemin trouvé jusqu'à présent
        if (currentPage == victoryPage && currentPath.size() > longestPath.size()) {
            longestPath = currentPath;
        }
        List<Choix> choices = currentPage.getChoix();

        // Pour chaque choix, ajoute la page suivante à la pile si elle n'a pas été visitée
        for (Choix choix : choices) {
            Page nextPage = choix.getNextPage(livre);
            if (!visitedPages.contains(nextPage)) {
                List<Page> newPath = new ArrayList<>(currentPath);
                newPath.add(nextPage);
                pathsStack.push(newPath);
                visitedPages.add(nextPage);
            }
        }
    }
    if (longestPath.isEmpty()) {
        System.out.println("No path found from start page to victory page");
    }
    return longestPath;
}




public List<Page> longestPathToDeath(Page startPage, ArrayList<Page> livre) {
    List<Page> visitedPages = new ArrayList<>();
    Stack<List<Page>> pathsStack = new Stack<>();
    pathsStack.push(Collections.singletonList(startPage));
    List<Page> longestPath = new ArrayList<>();
    while (!pathsStack.isEmpty()) {
        List<Page> currentPath = pathsStack.pop();
        Page currentPage = currentPath.get(currentPath.size() - 1);
        if (currentPage.isEnding() && !currentPage.getGoodEnding()) { // Vérifier si la page actuelle est une page de mort
            if (currentPath.size() > longestPath.size()) {
                longestPath = currentPath;
            }
        } else {
            List<Choix> choices = currentPage.getChoix();
            for (int i = choices.size() - 1; i >= 0; i--) { // Parcourir les choix dans l'ordre inverse
                Page nextPage = choices.get(i).getNextPage(livre);
                if (!visitedPages.contains(nextPage)) {
                    List<Page> newPath = new ArrayList<>(currentPath);
                    newPath.add(nextPage);
                    pathsStack.push(newPath);
                    visitedPages.add(nextPage);
                }
            }
        }
    }
    if (longestPath.isEmpty()) {
        System.out.println("No path found from start page to death page");
    }
    return longestPath;
}

}