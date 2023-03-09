
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul.numerateur;

import kk.calcul.matrice.Ressort;
import kk.calcul.numerateur.*;

public class NumeDouble extends Numerateur{
		
	@Override
	public double numerateur(Ressort R, double a, double b){
		return R.k()*R.l()*a*b;
	}
}