
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.gui;

import java.util.ArrayList;

import kk.geometrie.Point;
import kk.gui.Boule;

import java.awt.*; 
import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

//-----------------------------------------------//

public class VueGraphe extends JPanel{
	
	private int zoom = 10;
	private int DIM;
	private static final int DFLT_DIM = 500;
	private int DIAM = 2*zoom;

	public VueGraphe(ArrayList<Point> points, int dim){
		super(null);
		this.DIM = dim;			
		for (int i = 0; i < points.size(); i++){
			Boule b = new Boule(DIAM);
			b.setBounds((int) points.get(i).getX()*zoom, (int) points.get(i).getY()*zoom, this.DIAM, this.DIAM);
			this.add(b);
		}	
	}
	
	public VueGraphe(ArrayList<Point> points){	
		this(points, DFLT_DIM);
	}
	
	//-------------------------------------------------//
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(this.DIM, this.DIM);
	}

	@Override
	public Dimension getMinimumSize(){
		return new Dimension(this.DIM, this.DIM);
	}		
}