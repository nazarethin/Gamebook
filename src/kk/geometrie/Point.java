
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.geometrie;

import kk.geometrie.Segment;
import kk.calcul.*;
import kk.calcul.matrice.*;
import kk.calcul.denominateur.*;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Objects;
import java.util.HashMap;

/*----------------------------------------------------------------------------*/

public class Point{

	private double x, y;
	private int value;
	private ArrayList<Segment> segments;
	
	public Point(double x, double y, int value){
		this.x = x;
		this.y = y;
		this.value = value; //Ici, le numero de la page concernée.
		this.segments = new ArrayList<>();
	}
	
	public Point(double x, double y){
		this(x, y, -1); //-1 : valeur d'erreur, pas de page.
	}

	/*--------------------------------------------------------------------------*/
	/* Accesseurs */
	/*--------------------------------------------------------------------------*/

	public void addSeg(Segment seg){
		this.segments.add(seg);
	}
	
	public ArrayList<Segment> getSeg(){
		return this.segments;
	}
	
	public Boolean isLinked(Point p){
		if ( ! (p.equals(this)) ){			
			for (Segment s : this.segments){
				if (s.contains(p)){
					return true;
				}
			}
		}
		return false;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public HashMap<String, Double> getXY(){
		HashMap<String, Double> res = new HashMap<String, Double>();
		res.put("x", this.x);
		res.put("y", this.y);
		return res;
	}

	public int getPage(){
		return this.value;
	}
  

	/*--------------------------------------------------------------------------*/
	/* Méthodes */
	/*--------------------------------------------------------------------------*/

	public double delta(MatriceAdjacence m){
		Point dp = new DP(m, this, 1).get().get("dEa");
		Denominateur d = new Denominateur(0.5);
		return d.denominateur(dp);
	}


	public double dist(Point p){
		double x = p.getX() - this.x;
		double y = p.getY() - this.y;
		return Math.sqrt(x*x + y*y);
	}
	

	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setXY(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setXY(Point p){
		this.x = p.getX();
		this.y = p.getY();
	}

	@Override
	public String toString(){
		return "(" + this.x + "," + this.y + ")";
	}
	
	
	@Override
	public boolean equals(Object other){
		if (other == null) {
			return false;
		}
		if ( ! (other instanceof Point) ){
			return false;
		}
		Point o = (Point) other;
		return (this.x == o.x) && (this.y == o.y);
	}

	@Override
	public int hashCode(){
		return Objects.hash(this.x, this.y);
	}
}
