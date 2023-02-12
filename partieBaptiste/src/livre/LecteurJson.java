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

    public Page createIntro(){

        if (myObject.has("intro_sequence") && myObject.get("intro_sequence") instanceof JSONArray) {
            JSONArray introSequence = myObject.getJSONArray("intro_sequence");
            String intro = "";
            for (int i = 0 ;i<introSequence.length();i++){
                //System.out.println(introSequence.get(i)instanceof String);
                //System.out.println(i);
                if(introSequence.get(i)instanceof String){
                    intro += "\n"+introSequence.getString(i)+"\n";
                    //System.out.println("Boucle i : " +i);
                }
                else if (introSequence.get(i)instanceof JSONArray){
                    //System.out.println(introSequence.get(i));
                    JSONArray subSection = introSequence.getJSONArray(i);
                    for (int j = 0; j<subSection.length();j++){
                        if (subSection.get(j) instanceof String) {
                            intro += subSection.getString(j)+"\n";
                            //System.out.println("Boucle j :"+j);
                        }
                    } 
                }
            }
        Page pageintro = new Page(-1,intro);
        return pageintro;
        }
        else{      System.out.println("Pas d'intro ou dans un format invalide");
            String intituleVide = "";
            Page pageintro = new Page(-1, intituleVide);
            return pageintro;
        } 
    }

    public Page createSetup (){
        if(myObject.has("setup")&& myObject.get("setup")instanceof JSONObject){
            JSONObject setup = myObject.getJSONObject("setup");
            JSONArray setupSequence = setup.getJSONArray("sequence");
            String setupText = "";
            for(int i=0; i<setupSequence.length();i++){
                JSONArray tmp = new JSONArray();
                if(setupSequence.get(i)instanceof JSONArray){
                    tmp = setupSequence.getJSONArray(i);
                    for(int j = 0; j<tmp.length();j++)
                    setupText += tmp.getString(j)+"\n";
                }
                else if(setupSequence.get(i)instanceof String){
                    setupText += "\n"+setupSequence.getString(i)+"\n";
                }  
                else{
                    System.out.println("Error");
                }
                
            }  
            System.out.println(setupText);
            String intituleVide = "";
            Page pageSetup = new Page(0, intituleVide);
            return pageSetup;
        }
        else{     
            System.out.println("Pas de 'setup' du personnage ou dans un format invalide");
            String intituleVide = "";
            Page pageSetup = new Page(0, intituleVide);
            return pageSetup;
        } 
    }
}
