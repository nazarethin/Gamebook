

/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk;

import kk.geometrie.*;
import kk.calcul.matrice.*;
import kk.calcul.*;
import kk.gui.*;
import kk.KamadaKawai;

import java.util.ArrayList;

/*----------------------------------------------------------------------------*/

public class Demo{	
	public static void main(String[] args){	
	
		Point a = new Point(5, 0); //points déjà arrangés en cercle (coords arrondies à l'entier)
		Point b = new Point(1, 7);
		Point c = new Point(9, 8);
		
		Segment ab = new Segment(a, b, true);
		Segment bc = new Segment(b, c, true);
					
		ArrayList<Point> points = new ArrayList<>();
		points.add(a);
		points.add(b);
		points.add(c);
	
		KamadaKawai kk = new KamadaKawai(points, 10, 2);		
		new GrapheGui(kk);
		
		/*
		System.out.println("Démo avec SEUIL = 2.");
		System.out.println("Tous les deltas doivent être inférieurs au SEUIL.");
		System.out.println("Les index des points correspondent à ceux des deltas.\n");
		System.out.println("Points :" + kk.getPoints().toString());	
		System.out.println("Deltas :" + kk.getDeltas().toString());
		System.out.println("Calcul ...");
		kk.compute(2);
		System.out.println("Points :" + kk.getPoints().toString());	
		System.out.println("Deltas :" + kk.getDeltas().toString());
		*/
	}
}
