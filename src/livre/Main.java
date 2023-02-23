package livre;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        //CHOIX FICHIER
        boolean isTxt = false;
        String folderPath = "../ma_partie/books"; // chemin du dossier à vérifier
        File folder = new File(folderPath);

        // Récupération de tous les fichiers dans le dossier
        File[] files = folder.listFiles();
        Lecteur monLecteur = null;
        if (files == null || files.length == 0) {
            System.out.println("Aucun fichier trouvé au chemin : ");
        } else if (files.length == 1) {
            // Si un seul fichier est trouvé, l'ouvrir directement
            File file = files[0];
            if (file.getName().endsWith(".txt")) {
                System.out.println("Lecteur txt");
                monLecteur = new LecteurTxt(folderPath + "/" + file.getName());
                isTxt = true;
            }
            else {
                System.out.println("Lecteur JSON");
                monLecteur = new LecteurJson(folderPath + "/" + file.getName());
            }
        } else {
            // Si plusieurs fichiers sont trouvés, afficher un prompt pour choisir lequel ouvrir
            System.out.println("Plusieurs fichiers trouvés:");
            for (int i = 0; i < files.length; i++) {
                System.out.println((i+1) + ". " + files[i].getName());
            }

            Scanner scanner = new Scanner(System.in);
            int selectedFileIndex = -1;
            while (selectedFileIndex < 1 || selectedFileIndex > files.length) {
                System.out.print("Entrez le numéro du fichier à ouvrir: ");
                selectedFileIndex = scanner.nextInt();
            }
            File selectedFile = files[selectedFileIndex - 1];

            if (selectedFile.getName().endsWith(".txt")) { //Lancement du bon lecteur après insertion au clavier
                monLecteur = new LecteurTxt(folderPath + "/" + selectedFile.getName());
            }
            else {
                monLecteur = new LecteurJson(folderPath + "/" + selectedFile.getName());
            }
        }

        /* ----------- Création de l'intro ---------- */

        Page monIntro =  monLecteur.createIntro();
        System.out.println(" ----------------------\n|    Introduction :"
        + "    | \n ----------------------\n" + " \n" + monIntro.getTexte());
        System.out.println("Passez à la création du personnage : (Press Enter to continue)");
        Scanner pressEnter1 = new Scanner(System.in);
        pressEnter1.nextLine();  
        
        /* ------------- Création du Personnage ----------- */
        System.out.println(" \n--------------------------------------------------\n|    Bienvenue dans la création de Personnage :"
        + "    | \n --------------------------------------------------\n");
        Personnage mainCharacter = new Personnage();
        mainCharacter.setArgent(0);
        System.out.println("\nPar défaut vous débuterez l'aventure avec "+mainCharacter.getArgent()+" d'ors.\nVous en gagnerez au cours de votre aventure.");

        Scanner scNomCharacter = new Scanner(System.in);
        System.out.println("\nVeuillez entrez le nom de votre personnage : ");    
        String nomChar = scNomCharacter.nextLine();
        mainCharacter.setNom(nomChar);
        monLecteur.createSetup();
        
    

        System.out.println("Etes vous pret à l'aventure ? (Press Enter to continue)");
        Scanner pressEnter2 = new Scanner(System.in);
        pressEnter2.nextLine();

        /* ----------- Début du jeu ---------- */
        Page maPage = monLecteur.createPage(1);

        System.out.println(" ----------------------------\n|    Texte de la page " + maPage.getSection() + " :"
                + "    | \n ----------------------------\n" + " \n" + maPage.getTexte());
        
        ArrayList<Choix> mesChoix = maPage.getChoix();
        if (mesChoix.size() == 0) {
            System.out.println("\n ----------------\n|      FIN ! " + "    | \n ----------------"
                    + " \n");
        } else {
            System.out.println("\n ------------------\n|      Choix : " + "    | \n ------------------"
                    + " \n");
            for (int i = 0; i < mesChoix.size(); i++) {
                int compt = i + 1;
                System.out.println("-" + compt + " " + mesChoix.get(i).getIntitule());
            }
        }
        Scanner choixScanner = new Scanner(System.in);
        while (mesChoix.size() != 0) {
            System.out.println("\nQuel choix faites vous ? : ");
            int choixUtilisateur = choixScanner.nextInt();
            if(choixUtilisateur>mesChoix.size() || choixUtilisateur < 1){
                System.out.println("\nLe chiffre que vous avez entré est trop grand !");
            }else {
                Choix choix = mesChoix.get(choixUtilisateur - 1);
                int sectionSuivante = choix.getSuivant();

                Page pageSuivante = monLecteur.createPage(sectionSuivante);

                System.out
                        .println(" ----------------------------\n|    Texte de la page " + pageSuivante.getSection() + " :"
                                + "    | \n ----------------------------\n" + " \n" + pageSuivante.getTexte());
            }
        } 
        
        choixScanner.close(); 
        pressEnter1.close();
        pressEnter2.close();
        scNomCharacter.close();
    }
}
