
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul.numerateur;

import kk.calcul.matrice.Ressort;
import kk.calcul.numerateur.*;

public class NumeSimple extends Numerateur{
		
	@Override
	public double numerateur(Ressort R, double a, double b){ //ici, b = D
		return R.k()*a*(b - R.l());
	}
}