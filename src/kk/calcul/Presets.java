

/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul;

import kk.geometrie.*;
import kk.calcul.*;
import kk.KamadaKawai;
import view.kk.*;
import livre.*;

import java.util.ArrayList;
import java.lang.Math;

/*----------------------------------------------------------------------------*/

public class Presets{
	private ArrayList<Point> pagesToPoints(ArrayList<Page> pages){
		ArrayList<Point> points = new ArrayList<>();
		for (Page p : pages){
			points.add(new Point(0, 0, p.getSection()));
		}
		return points;
	}
			
	public KamadaKawai grapheLivre(Lecteur monLecteur, int racine){
		monLecteur.createLivre();
		ArrayList<Page> pages = monLecteur.getLivre();
		ArrayList<Point> points = pagesToPoints(pages);
		System.out.println(pages.toString());
		
		for (Page page : pages){
			for (Choix c : page.getChoix()){
				if (c.getSuivant() != -1 && c.getSuivant() != 400){
					new Segment(points.get(page.getSection()), points.get(c.getSuivant()), true);
				}
			}
		}	
		KamadaKawai kk = new KamadaKawai(points, 800, points.get(racine), 1);
		kk.initCercle();
		return kk;
	}
	
	public KamadaKawai grapheTxt(){
		return grapheLivre(new LecteurTxt("books/le-labyrinthe-de-la-mort.txt"), 1);		
	}
	
	public KamadaKawai grapheJson(){
		return grapheLivre(new LecteurTxt("books/FotW.json"), 0);		
	}
		
	public KamadaKawai grapheTemoin(){
		Point a = new Point(5, 0, 1);
		Point b = new Point(1, 7, 2);
		Point c = new Point(9, 8, 3);
		
		Segment ab = new Segment(a, b, true);
		Segment bc = new Segment(b, c, true);
					
		ArrayList<Point> points = new ArrayList<>();
		points.add(a);
		points.add(b);
		points.add(c);

		return new KamadaKawai(points, 10, a, 0.2);
	}
	
	public KamadaKawai grapheCube(){
		Point a = new Point(0, 0, 1);
		Point b = new Point(0, 0, 2);
		Point c = new Point(0, 0, 3);
		Point d = new Point(0, 0, 4);
		Point e = new Point(0, 0, 5);
		Point f = new Point(0, 0, 6);
		Point g = new Point(0, 0, 7);
		Point h = new Point(0, 0, 8);

		
		Segment ae = new Segment(a, e, true);
		Segment ad = new Segment(a, d, true);
		Segment ab = new Segment(a, b, true);
		
		Segment bc = new Segment(b, c, true);
		Segment bf = new Segment(b, f, true);
		
		Segment cd = new Segment(c, d, true);
		Segment cg = new Segment(c, g, true);
		
		Segment dh = new Segment(d, h, true);
		
		Segment eh = new Segment(e, h, true);
		Segment ef = new Segment(e, f, true);
		
		Segment fg = new Segment(f, g, true);
		
		Segment gh = new Segment(g, h, true);
					
		ArrayList<Point> points = new ArrayList<>();
		points.add(a);
		points.add(b);
		points.add(c);
		points.add(d);
		points.add(e);
		points.add(f);
		points.add(g);
		points.add(h);

		KamadaKawai kk = new KamadaKawai(points, 50, a, 0.000001);
		kk.initCercle();
		return kk;
	}
}
