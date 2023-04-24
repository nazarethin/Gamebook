package livre;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern; //Utile pour la lecture du fichier txt
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;

import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.plaf.metal.MetalScrollButton;

public class LecteurTxt extends Lecteur {
    private Scanner scannerTxt;
    private String fileContent;
    protected ArrayList<Page> listePage;

    public LecteurTxt(String fileName) {
        try {
            scannerTxt = new Scanner(new File(fileName));
            this.fileContent = scannerTxt.useDelimiter("\\Z").next();
            scannerTxt.close();
            this.listePage = new ArrayList<Page>();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier non trouvé ou chemin invalide");
        }
    }

    /*
     * Créé un objet de type Page à partir d'une section extraite par extractSection
     * 
     * @param numSection numéro de la section à partir de laquelle on créé la page
     * 
     * @return un objet Page
     */
    public Page createPage(int numSection) {
        String section = extractSection(numSection);
        Page maPage = new Page(numSection, section);

        ArrayList<Choix> mesChoix = extractChoices(section, maPage);
        for (int i = 0; i < mesChoix.size(); i++) {
            maPage.ajouterChoix(mesChoix.get(i));
        }
        return maPage;
    }

    /*
     * Extrait toute la section du numéro envoyé en argument
     * 
     * @param numSection numéro de la section à extraire
     * 
     * @return string correspondant à la section à extraire
     * 
     * @return "Fichier non trouvé" si le fichier n'est pas bien nommé ou pas au bon
     * endroit
     * 
     * @return "" si le numéro de section n'est pas trouvé
     */
    public String extractSection(int numSection) {
        String section = "";
        Scanner sectionScanner = new Scanner(this.fileContent);
        while (sectionScanner.hasNextLine()) {
            String ligne = sectionScanner.nextLine();
            if (ligne.isEmpty()) {
                try {
                    int maligne = Integer.parseInt(sectionScanner.nextLine());
                    sectionScanner.nextLine();
                    if (maligne == numSection) {
                        while (sectionScanner.hasNextLine()) {
                            String nextLine = sectionScanner.nextLine();
                            String nextNextLine = sectionScanner.nextLine();
                            if (nextLine.isEmpty() && isStringInt(nextNextLine)) {
                                break;
                            }
                            if (isStringInt(nextLine)) {
                                break;
                            }
                            section += nextLine + "\n";
                            if (!nextNextLine.isEmpty()) {
                                section += nextNextLine + "\n";
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        sectionScanner.close();
        return section;
    }

    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public ArrayList<Choix> extractChoices(String section, Page maPage) { // Prend en argument la section sous forme de
                                                                          // String
        ArrayList<Choix> mesChoix = new ArrayList<Choix>();
        String[] phrases = section.split("[,.!?]");
        ArrayList<String> phrasesChoix = new ArrayList<String>();
        ArrayList<Integer> numsChoix = new ArrayList<Integer>();
        for (String phrase : phrases) {
            if (phrase.contains("rendez-vous au") || phrase.contains("Tournez la page")
                    || phrase.contains("Rendez-vous au")) {
                phrase = phrase.toLowerCase();
                phrasesChoix.add(phrase.trim());
                Pattern pattern = Pattern.compile("rendez-vous au\\s*(\\d+)");
                Matcher matcher = pattern.matcher(phrase);
                if (matcher.find()) {
                    int num = Integer.parseInt(matcher.group(1));
                    numsChoix.add(num);
                }
            }
            if (phrase.contains("Si vous êtes vainqueur") || phrase.contains("Si vous gagnez")) { // Détecte les choix à
                                                                                                  // partir d'un combat
                maPage.setIsFight(true);
                phrasesChoix.add("Vous mourrez.");
                numsChoix.add(0);
            }

            if (phrase.contains("Notez")) {
                maPage.setModifieInventaire();

            }
        }
        for (int i = 0; i < phrasesChoix.size(); i++) {
            Choix monChoix = new Choix(phrasesChoix.get(i), numsChoix.get(i));
            mesChoix.add(monChoix);
        }
        return mesChoix;
    }

    public String createIntro() {
        String intro = "";
        Scanner introScanner = new Scanner(this.fileContent);
        while (introScanner.hasNextLine()) { // Evite de boucler à l'infini dans la lecture du fichier
            String nextLine = introScanner.nextLine();
            String nextNextLine = introScanner.nextLine();
            if (nextLine.isEmpty() && isStringInt(nextNextLine)) { // Détecte fin de section
                break;
            }
            if (isStringInt(nextLine)) {
                break;
            }
            intro += nextLine + "\n";
            if (!nextNextLine.isEmpty()) { // Eviter d'insérer une ligne vide à la fin du texte
                intro += nextNextLine + "\n";
            }
        }
        introScanner.close();
        return intro;
    }

    public Page createSetup(Personnage monPerso) {
        String intituleVide = "";
        Page pageSetup = new Page(0, intituleVide);
        return pageSetup;
    }

    public ArrayList<Page> getLivre() {
        return this.listePage;
    }

    public void createLivre() {
        // Récupérer le numéro de la dernière page du livre
        int numSect = 0;
        Scanner scanner = new Scanner(this.fileContent);
        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            if (ligne.isEmpty()) {
                try {
                    int maligne = Integer.parseInt(scanner.nextLine());
                    scanner.nextLine();
                    if (maligne > numSect) {
                        numSect = maligne;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        scanner.close();

        // Parcourir toute les pages pour les créer
        for (int i = 0; i < numSect + 1 ; i++) {
            this.listePage.add(createPage(i));
        }
        this.listePage.get(numSect).setGoodEnding(true);
    }

    public ArrayList<String> itemToAdd(String section, Page maPage) {
        ArrayList<String> results = new ArrayList<>();
        String normalizedText = section.replaceAll("[éèêë]", "e")
                .replaceAll("[àâä]", "a")
                .replaceAll("[ûüù]", "u")
                .toLowerCase();
        Pattern pattern = Pattern.compile("(?i)notez\\s+(un\\s+|une\\s+)?([\\p{L}\\p{Punct}\\s&&[^.!?]]+)+[.!?]");
        Matcher matcher = pattern.matcher(normalizedText);

        while (matcher.find()) {
            String result = matcher.group().replaceAll("(?i)notez\\s+(un\\s+|une\\s+)?", "").replaceAll("\\.", "")
                    .trim();
            results.add(result);
        }

        return results;
    }

    
    public ArrayList<Map<Integer, String>> statsToChange(String section, Page maPage) {
        ArrayList<Map<Integer, String>> result = new ArrayList<Map<Integer, String>>();
        String[] phrases = section.split("\\.");
        for (String phrase : phrases) {
            if (phrase.contains("d'ENDURANCE") || phrase.contains("d'HABILETÉ") || phrase.contains("de CHANCE")) {
                String[] mots = phrase.split("\\s+");
                ArrayList<String> statsList = new ArrayList<String>(); // nouvelle ArrayList pour stocker les stats dans l'ordre où on les trouve
                ArrayList<Integer> valeurs = new ArrayList<Integer>();
                for (int i = 0; i < mots.length; i++) {
                    String mot = mots[i].replaceAll("[^\\p{L}\\p{N}]", ""); // on enlève les caractères spéciaux
                    if (mot.matches("[0-9]+")) { // on vérifie que le mot est un nombre entier
                        int valeur = Integer.parseInt(mot);
                        valeurs.add(valeur);
                        while (i+1 < mots.length && !mots[i+1].matches("[0-9]+")) { // on cherche le mot en majuscule qui suit le nombre
                            if (mots[i].equalsIgnoreCase("d'ENDURANCE") || mots[i].equalsIgnoreCase("d'HABILETÉ") || mots[i].equalsIgnoreCase("de CHANCE")) {
                                String stat = mots[i].toLowerCase().substring(2).replaceAll("[éèêë]", "e"); // on enlève les caractères spéciaux et on met en minuscules
                                
                                if (!statsList.contains(stat)){
                                    statsList.add(stat); // on ajoute la stat à la liste dans l'ordre où on l'a trouvée
                                }
                                
                            }
                            else if (mots[i+1].equalsIgnoreCase("d'ENDURANCE") || mots[i+1].equalsIgnoreCase("d'HABILETÉ") || mots[i+1].equalsIgnoreCase("de CHANCE")){
                                String stat = mots[i+1].toLowerCase().substring(2).replaceAll("[éèêë]", "e"); // on enlève les caractères spéciaux et on met en minuscules
                                if (!statsList.contains(stat)){
                                    statsList.add(stat); // on ajoute la stat à la liste dans l'ordre où on l'a trouvée
                                }
                            }
                            i++;
                        }
                    }
                }
                if (!valeurs.isEmpty() && !statsList.isEmpty()) {
                    for (int i = 0; i < valeurs.size(); i++) {
                        Map<Integer, String> stats = new HashMap<Integer, String>();
                        int valeur = valeurs.get(i);
                        String stat = statsList.get(i); // on récupère la stat dans l'ordre où on l'a trouvée
                        stats.put(valeur, stat);
                        result.add(stats);
                    }
                    
                    
                }
            }
        }
        return result;
    }



    // Tester avec la page 269
    /*
    public Map<Integer, String> statsToChange(String section, Page maPage) {
        Map<Integer, String> stats = new HashMap<Integer, String>();
        return stats;
    }
    */

}