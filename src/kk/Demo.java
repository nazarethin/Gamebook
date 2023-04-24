

/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk;

import kk.geometrie.*;
import kk.calcul.*;
import view.kk.*;
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
						
		int n = points.size();
		KamadaKawai kk = new KamadaKawai(points, Math.max(200*(n/100), 200), points.get(1), 0.0003);
		kk.initCercle();
		gestionZoom(kk, n);	
	}
	
	private void grapheTemoin(){
		Point a = new Point(5, 0, 1);
		Point b = new Point(1, 7, 2);
		Point c = new Point(9, 8, 3);
		
		Segment ab = new Segment(a, b, true);
		Segment bc = new Segment(b, c, true);
					
		ArrayList<Point> points = new ArrayList<>();
		points.add(a);
		points.add(b);
		points.add(c);

		new GrapheGui(new KamadaKawai(points, 10, a, 0.2), 50);
	}
	
	
	public void grapheTest(int n){
		ArrayList<Point> points = new ArrayList<>();
		for (int i = 0; i<n; i++){
			points.add(new Point(i, i, i+1));
		}		
		for (int j = 1; j<n; j++){
			new Segment(points.get(j-1), points.get(j), true);
		}
		this.creeVue(points, Math.max(200*(n/100), 200));
	}
		
	public void gestionZoom(KamadaKawai kk, int n){
		int zoom = 1;
		if (n <= 100){
			zoom = 6;
		}
		else if (n <= 300){
			zoom = 2;
		}
		new GrapheGui(kk, zoom);		
	}
		
	public void creeVue(ArrayList<Point> points, int n){
		KamadaKawai kk = new KamadaKawai(points, n, points.get(0), 2);
		kk.initCercle();
		gestionZoom(kk, n);
	}
					
	//--------------------------------------------------------------------------------------//
	//--------------------------------------------------------------------------------------//
	//--------------------------------------------------------------------------------------//
	
	public static void main(String[] args){		
		Demo demo = new Demo();		
		//demo.graphePagesJSon();
		//demo.grapheTemoin();
		//demo.grapheTest(3);	
		//demo.graphePagesTxt();	
		new GrapheGui(new Presets().grapheTxt(), 1);
	} 
}
