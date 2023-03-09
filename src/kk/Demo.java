

/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk;

import kk.geometrie.*;
import kk.calcul.matrice.*;
import kk.calcul.*;
import kk.KamadaKawai;

import java.util.ArrayList;

/*----------------------------------------------------------------------------*/

public class Demo{	
	public static void main(String[] args){	
	
		Point a = new Point(5, 0);
		Point b = new Point(1, 7);
		Point c = new Point(9, 8);
		
		Segment ab = new Segment(a, b, true);
		Segment bc = new Segment(b, c, true);

					
		ArrayList<Point> points = new ArrayList<>();
		points.add(a);
		points.add(b);
		points.add(c);
	
		KamadaKawai kk = new KamadaKawai(points, 10, 2);
		//System.out.println(kk.getMat().toString());
		
		/*
		System.out.println(a.delta(kk.getMat()));
		System.out.println(kk.getDeltas().get().toString());
		System.out.println(kk.getDeltas().maxDelta());
		System.out.println(kk.getDeltas().maxPoint());
		*/


		System.out.println("Points :" + kk.getPoints().toString());	
		System.out.println("Deltas" + kk.getDeltas().toString());
		kk.reduceDelta(a, 5);
		System.out.println("Points :" + kk.getPoints().toString());	
		System.out.println("Deltas" + kk.getDeltas().toString());
		//System.out.println(kk.compute().toString());


		
		/*
		System.out.println(a.toString());
		kk.newPos(a);
		System.out.println(a.toString());
		*/
	
		/*
		System.out.println("Polygone:\n");
		Polygone p = new Polygone(points, 10);
		System.out.println(p.getPoints().toString());

		MatriceAdjacence m = new MatriceAdjacence(points, 10, 2);
		System.out.println("");
		System.out.println(m.toString());
		
		DP dp = new DP(m, a, 1);
		System.out.println(dp.get().toString());
		*/
	}
}
