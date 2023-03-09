
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul.denominateur;

import java.lang.Math;
import kk.calcul.matrice.Ressort;
import kk.geometrie.Point;

/*----------------------------------------------------------------------------*/

public class Denominateur{
		
	private double p; //puissance
	
	public Denominateur(double p){
		this.p = p;
	}
		
	public double denominateur(Point pt){
		double x = pt.getX();
		double y = pt.getY();
		return Math.pow(x*x + y*y, this.p);
	}
}