package livre;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Lecteur{

    public Lecteur(){}
        //Objets servant à l'ouverture des fichiers

    public abstract Page createPage(int numSection);
    //public abstract ArrayList<Page> createLivre();
}