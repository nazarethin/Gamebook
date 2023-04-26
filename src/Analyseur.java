import livre.*;

import java.util.ArrayList;

/*----------------------------------------------------------------------------*/

public class Analyseur {
	public static void main(String[] args) {

		Lecteur monLecteur = new LecteurJson("books/FotW.json");
		monLecteur.createLivre();
		ArrayList<Page> pages = monLecteur.getLivre();
		System.out.println(pages.toString());
	}
}
