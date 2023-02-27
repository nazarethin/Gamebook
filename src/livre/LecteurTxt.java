package livre;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class LecteurTxt extends Lecteur{
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
    Créé un objet de type Page à partir d'une section extraite par extractSection
    @param numSection numéro de la section à partir de laquelle on créé la page
    @return un objet Page
    */
    public Page createPage(int numSection){
        String section = extractSection(numSection);
        Page maPage = new Page(numSection,section);

        ArrayList<Choix> mesChoix = extractChoices(section);
        for (int i = 0; i < mesChoix.size(); i++){
            maPage.ajouterChoix(mesChoix.get(i));
        }
        return maPage;
    }
    /* 
    Extrait toute la section du numéro envoyé en argument
    @param numSection numéro de la section à extraire
    @return string correspondant à la section à extraire
    @return "Fichier non trouvé" si le fichier n'est pas bien nommé ou pas au bon endroit
    @return "" si le numéro de section n'est pas trouvé
    */
    public String extractSection(int numSection){
        String section = "";
        Scanner sectionScanner = new Scanner(this.fileContent);
        while (sectionScanner.hasNextLine()) {
            String ligne = sectionScanner.nextLine();
            if (ligne.isEmpty()) {
                try {
                    int maligne = Integer.parseInt(sectionScanner.nextLine());
                    sectionScanner.nextLine();
                    if (maligne == numSection){
                        while (sectionScanner.hasNextLine()){
                            String nextLine = sectionScanner.nextLine();
                            String nextNextLine = sectionScanner.nextLine();
                            if (nextLine.isEmpty() && isStringInt(nextNextLine)) {
                                break;
                            }
                            if (isStringInt(nextLine)){
                                break;
                            }
                            section += nextLine + "\n";
                            if (!nextNextLine.isEmpty()){
                                section += nextNextLine + "\n";
                            }
                        }
                    }
                } catch (NumberFormatException e){
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

    public ArrayList<Choix> extractChoices(String section) { //Prend en argument la section sous forme de String
        ArrayList<Choix> mesChoix = new ArrayList<Choix>();
        String[] phrases = section.split("[,.!?]");
        ArrayList<String> phrasesChoix = new ArrayList<String>();
        ArrayList<Integer> numsChoix = new ArrayList<Integer>();
        for (String phrase : phrases) {
            if (phrase.contains("rendez-vous au") || phrase.contains("Tournez la page")) {
                phrasesChoix.add(phrase.trim());
                Pattern pattern = Pattern.compile("rendez-vous au\\s*(\\d+)");
                Matcher matcher = pattern.matcher(phrase);
                if (matcher.find()) {
                    int num = Integer.parseInt(matcher.group(1));
                    numsChoix.add(num);
                }
            }
        }
    for (int i = 0; i < phrasesChoix.size(); i++){
        Choix monChoix = new Choix(phrasesChoix.get(i), numsChoix.get(i));
        mesChoix.add(monChoix);
        }
    return mesChoix;
    }

    public Page createIntro() {
        String intro = "";
        Scanner introScanner = new Scanner(this.fileContent);
        while (introScanner.hasNextLine()) { //Evite de boucler à l'infini dans la lecture du fichier
            String nextLine = introScanner.nextLine();
            String nextNextLine = introScanner.nextLine();
            if (nextLine.isEmpty() && isStringInt(nextNextLine)){ // Détecte fin de section
                break;
            }            
            if (isStringInt(nextLine)){
                break;
                    }
            intro += nextLine + "\n";
            if(!nextNextLine.isEmpty()){ //Eviter d'insérer une ligne vide à la fin du texte
                intro += nextNextLine + "\n";
            }
        }
        Page pageIntro = new Page(-1, intro);
        introScanner.close();
        return pageIntro;
    }

    public Page createSetup() {
        String intituleVide = "";
        Page pageSetup = new Page(0, intituleVide);
        return pageSetup;
    }

    public ArrayList<Page> getLivre(){
        return this.listePage;
    }

    public void createLivre(){
        //Récupérer le numéro de la dernière page du livre
        int numSect = 0;
        Scanner scanner = new Scanner(this.fileContent);
        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            if (ligne.isEmpty()) {
                try {
                    int maligne = Integer.parseInt(scanner.nextLine());
                    scanner.nextLine();
                    if (maligne > numSect){
                        numSect = maligne;
                    }
                }catch (NumberFormatException e){
                    }
            }
        } 
        scanner.close();
        
        //Parcourir toute les pages pour les créer
        for (int i = 1; i < numSect +1; i++){
            this.listePage.add(createPage(i));
        }
    }
}
