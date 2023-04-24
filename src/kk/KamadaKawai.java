
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk;

import java.util.*;
import java.lang.Math;

import kk.geometrie.*;
import kk.calcul.*;
import util.*;

/*----------------------------------------------------------------------------*/

public class KamadaKawai extends ListenableModel{

	public class ComparateurDeltas implements Comparator<Point>{ //Comparateur sur delta pour les points
		@Override
		public int compare(Point p1, Point p2) {
		   return Double.compare(p1.delta(KamadaKawai.this.matrice), p2.delta(KamadaKawai.this.matrice));
		}
	}
	
	//------------------------------------------------------//

	private int cc = 0; //compteur compute
	private int crd = 0; //compteur reduce delta

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
	
	public KamadaKawai(ArrayList<Point> points, int res, Point racine, double seuil){	
		this.RES = res;
		this.matrice = new MatriceAdjacence(points, RES, racine);
		this.DIAM = matrice.longuestPath(racine);
		this.seuil = seuil;
		this.points = points;
	}
		
	//------------------------------------------------------//

	public ArrayList<Point> initCercle(){
		int modulo = 90;
		int n = points.size()/modulo;
		if (n > 1){
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
		DP dp = new DP(this.matrice, p); 
		a = dp.dE2a().getX();
		b = dp.dEab().getX();
		c = - dp.dEa().getX();
		d = dp.dE2a().getY();
		e = - dp.dEa().getY();
		
		nume = b*b - d*a;
		
		x = (b*e - d*c)/nume;
		y = (b*c - e*a)/nume;
		
		p.setXY(p.getX() + x, p.getY() + y);
	}
	
	public Point maxDelta(){
		return Collections.max(this.points, new ComparateurDeltas());
	}
			
	public void compute(double seuil){
		Point p = this.maxDelta();
		System.out.println(p.delta(this.matrice));
		
		while(p.delta(this.matrice) > seuil){
			cc++;
			System.out.println("---" + p.delta(this.matrice));
						
			while (p.delta(this.matrice) > seuil){
				this.newPos(p);
				crd++;
				System.out.println("-" + p.delta(this.matrice));
			}
						
			p = this.maxDelta();
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
	
	public int getDiam(){
		return this.DIAM;
	}
	
	public void compute(){
		this.compute(this.seuil);
	}
	
	@Override
	public void fireChange(){
		System.out.println("KK");
		for (Listener l : super.listeners){
			l.update(this);
		}	
	}
}