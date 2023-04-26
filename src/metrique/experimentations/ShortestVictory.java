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


public class ShortestVictory {


    protected ArrayList<Page> livre;
    protected Lecteur monLecteur;
    protected ArrayList<Choix> choix;
    protected Page startPage;
    protected Page victoryPage;

    public ShortestVictory(Lecteur monLecteur) {
        this.monLecteur = monLecteur;
        this.livre = monLecteur.getLivre();
        this.choix = new ArrayList<Choix>();
        this.startPage = livre.get(1);
        this.victoryPage = livre.get(livre.size() - 1);
    }



            /*CALCUL DU CHEMIN LE PLUS COURT EN UTILISANT l'ALGORITHME DE DIJKSTRA*/



//Méthode qui renvoie le chemin le plus court avec le moins de combats entre une page de départ et une page de victoire
public List<Page> shortestPathWithLeastCombats(Page source, Page destination) {
    WeightCalculator calculator = new WeightCalculator(monLecteur);
    Map<Page, Integer> distances = new HashMap<>();
    Map<Page, Page> parents = new HashMap<>();
    Set<Page> visited = new HashSet<>();

    PriorityQueue<Page> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
    queue.add(source);
    distances.put(source, 0);

    while (!queue.isEmpty()) {
        Page current = queue.poll();
        visited.add(current);

        if (current.equals(destination)) {
            break;
        }

            List<Choix> choices = current.getChoix();
            for (Choix choix : choices) {
                Page neighbor = choix.getNextPage(livre);

            if (!visited.contains(neighbor)) {
                int weight = calculator.getWeight(current) + calculator.getWeight(neighbor);
                int distance = distances.getOrDefault(current, Integer.MAX_VALUE) + weight;

                if (distance < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distances.put(neighbor, distance);
                    parents.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    List<Page> path = new ArrayList<>();
    Page current = destination;
    while (current != null) {
        path.add(0, current);
        current = parents.get(current);
    }

    return path;
}






public List<Page> findLongestPath() {
    // Initialisation des variables
    List<Page> longestPath = null; // le plus long chemin trouvé jusqu'à présent
    Set<Page> visited = new HashSet<>(); // ensemble de pages visitées

    // Pour chaque page non visitée, démarrer une nouvelle traversée DFS
     List<Choix> choices = currentPath.getChoix();
    for ( Choix choix : choices) {
        Page neighbor = choix.getNextPage(livre);
        if (!visited.contains(page)) {
            List<Page> currentPath = new ArrayList<>(); // chemin actuel parcouru
            int currentLength = 0; // longueur actuelle du chemin

            // Démarrer la traversée DFS à partir de la page actuelle
            dfs(page, currentPath, currentLength, visited);

            // Vérifier si le chemin actuel est plus long que le plus long chemin trouvé jusqu'à présent
            if (longestPath == null || currentPath.size() > longestPath.size()) {
                longestPath = currentPath;
            }
        }
    }

    // Renvoie le chemin le plus long trouvé
    return longestPath;
}

private void dfs(Page page, List<Page> currentPath, int currentLength, Set<Page> visited) {
    // Marquer la page actuelle comme visitée et l'ajouter au chemin actuel
    visited.add(page);
    currentPath.add(page);

    // Mettre à jour la longueur actuelle du chemin
    currentLength += page.getWeight();

    // Visiter récursivement chaque voisin non visité
    List<Choix> choices = currentPath.getChoix();
    for ( Choix choix : choices) {
        Page neighbor = choix.getNextPage(livre);
        if (!visited.contains(neighbor)) {
            dfs(neighbor, currentPath, currentLength, visited);
            break; // ne visiter que le premier voisin non visité
        }
    }

    // Vérifier si le chemin actuel est plus long que le plus long chemin trouvé jusqu'à présent
    if (currentPath.size() > longestPath.size()) {
        longestPath = new ArrayList<>(currentPath);
    }

    // Supprimer la page actuelle du chemin actuel et décrémenter la longueur actuelle du chemin
    currentPath.remove(currentPath.size() - 1);
    currentLength -= page.getWeight();

}




}