
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.geometrie;

import kk.geometrie.Point;

import java.util.*;
import java.lang.Math;

/*----------------------------------------------------------------------------*/

public class Polygone{
	
	private double diam;
	private ArrayList<Point> points;
	private int decalage;
	
	
	public Polygone(ArrayList<Point> points, double diam, int decalage){
		this.diam = diam;
		this.decalage = decalage;
		this.points = init(points);
	}
	
	public Polygone(ArrayList<Point> points, double diam){
		this(points, diam, 0);
	}
			
	//------------------------------------------------------//
	
	public Point rotate(Point M, Point O, double a){
		a *= Math.PI / 180; //angle
		double xM = M.getX() - O.getX();
		double yM = M.getY() - O.getY();
		M.setX(xM*Math.cos(a) +  yM*Math.sin(a) + O.getX());
		M.setY(- xM*Math.sin(a) + yM*Math.cos(a) + O.getY());
		return M;
	}
	
	public ArrayList<Point> init(ArrayList<Point> pts){
		Point O = new Point(this.diam/2, this.diam/2); //Déclare le centre	
		double angle = 360/pts.size();
		int i = 0;
		for (Point p : pts){
			p.setXY(this.diam/2, 0); 
			this.rotate(p, O, angle*i);
			i++;
			p.setXY(p.getX() + this.decalage , p.getY() + this.decalage);
		}		
		return pts;
	}
	
	public ArrayList<Point> getPoints(){
		return this.points;
	}	
}
