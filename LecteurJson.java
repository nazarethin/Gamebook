package livre;

import java.io.*;
import org.json.*;

public class LecteurJson {
    protected String fileName;
    protected Reader myReader; // permet d'ouvrir le fichier
    protected JSONTokene myTokener; // Permet de lire son contenu
    protected JSONObject myObject; // Le transforme en objet JSON manipulable
    protected ArrayList<Page> listePage;

    public LecteurJson(String filenName) {
        try {
            this.myReader = new FileReader(file);
            this.myTokener = new JSONTokener(myReader);
            this.myObject = new JSONObject(myTokener);
            this.listePage = new ArrayList<Page>();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier non trouvé.");
        }
    }

    public Page createPage() {

        // Renvoie le premier objet qui a une section soi la premiere "vraie" page.
        if (listePage != null) {
            for (Page page : listePage) {
                if (page.section == section) {
                    return page;
                }
            }
        }

        // A partir de mon Objet JSON je ne prends que la partie section contenant les
        // pages ( ce qui permet de skip ,l'inventaire ,l'intro ...)
        JSONObject sectionPart = myObject.getJSONObject("sections").getJSONObject(String.valueOf(section));
        String text = sectionPart.getString("text");
        Page myPage = new Page(section, text);

    }

}