package livre;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fileName = "livreExemple.json";

        LecteurJson monLecteur = new LecteurJson(fileName);

        /* ----------- Création de l'intro ---------- */
        Page monIntro =  monLecteur.createIntro();
        System.out.println(" ----------------------\n|    Introduction :"
        + "    | \n ----------------------\n" + " \n" + monIntro.getTexte());
        

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
            Choix choix = mesChoix.get(choixUtilisateur - 1);
            int sectionSuivante = choix.getSuivant();

            Page pageSuivante = monLecteur.createPage(sectionSuivante);

            System.out
                    .println(" ----------------------------\n|    Texte de la page " + pageSuivante.getSection() + " :"
                            + "    | \n ----------------------------\n" + " \n" + pageSuivante.getTexte());
        }
        choixScanner.close();
    }

}
