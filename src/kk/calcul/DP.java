
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul;

import java.util.HashMap;
import java.lang.Math;
import java.util.ArrayList;

import kk.geometrie.Point;
import kk.calcul.matrice.*;
import kk.calcul.numerateur.*;
import kk.calcul.denominateur.Denominateur;


/*----------------------------------------------------------------------------*/

public class DP{

	private MatriceAdjacence matrice;
	private Point point;
	private double degre;
	
	public DP(MatriceAdjacence mat, Point p, double degre){	
		this.point = p;
		this.matrice = mat;
		this.degre = degre - 0.5;
	}
	
	//------------------------------------------------------//
		
	public HashMap<String, Point> get(){
		HashMap<String, Point> res = new HashMap<>();		
		if (this.getDeg() == 1){
			res.put("dEa", this.computeDP(new NumeSimple()));
		}
		else {
			res.put("dE2a", this.computeDP(new NumeCarre()));
			res.put("dEab", this.computeDP(new NumeDouble()));
		}
		return res;
	}
		
	public Point computeDP(Numerateur N){
		Ressort[] paires = this.matrice.getPaires(this.point);
		double dEx = 0;
		double dEy = 0;
		for (Ressort R : paires){
				dEx += this.dpDiv(N, new Denominateur(this.degre), "x", R);
				dEy += this.dpDiv(N, new Denominateur(this.degre), "y", R);
		}
		return new Point(dEx, dEy);
	}
	
	public double dpDiv(Numerateur N, Denominateur D, String A, Ressort R){
		Point m = R.milieu();
		double a = m.getXY().get(A);
		double b = D.denominateur(m);
		
		if (N instanceof NumeCarre){
			a = m.getXY().get(this.otherKey(A));
		}
		else if (N instanceof NumeDouble){
			b = m.getXY().get(this.otherKey(A));
		}
		return this.safeDiv(N.numerateur(R, a, b), D.denominateur(m));
	}
			
	public double safeDiv(double a, double b){
		if (b == 0){
			return 0;
		}
		return a/b;
	}
	
	public String otherKey(String s){
		if (s.equals("x")){
			return "y";
		}
		return "x";
	}
	
	//-----------------------------------//
	
	public MatriceAdjacence getMat(){
		return this.matrice;
	}
	
	public Point getPoint(){
		return this.point;
	}
	
	public double getDeg(){
		return this.degre + 0.5;
	}
}