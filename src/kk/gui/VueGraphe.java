
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.gui;

import kk.geometrie.Point;
import kk.geometrie.Segment;
import kk.gui.*;
import kk.gui.util.*;
import kk.KamadaKawai;

import java.awt.*; 
import javax.swing.*;
import java.util.*;
import java.lang.Math;

//-----------------------------------------------//

public class VueGraphe extends JPanel implements Listener{
	
	private int zoom = 50;
	private int DIM;
	private KamadaKawai algo;
	private ArrayList<VuePoint> vues = new ArrayList<>();


	public VueGraphe(KamadaKawai kk){
		super(null);
		this.algo = kk;
		this.algo.addListener(this);		
		this.DIM = (int) kk.getRes()*zoom;			
		for (Point p : kk.getPoints()){
			VuePoint v = new VuePoint(p, 10); //DIAM FIXE
			this.vues.add(v);
			this.add(v);
			v.setBounds((int) v.x()*zoom, (int) v.y()*zoom, v.height(), v.height());
		}	
	}
		
	//-------------------------------------------------//
				
	@Override
	protected void paintComponent(Graphics g){	
		for (VuePoint v : this.vues){				
			for (Segment s : v.getSeg()){
				ArrayList<Point> pts = s.getPoints();
				g.drawLine((int) pts.get(0).getX()*zoom + (v.diam()/2),
						   (int) pts.get(0).getY()*zoom + (v.diam()/2),
						   (int) pts.get(1).getX()*zoom + (v.diam()/2),
						   (int) pts.get(1).getY()*zoom + (v.diam()/2));
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
		System.out.println(o.toString());
		for (VuePoint v : this.vues){ 
			v.setBounds((int) v.x()*zoom, (int) v.y()*zoom, v.height(), v.height());
		}
	}
}