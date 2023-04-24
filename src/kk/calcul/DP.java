
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul;

import java.lang.Math;
import java.util.ArrayList;

import kk.geometrie.Point;
import kk.geometrie.Segment;

/*----------------------------------------------------------------------------*/

public class DP{

	private MatriceAdjacence matrice;
	private Point point;
	
	public DP(MatriceAdjacence mat, Point p){	
		this.point = p;
		this.matrice = mat;
	}
	
	//------------------------------------------------------//
												
	public Point dEa(){
		Ressort[] paires = this.matrice.getPaires(this.point);
		double dEx = 0;
		double dEy = 0;
		Point milieu;
		for (Ressort r : paires){
				milieu = r.milieu();
				dEx += expression(milieu.getX(), r);
				dEy += expression(milieu.getY(), r);
		}
		return new Point(dEx, dEy);
	}
	
	public Point dE2a(){
		Ressort[] paires = this.matrice.getPaires(this.point);
		double dEx2 = 0;
		double dEy2 = 0;
		Point milieu;
		for (Ressort r : paires){
				milieu = r.milieu();
				dEx2 += expression2(milieu.getY(), r);
				dEy2 += expression2(milieu.getX(), r);
		}
		return new Point(dEx2, dEy2);
	}
			
	public Point dEab(){
		Ressort[] paires = this.matrice.getPaires(this.point);
		double dExy = 0;
		Point milieu;
		for (Ressort r : paires){
				milieu = r.milieu();
				dExy += expression(milieu.getX()*milieu.getY(), r);
		}
		return new Point(dExy, dExy);
	}
				
	public double safeDiv(double a, double b){
		if (b == 0){                            
			return 0;
		}
		return a/b;
	}
		
	public Double expression(Double coord, Ressort r){ //Pour dEx, dEy
		return r.kij * (coord - safeDiv(r.lij*coord, D(r, 0.5)));
	}

	public Double expression2(Double coord, Ressort r){ //Pour dEx2, dEy2
		return r.kij * (1 - safeDiv(r.lij*coord*coord, D(r, 1.5)));
	}
	
	public Double expression3(Double coord, Ressort r){ //Pour dExy
		return r.kij * safeDiv(r.lij*coord, D(r, 1.5));
	}
		
	public double D(Segment s, double degre){
		Point a = s.milieu();
		double x = a.getX();
		double y = a.getY();
		return Math.pow(x*x + y*y, degre);
	}	
	
	//-----------------------------------//
	
	public MatriceAdjacence getMat(){
		return this.matrice;
	}
	
	public Point getPoint(){
		return this.point;
	}
}