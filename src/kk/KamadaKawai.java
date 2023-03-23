
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk;

import java.util.*;
import java.lang.Math;

import kk.geometrie.*;
import kk.calcul.matrice.*;
import kk.calcul.*;
import kk.gui.util.*;

/*----------------------------------------------------------------------------*/

public class KamadaKawai extends ListenableModel{

	public class ComparateurDeltas implements Comparator<Point>{ //Comparateur sur delta pour les points
		@Override
		public int compare(Point p1, Point p2) {
		   return Double.compare(p1.delta(KamadaKawai.this.matrice), p2.delta(KamadaKawai.this.matrice));
		}
	}
	
	//------------------------------------------------------//

	private int RES; //dimension de l'espace d'affichage / résolution
	private int DIAM; //diamètre du graphe / chemin le plus long
	private double seuil; //seuil précision du repositionnement. Plus bas --> plus précis
	private MatriceAdjacence matrice;
	private ArrayList<Point> points;
	
	public KamadaKawai(ArrayList<Point> points, int res, int diam, double seuil){	
		this.RES = res;
		this.DIAM = diam;
		this.seuil = seuil;
		this.points = points;
		this.matrice = new MatriceAdjacence(points, RES, DIAM);
	}
		
	//------------------------------------------------------//

	public ArrayList<Point> initCercle(){
		int modulo = 90;
		int n = points.size()/modulo;
		if (n >= 1){
			ArrayList<Point> res = new ArrayList<>();
			for (int i = 0; i<=n; i++){	
				int fin = (i+1)*modulo;
				if (fin > points.size()){
					fin = points.size();
				}			
				ArrayList<Point> subList = new ArrayList<Point>(points.subList(i*modulo, fin));
				res.addAll(new Polygone(subList, RES - i*100, i*50).getPoints());				
			}
			return res;
		}
		return new Polygone(points, RES-18).getPoints();
	}
				
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
	
	public Point maxDelta(){
		return Collections.max(this.points, new ComparateurDeltas());
	}
	
	public void reduceDelta(Point p, double seuil){ 
		while (p.delta(this.matrice) > seuil){
			this.newPos(p);
		}
	}
		
	public void compute(double seuil){	
		Point pmax = this.maxDelta();
		while (pmax.delta(this.matrice) > seuil){
			this.reduceDelta(pmax, seuil);
		}
	}
	
	//------------------------------------------------------//
		
	public ArrayList<Point> getPoints(){
		return this.points;
	}

	public MatriceAdjacence getMat(){
		return this.matrice;
	}
	
	public double getRes(){
		return this.RES;
	}

	public ArrayList<Double> getDeltas(){
		ArrayList<Double> res = new ArrayList<>();
		for (Point p : this.points){
			res.add(p.delta(this.matrice));
		}
		return res;
	}
	
	public int getSeuil(){
		return (int) this.seuil;
	}
	
	@Override
	public void fireChange(){
		this.compute(this.seuil);
		for (Listener l : super.listeners){
			l.update(this);
		}	
	}
}