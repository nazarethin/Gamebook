
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul;

import kk.calcul.Ressort;
import kk.geometrie.Point;

import java.util.*;
import java.lang.Math;

/*----------------------------------------------------------------------------*/

public class MatriceAdjacence{

	private int len;
	private ArrayList<Point> points;		
	private int[][] shortPath; //Chemin le plus court entre toutes les pairs de points.
	private Ressort[][] matrice;
	
	public MatriceAdjacence(ArrayList<Point> points, int Lo, int diam){
		this.points = points;
		this.len = points.size();
		this.shortPath = initShortPath(points);
		this.matrice = initMatrice(points, Lo, diam);
	}
	
	public MatriceAdjacence(ArrayList<Point> points, int Lo, Point racine){
		this.points = points;
		this.len = points.size();
		this.shortPath = initShortPath(points);		
		this.matrice = initMatrice(points, Lo, this.longuestPath(racine));		
	}
	
	//------------------------------------------------------//

	/*
	* Retourne les valeurs de toutes les paires possibles entre le point donné et les autres.
	*/
	public Ressort[] getPaires(Point p){
		int index = this.points.indexOf(p);
		return this.matrice[index];
	}
						
	public int[][] initShortPath(ArrayList<Point> pts){
		int[][] matrice = new int[this.len][this.len];
		for (int i = 0; i < this.len; i++){			
			for (int j = 0; j < this.len; j++){
				if (pts.get(i).isLinked(pts.get(j))){
					matrice[i][j] = 1;
				}
				else if (i == j){
					matrice[i][j] = 0;
				}
				else {
					matrice[i][j] = 9999; //INF
				}
			}
		}
		FloydWarshall fw = new FloydWarshall(matrice, this.len);
		return fw.graph;
	}
	
	public Ressort[][] initMatrice(ArrayList<Point> pts, int Lo, int diam){
		Ressort[][] matrice = new Ressort[this.len][this.len];
		for (int i = 0; i < this.len; i++){			
			for (int j = 0; j < this.len; j++){
				matrice[i][j] = new Ressort(pts.get(i), pts.get(j), this.shortPath[i][j], Lo, diam);
			}
		}
		return matrice;
	}
	
	
	/*
	* Donne le chemin le plus long des plus court chemins, pour un point donné.
	* Note : Sur un graphe général, n'équivaut pas au plus long chemin.
	*		 Mais dans le cas des arbres, oui ! --Raccourci utilisé ici.
	*		 Si on souhaite avoir un module qui marche pour tout graphe
	* 		 ==> Faut implémenter un algo qui donne cette donnée.
	*/
	public int longuestPath(Point p){
		int[] arr = this.shortPath[this.points.indexOf(p)];
		Arrays.sort(arr);
		return arr[this.len-1];
	}


	//--------------------------------------------------------------//		
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