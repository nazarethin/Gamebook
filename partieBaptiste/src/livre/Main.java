package livre;

import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {

        String fileName = "livreExemple.json";

        LecteurJson monLecteur = new LecteurJson(fileName);
        //System.out.println("monLecteur : "+monLecteur); 

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
            if(choixUtilisateur>mesChoix.size()){
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
