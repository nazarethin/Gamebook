
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul.matrice;

import kk.geometrie.Segment;
import kk.geometrie.Point;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/*----------------------------------------------------------------------------*/

public class Ressort extends Segment{

	private static final DecimalFormat df = new DecimalFormat("0.0"); //arrondi à une décimale pour affichage.
	private static final double K = 2;
	
	private double dij; //Distance entre deux points. 1 si lie deux points, 0 sinon.
	private double lij; //Longueur du ressort.
	private double kij; //Force du ressort.
			
	/* Lo --> Côté de la zône d'affichage.
	   diam --> Distance entre points les + éloignés graphe. */ 
	   
	public Ressort(Point p1, Point p2, double dij, int Lo, int diam){ 
		super(p1, p2);
		this.dij = dij;
		
		if (diam != 0){
			this.lij = (Lo*this.dij) / diam;
		}
		else {
			this.lij = 0;
		}
		
		if (dij != 0){
			this.kij = this.K / (this.dij*this.dij);
		}
		else {
			this.kij = 0;
		}
	}
	
	public Ressort(Point p1, Point p2, double dij){ 
		this(p1, p2, dij, 0, 0);
	}
	
	public Ressort(double dij){ 
		this(null, null, dij, 0, 0);
	}
	
	//-----------------------------------------------------//
	
	
	public Point milieu(){
		double x, y;
		x = this.points.get(0).getX() - this.points.get(1).getX();
		y = this.points.get(0).getY() - this.points.get(1).getY();
		return new Point(x, y);
	}
		
	@Override
	public String toString(){
		return "(" + this.df.format(this.dij) + "|" + this.df.format(this.lij) + "|" + this.df.format(this.kij) + ")";
	}
	
		
	public double d(){
		return this.dij;
	}
	
	public double l(){
		return this.lij;
	}
	
	public double k(){
		return this.kij;
	}
}
