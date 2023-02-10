package livre;

import java.io.*;
import org.json.*;
import java.util.ArrayList;

public class LecteurJson {
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
            System.out.println("Erreur : Fichier non trouvé.");
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
        listePage.add(page);
        JSONArray myChoices = sectionPart.getJSONArray("choices");

        if (myChoices.length() != 0) {
            for (int i = 0; i < myChoices.length(); i++) {
                if (!myChoices.getJSONObject(i).isNull("text")) { // pour s'assurer qu'il y a bien un texte a notre
                                                                  // choix
                    // Création de notre objet Choix
                    String texteChoix = myChoices.getJSONObject(i).getString("text");
                    String sectionChoixString = myChoices.getJSONObject(i).getString("section");
                    int sectionChoix = Integer.parseInt(sectionChoixString);
                    Choix myChoice = new Choix(texteChoix, sectionChoix);

                    page.ajouterChoix(myChoice);
                }
            }
        } else {
            if (sectionPart.getBoolean("goodEnding") == true) {
                page.setGoodEnding(true);
            } else {
                page.setGoodEnding(false);
            }
        }
        return page;
    }

}
