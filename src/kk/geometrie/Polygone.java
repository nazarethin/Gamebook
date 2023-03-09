
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.geometrie;

import kk.geometrie.Point;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Objects;

/*----------------------------------------------------------------------------*/

public class Polygone{
	
	private double diam;
	private ArrayList<Point> points;
	
	public Polygone(ArrayList<Point> points, double diam){
		this.diam = diam;
		this.points = this.init(points);
		//this.points = points;
	}
	
	public Polygone(ArrayList<Point> points){
		this(points, 1080);
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
	
	//placement par rotations sucessives. 
	public ArrayList<Point> init(ArrayList<Point> pts){
		Point O = new Point(this.diam/2, this.diam/2); //Déclare le centre
		pts.get(0).setXY(this.diam/2, 0); //Place le premier point		
		double angle = 360/pts.size();
		
		for (int i = 1; i < pts.size(); i++){
			Point p = pts.get(i);
			p.setXY(pts.get(i-1)); 
			this.rotate(p, O, angle);
		}
		return pts;
	}
	
	public ArrayList<Point> getPoints(){
		return this.points;
	}
}
