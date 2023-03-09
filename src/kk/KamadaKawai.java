
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk;

import java.util.*;
import java.lang.Math;

import kk.geometrie.*;
import kk.calcul.matrice.*;
import kk.calcul.*;

import java.io.*;
import java.lang.Thread;

/*----------------------------------------------------------------------------*/

public class KamadaKawai{

	private int RES;
	private int DIAM;
	private MatriceAdjacence matrice;
	private ArrayList<Point> points;
	private Deltas deltas;
	
	public KamadaKawai(ArrayList<Point> points, int res, int diam){	
		this.RES = res;
		this.DIAM = diam;
		this.points = points;
		this.matrice = new MatriceAdjacence(points, RES, DIAM);
		this.deltas = new Deltas(this.matrice, points);
	}
	
	//------------------------------------------------------//
		
	public void newPos(Point p){
		double a, b, c, d , e, x, y, nume;
		HashMap<String, Point> dp1 = new DP(this.matrice, p, 1).get(); 
		HashMap<String, Point> dp2 = new DP(this.matrice, p, 2).get();
		a = dp2.get("dE2a").getX();
		b = dp2.get("dEab").getX();
		c = -dp1.get("dEa").getX();
		d = dp2.get("dE2a").getY();
		e = -dp1.get("dEa").getY();
		
		nume = b*b - d*a;
		
		x = (b*e - d*c)/nume;
		y = (b*c - e*a)/nume;
		
		p.setXY(x, y);
	}
	
	public void reduceDelta(Point p, double seuil){
		double d = p.delta(this.matrice);
		while (d > seuil){
			d = p.delta(this.matrice);
			this.deltas.remove(d);
			this.newPos(p);
			this.deltas.put(d, p);
		}
	}
	
	
	public ArrayList<Point> compute(){
		double SEUIL = 3;
		ArrayList<Point> copy = this.points;

		//...
		return copy;
	}
	

	//------------------------------------------------------//
		
	public ArrayList<Point> getPoints(){
		return this.points;
	}

	public MatriceAdjacence getMat(){
		return this.matrice;
	}
	
	public Deltas getDeltas(){
		return this.deltas;
	}
}