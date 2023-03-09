
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul.numerateur;

import kk.calcul.matrice.Ressort;
import kk.calcul.numerateur.*;

public abstract class Numerateur implements NumeInterface{
		
	@Override
	public abstract double numerateur(Ressort R, double a, double b);
}