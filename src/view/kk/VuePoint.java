
/**
 * @author LARRIVAIN Daphne 22112005
 */

package view.kk;

import kk.geometrie.Point;
import kk.geometrie.Segment;

import java.awt.*; 
import javax.swing.*;
import java.util.*;

//-----------------------------------------------//

public class VuePoint extends JPanel{
	private int DIAM; //Diamètre de la boule et largeur de la vue
	private int HEIGHT;
	private Point point;
	private Boule boule;
	private JLabel label;

	public VuePoint(Point p, int diam){
		super(new GridLayout(2, 1));	
		this.DIAM = diam;
		this.point = p;
		this.boule = new Boule(DIAM);
		this.label = new JLabel(p.getValue().toString());
		
		this.add(this.boule);
		this.add(this.label);
	}

	//-------------------------------------------------//
	
	public int x(){
		return (int) this.point.getX();
	}

	public int y(){
		return (int) this.point.getY();
	}
	
	public int diam(){
		return this.DIAM;
	}
	
	public int height(){
		return (this.DIAM*2)+2;
	}
	
	public ArrayList<Segment> getSeg(){
		return this.point.getSeg();
	}
}