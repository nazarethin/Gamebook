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
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

public class Probability {


    protected ArrayList<Page> livre;
    protected Lecteur monLecteur;
    protected ArrayList<Choix> choix;
    protected Page startPage;
    protected Page victoryPage;
    protected ArrayList<String> kaiDisciplines;

    public Probability(Lecteur monLecteur) {
        this.monLecteur = monLecteur;
        this.livre = monLecteur.getLivre();
        this.choix = new ArrayList<Choix>();
        this.startPage = livre.get(1);
        this.victoryPage = livre.get(livre.size() - 1);
    }

/*
public double[] calculateProbabilitiesOfEndings(Page startPage) {
    ArrayList<ArrayList<Choix>> allPaths = getAllPaths(startPage);
    double[] totalProbabilities = new double[3];
    for (ArrayList<Choix> path : allPaths) {
        double pathProbability = 1.0;
        boolean reachedEnding = false;
        boolean reachedGoodEnding = false;
        boolean reachedDeath = false;
        for (Choix choix : path) {
            pathProbability *= choix.getProbability();
            Page nextPage = choix.getNextPage(livre);
            if (nextPage.isEnding()) {
                reachedEnding = true;
                if (nextPage.getGoodEnding()) {
                    reachedGoodEnding = true;
                }
            }
            if (nextPage.isEnding() && !nextPage.getGoodEnding()) {
                reachedDeath = true;
            }
        }
        if (reachedEnding) {
            totalProbabilities[0] += pathProbability;
            if (reachedGoodEnding) {
                totalProbabilities[1] += pathProbability;
            }
        } else {
            totalProbabilities[2] += pathProbability;
        }
    }
    for (int i = 0; i < 3; i++) {
        totalProbabilities[i] /= allPaths.size();
    }
    return totalProbabilities;
}

public ArrayList<ArrayList<Choix>> getAllPaths(Page currentPage) {
    ArrayList<ArrayList<Choix>> allPaths = new ArrayList<ArrayList<Choix>>();
    for (Choix choix : currentPage.getChoix()) {
        ArrayList<ArrayList<Choix>> subPaths = getAllPaths(choix.getNextPage(livre));
        for (ArrayList<Choix> subPath : subPaths) {
            ArrayList<Choix> newPath = new ArrayList<Choix>();
            newPath.add(choix);
            newPath.addAll(subPath);
            allPaths.add(newPath);
        }
    }
    if (allPaths.isEmpty()) {
        ArrayList<Choix> baseCase = new ArrayList<Choix>();
        allPaths.add(baseCase);
    }
    return allPaths;
}
*/
/*
public void calculateProbabilities() {
    System.out.println("Probability of victory for each choice:");

    // Iterate over each choice in the choix ArrayList
    for (Choix choix : choix) {
        // Check if the choice requires an ability
        if (choix.getRequireAbility()) {
            // If so, print a message indicating that the choice requires an ability
            System.out.println(choix.getIntitule() + " (requires ability)");
        } else {
            // Otherwise, calculate the probability of victory for the choice
            int numPages = livre.size();
            int numVictories = 0;

            // Iterate over each page in the livre ArrayList
            for (Page page : livre) {
                // Check if the page has the current choice
                if (page.hasChoix(choix)) {
                    // If so, check if the page is a good ending
                    if (page.getGoodEnding()) {
                        // If so, increment the number of victories
                        numVictories++;
                    }
                }
            }

            // Calculate the probability of victory
            double probability = (double) numVictories / (double) numPages;

            // Print a message indicating the probability of victory for the choice
            System.out.println(choix.getIntitule() + " (" + probability + ")");
        }
    }
}
*/
/*
public static void calculateProbabilities(ArrayList<Page> pages) {
    System.out.println("Probability of victory for each choice:");
    for (Page page : pages) {
        boolean hasAbilityChoices = false; // Keep track of whether there are any choices that require ability
        for (Choix choix : page.getChoix()) {
            if(choix.getRequireAbility()){
                hasAbilityChoices = true;
                Page nextPage = choix.getNextPage(pages);
                double proba = 0.0;
                if (nextPage != null && nextPage.getGoodEnding()) {
                    proba = 1.0 / page.getChoix().size();
                }
                System.out.println("Page " + page.getSection() + " - Choice " + choix.getIntitule() + "(requires ability): " + proba);
            }
        }
        if (!hasAbilityChoices) {
            System.out.println("Page " + page.getSection() + " - No choices that require ability.");
        }
    }
}
*/
public static double victoryProbability(Personnage joueur, Choix choix) {

        int playerCombatSkill = joueur.getCombatSkill();
        int playerEndurance = joueur.getEndurance();
        int playerChance = joueur.getChance();
        boolean playerHasWeapon = joueur.containAbility("Arme");
        boolean playerHasMindShield = joueur.containAbility("Bouclier mental");

        int ennemyCombatSkill = choix.getAbilityValue();
        int ennemyEndurance = choix.getHealthPoints();
        String ennemySpecialAbility = choix.getAbilityName();

        double playerEffectiveCombatSkill = playerCombatSkill;
        double ennemyEffectiveCombatSkill = ennemyCombatSkill;

        if (playerHasWeapon) {
            playerEffectiveCombatSkill += 2;
        }
        if (ennemySpecialAbility.equals("Illusion")) {
            ennemyEffectiveCombatSkill /= 2;
        }
        if (playerHasMindShield && ennemySpecialAbility.equals("Psionique")) {
            ennemyEffectiveCombatSkill /= 2;
        }

        double playerExpectedDamagePerTurn = Math.max((playerEffectiveCombatSkill - ennemyEndurance) / 6.0, 0);
        double ennemyExpectedDamagePerTurn = Math.max((ennemyEffectiveCombatSkill - playerEndurance) / 6.0, 0);

        double playerWinProbability = (1 - Math.exp(-ennemyEndurance / playerExpectedDamagePerTurn)) * (1 - Math.exp(-playerEndurance / ennemyExpectedDamagePerTurn));

        return playerWinProbability;

    }

    public static Map<Choix, Double> allVictoryProbabilities(Personnage joueur, List<Choix> choixList) {
        Map<Choix, Double> probabilities = new HashMap<>();

        for (Choix choix : choixList) {
            double probability = victoryProbability(joueur, choix);
            probabilities.put(choix, probability);
        }

        return probabilities;
    }










}