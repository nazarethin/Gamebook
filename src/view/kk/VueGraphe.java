
/**
 * @author LARRIVAIN Daphne 22112005
 */

package view.kk;

import kk.geometrie.Point;
import kk.geometrie.Segment;
import kk.KamadaKawai;

import util.*;

import java.awt.*; 
import javax.swing.*;
import java.util.*;
import java.lang.Math;

//-----------------------------------------------//

public class VueGraphe extends JPanel implements Listener{
	
	private int DIM;
	private int zoom;
	private int decalage;
	private int taillePoint;
	private KamadaKawai algo;
	private ArrayList<VuePoint> vues = new ArrayList<>();

	public VueGraphe(KamadaKawai kk, int zoom, int taillePoint){
		super(null);
		this.zoom = zoom;
		this.taillePoint = taillePoint;
		this.algo = kk;
		this.algo.addListener(this);		
		this.DIM = (int) kk.getRes()*zoom*2;
		this.decalage = this.DIM/2 - ((((int) kk.getRes())*zoom)/2);
		for (Point p : kk.getPoints()){
			VuePoint v = new VuePoint(p, taillePoint);
			this.vues.add(v);
			this.add(v);
			v.setBounds((int) v.x()*zoom + decalage, (int) v.y()*zoom + decalage, v.height(), v.height());
		}	
	}

	public VueGraphe(KamadaKawai kk, int zoom){
		this(kk, zoom, 10);
	}
	
	public VueGraphe(KamadaKawai kk){
		this(kk, 50, 10);
	}	
		
	//-------------------------------------------------//
	
	public int getDim(){
		return this.DIM;
	}
				
	@Override
	protected void paintComponent(Graphics g){	
		for (VuePoint v : this.vues){				//Pose les segments
			for (Segment s : v.getSeg()){
				ArrayList<Point> pts = s.getPoints();
				g.drawLine((int) pts.get(0).getX()*zoom + (v.diam()/2) + decalage,
						   (int) pts.get(0).getY()*zoom + (v.diam()/2) + decalage,
						   (int) pts.get(1).getX()*zoom + (v.diam()/2) + decalage,
						   (int) pts.get(1).getY()*zoom + (v.diam()/2) + decalage);
			}
		}		
	}	
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(this.DIM, this.DIM);
	}

	@Override
	public Dimension getMinimumSize(){
		return new Dimension(this.DIM, this.DIM);
	}

	@Override
	public void update(Object o){
		System.out.println();
		for (VuePoint v : this.vues){ 
			v.setBounds((int) v.x()*zoom + decalage, (int) v.y()*zoom + decalage, v.height(), v.height());
		}
	}
}