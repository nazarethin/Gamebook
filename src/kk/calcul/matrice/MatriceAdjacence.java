
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul.matrice;

import kk.calcul.matrice.Ressort;
import kk.geometrie.Point;

import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Math;

/*----------------------------------------------------------------------------*/

public class MatriceAdjacence{
		
	private Ressort[][] matrice;
	private int len;
	private ArrayList<Point> points;
	
	public MatriceAdjacence(ArrayList<Point> points, int Lo, int diam){
		this.points = points;
		this.len = points.size();
		this.matrice = initMatrice(points, Lo, diam);
	}
	
	public MatriceAdjacence(ArrayList<Point> points){
		this(points, 0, 0);
	}
	
	//------------------------------------------------------//
	
	public Ressort[] getPaires(Point p){
		int index = this.points.indexOf(p);
		return this.matrice[index];
	}
			
	public Ressort[][] initMatrice(ArrayList<Point> pts, int Lo, int diam){
		Ressort[][] matrice = new Ressort[this.len][this.len];
		for (int i = 0; i < this.len; i++){			
			for (int j = 0; j < this.len; j++){
				Ressort r;
				if (pts.get(i).isLinked(pts.get(j))){
					r = new Ressort(pts.get(i), pts.get(j), 1, Lo, diam);
				}
				else {
					r = new Ressort(pts.get(i), pts.get(j), 0, Lo, diam);
				}
				matrice[i][j] = r;
			}
		}
		return matrice;
	}
		
	@Override
	public String toString(){
		String res = "            ";
		for (Point p : this.points){
			res += p.toString() + "       ";
		}
		res += "\n";
		for (int i = 0; i < this.len; i++){
			res += this.points.get(i) + " ";
			for (int j = 0; j < this.len; j++){
				res += this.matrice[i][j].toString() + "   ";
			}
			res += "\n";
		}
		return res;
	}
}