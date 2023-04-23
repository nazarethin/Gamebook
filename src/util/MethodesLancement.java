package util;

import livre.*;
import view.*;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

public class MethodesLancement extends ListenableModel {
    private String format;
    public Lecteur monLecteur;
    public Personnage mainCharacter;
    private Page pageActuelle;
    private int inputChoice;
    private Menu frame;
    private EcranMenu menu;

    public MethodesLancement(Menu frame, EcranMenu menu, String format) {
        super();
        this.frame = frame;
        this.menu = menu;
        this.pageActuelle = pageActuelle;
        this.format = format;
        this.inputChoice = -1;

    }

    @Override
    public void fireChange() {
        for (Listener l : super.listeners) {
            l.update(this);
        }
    }

    public void chooseLecteur() {
        if (this.format == "json") {
            this.monLecteur = new LecteurJson("books/FotW.json");
        } else if (this.format == "txt") {
            this.monLecteur = new LecteurTxt("books/le-labyrinthe-de-la-mort.txt");
        }
    }

    public void setPageActuelle(Page page) {
        this.pageActuelle = page;
    }

    public Page getPageActuelle() {
        return this.pageActuelle;
    }

    public File[] selectFile(String folderPath) {

        File folder = new File(folderPath);
        // Récupération de tous les fichiers dans le dossier
        File[] files = folder.listFiles();
        return files;
    }

    public Lecteur createLecteur(String folderPath) {

        File[] files = selectFile(folderPath);

        Scanner scanner = new Scanner(System.in);
        if (files == null || files.length == 0) {
            System.out.println("Aucun fichier trouvé au chemin : ");
        } else if (files.length == 1) {
            // Si un seul fichier est trouvé, l'ouvrir directement
            File file = files[0];
            if (file.getName().endsWith(".txt")) {
                System.out.println("Lecteur txt");
                this.monLecteur = new LecteurTxt(folderPath + "/" + file.getName());
            } else {
                System.out.println("Lecteur JSON");
                this.monLecteur = new LecteurJson(folderPath + "/" + file.getName());
            }
            return monLecteur;
        } else {
            // Si plusieurs fichiers sont trouvés, afficher un prompt pour choisir lequel
            // ouvrir
            int selectedFileIndex = -1;
            boolean testfile = false;
            do { // Rend insensible à la casse
                System.out.println("\n -------------------------------\n| Plusieurs fichiers trouvés : "
                        + " | \n -------------------------------");
                for (int i = 0; i < files.length; i++) {
                    System.out.println((i + 1) + ". " + files[i].getName());
                }
                System.out.print("Entrez le numéro du fichier à ouvrir: ");

                try {
                    selectedFileIndex = scanner.nextInt();
                    testfile = true; // La variable est modifiée uniquement si le scanner ne renvoie pas d'erreur
                } catch (Exception e) {
                    System.out.println("\n/!\\ Veuillez entrer un entier entre 1 et " + files.length);
                    scanner.next(); // Supprime le buffer du scanner
                }

            }

            while (selectedFileIndex < 1 || selectedFileIndex > files.length || testfile == false);

            File selectedFile = files[selectedFileIndex - 1];

            if (selectedFile.getName().endsWith(".txt")) { // Lancement du bon lecteur après insertion au clavier
                this.monLecteur = new LecteurTxt(folderPath + "/" + selectedFile.getName());
            } else {
                this.monLecteur = new LecteurJson(folderPath + "/" + selectedFile.getName());
            }
            return monLecteur;
        }
        scanner.next();
        scanner.close();
        return monLecteur;
    }

    public boolean terminalMode() {
        Scanner scanner = new Scanner(System.in);
        String reponse = " ";
        System.out.println("terminal mode test");
        do {
            System.out.println("Voulez-vous lire le livre dans le terminale ? (y or n)");
            reponse = scanner.next();

        } while (!(reponse.equals("y") || reponse.equals("n")));

        if (reponse.equals("y")) {
            System.out.println("terminal mode passé");
            return true;
        } else {
            System.out.println("terminal mode pas passé");
            return false;
        }
    }

    public String createIntroPage() {
        String monIntro = this.monLecteur.createIntro();
        System.out.println(" ----------------------\n|    Introduction :"
                + "    | \n ----------------------\n" + " \n" + monIntro);
        return monIntro;
    }

    public Personnage createCharacterPage() {
        System.out.println(
                " \n--------------------------------------------------\n|    Bienvenue dans la création de Personnage :"
                        + "    | \n --------------------------------------------------\n");
        this.mainCharacter = new Personnage();
        this.mainCharacter.setArgent(0);
        System.out.println("\nPar défaut vous débuterez l'aventure avec " + this.mainCharacter.getArgent()
                + " d'ors.\nVous en gagnerez au cours de votre aventure.");

        // this.monLecteur.createSetup(this.mainCharacter);
        return this.mainCharacter;
    }

    public void jeu() {
        // Scanner scanner = new Scanner(System.in);
        // createLecteur("books");
        // if (terminalMode()) {
        chooseLecteur();
        // System.out.println(this.);
        createIntroPage();
        createCharacterPage();
        int numPage = 1;
        Page maPage = monLecteur.createPage(numPage);
        ArrayList<Choix> mesChoix = maPage.getChoix();
        setPageActuelle(maPage);
        while (mesChoix.size() != 0) {
            maPage = createPage(numPage);
            setPageActuelle(maPage);
            fireChange();
            createChoix(maPage);
            if (maPage.modifieInventaire())
                modifieInventaire(maPage);
            if (!this.monLecteur.statsToChange(maPage.getTexte(), maPage).isEmpty())
                changeStats(maPage);
            if (maPage.isFight())
                isFight(maPage);
            while (this.inputChoice == -1) {
                // attendre que l'utilisateur entre un choix
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    // gérer l'exception
                }
            }
            System.out.println("input choice = " + this.inputChoice);
            numPage = getChoiceInterface(maPage);
            System.out.println(numPage + "\n");
            if (numPage == 0) {
                this.frame.set(new EcranFin(frame, menu));
            }

            mesChoix = maPage.getChoix();

        }
        createFin(maPage);
    }

    public void setInputChoice(int choix) {
        System.out.println("Choice Input = " + choix);
        this.inputChoice = choix;
    }

    public Page createPage(int numSect) {
        Page maPage = monLecteur.createPage(numSect);

        System.out.println(" ----------------------------\n|    Texte de la page " + maPage.getSection() + " :"
                + "    | \n ----------------------------\n" + " \n" + maPage.getTexte());
        return maPage;
    }

    public Page createPage() {
        return createPage(1);
    }

    public void createChoix(Page maPage) {
        ArrayList<Choix> mesChoix = maPage.getChoix();
        System.out.println("\n ------------------\n|      Choix : " + "    | \n ------------------"
                + " \n");
        for (int i = 0; i < mesChoix.size(); i++) {
            if (mesChoix.get(i).getRequireAbility()) {
                System.out.println("Rappel de vos compétences : " + this.mainCharacter.getKaiDisciplines() + "\n");
            }
            int compt = i + 1;
            System.out.println("-" + compt + " " + mesChoix.get(i).getIntitule());
        }
        ;
    }

    public void modifieInventaire(Page maPage) {
        ArrayList<String> itemToAdd = this.monLecteur.itemToAdd(maPage.getTexte(), maPage);
        for (int i = 0; i < itemToAdd.size(); i++) {
            this.mainCharacter.addInventaire(itemToAdd.get(i));
        }
        System.out
                .println("Voici le nouveau contenu de votre inventaire : " + this.mainCharacter.getInventaire() + "\n");
    }

    public void changeStats(Page maPage) {
        ArrayList<Map<Integer, String>> statsToChangeList = this.monLecteur.statsToChange(maPage.getTexte(), maPage);
        for (Map<Integer, String> statsToChange : statsToChangeList) {
            for (Map.Entry<Integer, String> entry : statsToChange.entrySet()) {
                Integer valeur = entry.getKey();
                String stat = entry.getValue();
                this.mainCharacter.changeStats(stat, valeur);
                System.out.println("La stat suivante : " + stat + " a été modifiée de " + valeur + "\n");
            }
        }
    }

    public void isFight(Page maPage) {
        ArrayList<Choix> mesChoix = maPage.getChoix();
        System.out.println("Voici vos compétences : \nHabileté :" + this.mainCharacter.getCombatSkill()
                + "\nEndurance : " + mainCharacter.getCombatSkill() + "\n");
        for (int i = 0; i < mesChoix.size(); i++) {

            int compt = i + 1;
        }
    }

    public int getChoiceInterface(Page maPage) {
        ArrayList<Choix> mesChoix = maPage.getChoix();
        int sectionSuivante = mesChoix.get(this.inputChoice).getSuivant();
        this.inputChoice = -1;
        return sectionSuivante;
    }

    public int getChoiceInput(Page maPage, Scanner scanner) {
        ArrayList<Choix> mesChoix = maPage.getChoix();
        boolean test = false;
        int choixUtilisateur = 0;
        int sectionSuivante = 0;
        do { // Rend insensible à la casse
            System.out.println("\nQuel choix faites vous ? : ");
            try {
                choixUtilisateur = scanner.nextInt();
                test = true; // La variable est modifiée uniquement si le scanner ne renvoie pas d'erreur
            } catch (Exception e) {
                System.out.println("\n/!\\ Veuillez entrer un entier entre 1 et " + mesChoix.size());
                scanner.next(); // Supprime le buffer du scanner
            }
        } while (test == false); // Le nombre est redemandé tant que celui lancé n'est pas valide

        if (choixUtilisateur > mesChoix.size() || choixUtilisateur < 1) {
            System.out.println("\nLe chiffre que vous avez entré est trop grand !\n");
        } else {
            Choix choix = mesChoix.get(choixUtilisateur - 1);
            sectionSuivante = choix.getSuivant();
        }
        return sectionSuivante;
    }

    public void createFin(Page maPage) {
        System.out.println(" ----------------------------\n|    Texte de la page " + maPage.getSection() + " :"
                + "    | \n ----------------------------\n" + " \n" + maPage.getTexte()); // Affiche la dernière page
        System.out.println("\n ----------------\n|      FIN ! " + "    | \n ----------------" + " \n");

    }
}