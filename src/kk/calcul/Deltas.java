
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.calcul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.lang.Math;

import kk.geometrie.*;
import kk.calcul.matrice.*;
import kk.calcul.*;

/*----------------------------------------------------------------------------*/

public class Deltas{
	
	private HashMap<Double, Point> deltas;
	
	public Deltas(MatriceAdjacence matrice, ArrayList<Point> points){			
		this.deltas = new HashMap<Double, Point>();
		for(Point p : points){
			this.deltas.put(p.delta(matrice), p);
		}		
	}
	
	//-----------------------------------------------//
	
	public HashMap<Double, Point> getDeltas(){
		return this.deltas;
	}
			
	public double maxDelta(){
		return Collections.max(this.deltas.keySet());
	}
	
	public Point maxPoint(){
		return this.deltas.get(this.maxDelta());
	}
	
	public void remove(double d){
		this.deltas.remove(d);
	}
	
	public void put(double d, Point p){
		this.deltas.put(d, p);
	}
	
	public HashMap<Double, Point> get(){
		return this.deltas;
	}
	
	@Override
	public String toString(){
		return this.deltas.toString();
	}
}