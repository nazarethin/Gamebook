package livre;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        //CHOIX FICHIER
        boolean isTxt = false;
        String folderPath = "books"; // chemin du dossier contenant les livres
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
            Scanner scanner = new Scanner(System.in);
            int selectedFileIndex = -1;
            boolean testfile = false;
            do { //Rend insensible à la casse
                System.out.println("\n -------------------------------\n| Plusieurs fichiers trouvés : "
                    + " | \n -------------------------------");
                for (int i = 0; i < files.length; i++) {
                    System.out.println((i+1) + ". " + files[i].getName());
                }
				System.out.print("Entrez le numéro du fichier à ouvrir: ");
                
				try {
					selectedFileIndex = scanner.nextInt();
					testfile = true; //La variable est modifiée uniquement si le scanner ne renvoie pas d'erreur
				}
				catch (Exception e){
					System.out.println("\n/!\\ Veuillez entrer un entier entre 1 et " + files.length);
					scanner.next(); //Supprime le buffer du scanner	
				}
			}
            while (selectedFileIndex < 1 || selectedFileIndex > files.length || testfile == false);

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
        
        /* ------------- Création du Personnage ----------- */
        System.out.println(" \n--------------------------------------------------\n|    Bienvenue dans la création de Personnage :"
        + "    | \n --------------------------------------------------\n");
        Personnage mainCharacter = new Personnage();
        mainCharacter.setArgent(0);
        System.out.println("\nPar défaut vous débuterez l'aventure avec "+mainCharacter.getArgent()+" d'ors.\nVous en gagnerez au cours de votre aventure.");

        monLecteur.createSetup(mainCharacter);


        /* ----------- Début du jeu ---------- */
        Page maPage = monLecteur.createPage(1);
        ArrayList<Choix> mesChoix = maPage.getChoix();
        Scanner choixScanner = new Scanner(System.in); //Détecte la sortie de l'utilisateur

        while (mesChoix.size() != 0) {
            System.out.println(" ----------------------------\n|    Texte de la page " + maPage.getSection() + " :"
                    + "    | \n ----------------------------\n" + " \n" + maPage.getTexte());
            System.out.println("\n ------------------\n|      Choix : " + "    | \n ------------------"
                        + " \n");
            for (int i = 0; i < mesChoix.size(); i++) {
                int compt = i + 1;
                System.out.println("-" + compt + " " + mesChoix.get(i).getIntitule());
            }

			boolean test = false;
            int choixUtilisateur = 0;
			do { //Rend insensible à la casse
				System.out.println("\nQuel choix faites vous ? : ");
				try {
					choixUtilisateur = choixScanner.nextInt();
					test = true; //La variable est modifiée uniquement si le scanner ne renvoie pas d'erreur
				}
				catch (Exception e){
					System.out.println("\n/!\\ Veuillez entrer un entier entre 1 et " + mesChoix.size());
					choixScanner.next(); //Supprime le buffer du scanner	
				}
			}
			while (test == false); // Le nombre est redemandé tant que celui lancé n'est pas valide
            
            if(choixUtilisateur>mesChoix.size() || choixUtilisateur < 1){
                System.out.println("\nLe chiffre que vous avez entré est trop grand !\n");
            }else {
                Choix choix = mesChoix.get(choixUtilisateur - 1);
                int sectionSuivante = choix.getSuivant();

                maPage = monLecteur.createPage(sectionSuivante);
                mesChoix = maPage.getChoix();
            }
        } 
        System.out.println(" ----------------------------\n|    Texte de la page " + maPage.getSection() + " :"
                    + "    | \n ----------------------------\n" + " \n" + maPage.getTexte()); //Affiche la dernière page
        System.out.println("\n ----------------\n|      FIN ! " + "    | \n ----------------"
                        + " \n");
        choixScanner.close();
    }
}
