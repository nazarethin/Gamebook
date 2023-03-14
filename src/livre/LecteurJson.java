package livre;

import java.io.*;
import java.util.Scanner;

import org.json.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;

import java.util.HashMap;

public class LecteurJson extends Lecteur {
    protected String fileName;
    protected Reader myReader; // permet d'ouvrir le fichier
    protected JSONTokener myTokener; // Permet de lire son contenu
    protected JSONObject myObject; // Le transforme en objet JSON manipulable
    protected ArrayList<Page> listePage;

    public LecteurJson(String fileName) {
        try {
            this.myReader = new FileReader(fileName);
            this.myTokener = new JSONTokener(myReader);
            this.myObject = new JSONObject(myTokener);
            this.listePage = new ArrayList<Page>();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier non trouvé ou chemin invalide." + fileName);
        }
    }

    public Page createPage(int section) {

        // Renvoie le premier objet qui a une section soi la premiere "vraie" page.
        if (listePage != null) {
            for (Page page : listePage) {
                if (page.getSection() == section) {
                    return page;
                }
            }
        }

        /*
         * A partir de mon Objet JSON je ne prends que la partie section contenant les
         * pages ( ce qui permet de skip ,l'inventaire ,l'intro ...)
         */
        JSONObject sectionPart = myObject.getJSONObject("sections").getJSONObject(String.valueOf(section));
        String text = sectionPart.getString("text");
        Page page = new Page(section, text);
        JSONArray myChoices = sectionPart.getJSONArray("choices");
        if (sectionPart.has("combat")) {
            page.setIsFight(true);
            String txtFin = "Vous mourrez";
            Choix fin = new Choix(txtFin, 0);
            page.ajouterChoix(fin);
        }
        if (myChoices.length() != 0) {
            for (int i = 0; i < myChoices.length(); i++) {
                JSONObject myChoicesObj = myChoices.getJSONObject(i);
                if (!myChoicesObj.isNull("text")) { // pour s'assurer qu'il y a bien un texte a notre
                    // choix
                    String texteChoix = myChoicesObj.getString("text");
                    String sectionChoixString = myChoicesObj.getString("section");
                    int sectionChoix = Integer.parseInt(sectionChoixString);
                    Choix myChoice = new Choix(texteChoix, sectionChoix);
                    if (myChoicesObj.has("requires")) {
                        myChoice.setRequireAbility(true);
                    }
                    page.ajouterChoix(myChoice);
                }
            }
        } else {
            if (sectionPart.has("goodEnding")) {
                if (sectionPart.getBoolean("goodEnding")) {
                    page.setGoodEnding(true);
                } else {
                    page.setGoodEnding(false);
                }
            }
        }
        return page;
    }

    public Page createIntro() {

        if (myObject.has("intro_sequence") && myObject.get("intro_sequence") instanceof JSONArray) {
            JSONArray introSequence = myObject.getJSONArray("intro_sequence");
            String intro = "";
            for (int i = 0; i < introSequence.length(); i++) {
                if (introSequence.get(i) instanceof String) {
                    intro += "\n" + introSequence.getString(i) + "\n";
                } else if (introSequence.get(i) instanceof JSONArray) {
                    JSONArray subSection = introSequence.getJSONArray(i);
                    for (int j = 0; j < subSection.length(); j++) {
                        if (subSection.get(j) instanceof String) {
                            intro += subSection.getString(j) + "\n";
                        }
                    }
                }
            }
            Page pageintro = new Page(-1, intro);
            return pageintro;
        } else {
            System.out.println("Pas d'intro ou dans un format invalide");
            String intituleVide = "";
            Page pageintro = new Page(-1, intituleVide);
            return pageintro;
        }
    }

     public Page createSetup(Personnage monPerso) {
        if (myObject.has("setup") && myObject.get("setup") instanceof JSONObject) {
            JSONObject setup = myObject.getJSONObject("setup");
            JSONArray setupSequence = setup.getJSONArray("sequence");
            String setupText = "";
            for (int i = 0; i < setupSequence.length(); i++) {
                JSONArray tmp = new JSONArray();
                if (setupSequence.get(i) instanceof JSONArray) {
                    tmp = setupSequence.getJSONArray(i);
                    for (int j = 0; j < tmp.length(); j++)
                        setupText += tmp.getString(j) + "\n";
                } else if (setupSequence.get(i) instanceof String) {
                    setupText += "\n" + setupSequence.getString(i) + "\n";
                } else {
                    System.out.println("Error");
                }
            }
            System.out.println(setupText + "");
            Scanner scanner = new Scanner(System.in);
            if (setupSequence != null && setupSequence.get(12) instanceof String) {
                String stringAbility = setupSequence.getString(12);
                Scanner scannerL = new Scanner(stringAbility);
                ArrayList<String> maListe = new ArrayList<String>();
                while (scannerL.hasNextLine()) {
                    String nextLine = scannerL.nextLine();
                    maListe.add(nextLine);
                }
                scannerL.close();
                System.out.println(maListe);
                for (int i = 0; i < maListe.size(); i++) { // Sert a s'assurer que maListe ne contient pas d'élément
                                                           // vide
                    if (maListe.get(i).isEmpty()) {
                        maListe.remove(i);
                        i--; // soustraire 1 pour éviter de sauter un élément
                    }
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("\nEntrez le numéro de votre compétence numéro : " + (i + 1));
                    String abilityInput = scanner.nextLine();
                    try {
                        int abilityChose = Integer.parseInt(abilityInput);
                        if (abilityChose >= 0 && abilityChose < maListe.size()) {
                            if (monPerso.containAbility(maListe.get(abilityChose))) {
                                System.out.println("\n /!\\ Vous avez déjà choisie cette compétence");
                                i--;
                            } else {
                                monPerso.addKaiDisciplines(maListe.get(abilityChose));
                            }
                        } else {
                            System.out.println("Le numéro entré est invalide. Veuillez entrer un nombre entre 0 et "
                                    + (maListe.size() - 1));
                            i--; // soustraire 1 pour refaire l'itération
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer un nombre.");
                        i--; // soustraire 1 pour refaire l'itération
                    }
                }
            }
            System.out.println("Recapitulatif des compétences choisie : " + monPerso.getKaiDisciplines());
            String intituleVide = "";
            Page pageSetup = new Page(0, intituleVide);
            // mettre un scanner.close() entraine une erreur du type :
            // java.util.NoSuchElementException sous entendant que on va encore utiliser les
            // info du scanner et que je ne peux donc pas le fermer prématurement.
            return pageSetup;
        } else

        {
            System.out.println("Pas de 'setup' du personnage ou dans un format invalide");
            String intituleVide = "";
            Page pageSetup = new Page(0, intituleVide);
            return pageSetup;
        }
    }
    

    public ArrayList<Page> getLivre() {
        return this.listePage;
    }

    public void createLivre() {

        JSONObject sectionPart = myObject.getJSONObject("sections");
        ArrayList<Integer> sectionNumbers = new ArrayList<Integer>();
        for (String sectionNumber : sectionPart.keySet()) {
            sectionNumbers.add(Integer.parseInt(sectionNumber));
        }
        Collections.sort(sectionNumbers);
        int max = Collections.max(sectionNumbers);
        for (int i = 1; i <= max + 1; i++) {
            this.listePage.add(createPage(i - 1));
        }

    }

    public ArrayList<String> itemToAdd(String section, Page maPage) { // A COMPLETER Reçoit le texte de la page et la
                                                                      // page en question, renvoie la string de l'item à
                                                                      // ajouter à l'inventaire
        ArrayList<String> res = new ArrayList<String>();
        return res;
    }

    public ArrayList<Map<Integer, String>> statsToChange(String section, Page maPage) { // A COMPLETER Reçoit le texte de la page
                                                                             // et la page en question, renvoie un
                                                                             // couple avec la valeur du changement et
                                                                             // la string correspondant à la stat à
                                                                             // changer
        ArrayList<Map<Integer, String>> result = new ArrayList<>();
        return result;
    }
}
