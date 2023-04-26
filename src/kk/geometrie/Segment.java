
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.geometrie;

import kk.geometrie.Point;

import java.util.ArrayList;
import java.lang.Math;


/*----------------------------------------------------------------------------*/

public class Segment implements Comparable<Segment>{

	protected ArrayList<Point> points; //Couple de points (debut, fin)
	protected double dist;
		
	public Segment(Point p1, Point p2, Boolean addTo){
		this.points = new ArrayList<>();
		this.points.add(p1);
		this.points.add(p2);
		
		this.dist = p1.dist(p2);
		
		if (addTo){ //Si on souhaite qu'il se fasse connaître auprès des points.
			p1.addSeg(this);
			p2.addSeg(this);
		}
	}
	
	public Segment(Point p1, Point p2){
		this(p1, p2, false);
	}	
	
	/*--------------------------------------------------------------------------*/
	/* Accesseurs */
	/*--------------------------------------------------------------------------*/
	
	public ArrayList<Point> getPoints(){
		return this.points;
	}
	
	public double getDist(){
		return this.dist;
	}
		
	public Boolean contains(Point p){
		if (p == this.points.get(0) || p == this.points.get(1)){
			return true;
		}
		return false;
	}
	
	public Point milieu(){
		double x, y;
		x = this.points.get(0).getX() - this.points.get(1).getX();
		y = this.points.get(0).getY() - this.points.get(1).getY();
		return new Point(x, y);
	}
		
	/*--------------------------------------------------------------------------*/
	/* Méthodes */
	/*--------------------------------------------------------------------------*/

	@Override
	public int compareTo(Segment s){
        if (this.getDist() == s.getDist()){
			return 0;
			}
        else if (this.getDist() > s.getDist()){
			return 1;
		}
        return -1;
	}

	@Override
	public String toString(){
		return this.points.get(0).toString() + "--" + this.points.get(1).toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if ( ! (other instanceof Segment) ) {
			return false;
		}
		Segment o = (Segment)other;
		if (Math.abs(this.compareTo(o)) == 0){
			if (this.points.get(0) == o.getPoints().get(0)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int) this.getDist();
	}
}
