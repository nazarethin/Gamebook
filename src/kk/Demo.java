

/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk;

import kk.geometrie.*;
import kk.calcul.matrice.*;
import kk.calcul.*;
import kk.gui.*;
import kk.KamadaKawai;
import livre.*;

import java.util.ArrayList;
import java.lang.Math;

/*----------------------------------------------------------------------------*/

public class Demo{	

	private ArrayList<Point> pagesToPoints(ArrayList<Page> pages){
		ArrayList<Point> points = new ArrayList<>();
		for (Page p : pages){
			points.add(new Point(0, 0, p.getSection()));
		}
		return points;
	}
		
	private void graphePagesTxt(){
		Lecteur monLecteur = new LecteurTxt("books/le-labyrinthe-de-la-mort.txt");
		monLecteur.createLivre();
		ArrayList<Page> pages = monLecteur.getLivre();
		ArrayList<Point> points = pagesToPoints(pages);
		
		for (Page page : pages){
			for (Choix c : page.getChoix()){
				if (c.getSuivant() != -1 && c.getSuivant() != 400){
					new Segment(points.get(page.getSection()), points.get(c.getSuivant()), true);
				}
			}
		}	
		this.creeVue(points, points.size());
	}

	private void grapheTemoin(){
		Point a = new Point(5, 0);
		Point b = new Point(1, 7);
		Point c = new Point(9, 8);
		
		Segment ab = new Segment(a, b, true);
		Segment bc = new Segment(b, c, true);
					
		ArrayList<Point> points = new ArrayList<>();
		points.add(a);
		points.add(b);
		points.add(c);

		new GrapheGui(new KamadaKawai(points, 10, 2, 2), 50);		
	}

	public void grapheTest(int n){
		ArrayList<Point> points = new ArrayList<>();
		for (int i = 0; i<n; i++){
			points.add(new Point(i, i, i+1));
		}		
		for (int j = 1; j<n; j++){
			new Segment(points.get(j-1), points.get(j), true);
		}
		//new Segment(points.get(0), points.get(n-1), true);
		this.creeVue(points, n);
	}
				
	public void creeVue(ArrayList<Point> points, int n){
		KamadaKawai kk = new KamadaKawai(points, Math.max(200*(n/100), 200), n, 2);
		kk.initCercle();
		int zoom = 1;
		if (n <= 100){
			zoom = 4;
		}
		else if (n <= 300){
			zoom = 2;
		}
		new GrapheGui(kk, zoom);		
	}
			
	private void demoCalculs(KamadaKawai kk, int seuil){
		System.out.println("Démo avec SEUIL = " + seuil + ".");
		System.out.println("Tous les deltas doivent être inférieurs au SEUIL.");
		System.out.println("Les index des points correspondent à ceux des deltas.\n");
		System.out.println("Points :" + kk.getPoints().toString());	
		System.out.println("Deltas :" + kk.getDeltas().toString());
		System.out.println("Calcul ...");
		kk.compute(seuil);
		System.out.println("Points :" + kk.getPoints().toString());	
		System.out.println("Deltas :" + kk.getDeltas().toString());		
	}
	
	public static void main(String[] args){		
		Demo demo = new Demo();		
		//demo.grapheTemoin();
		//demo.grapheTest(3);	
		demo.graphePagesTxt();		
	}
}
