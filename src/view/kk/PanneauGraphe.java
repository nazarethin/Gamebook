
package view.kk;

import kk.geometrie.Point;
import kk.KamadaKawai;
import kk.calcul.*;

import view.Menu;
import view.EcranMenu;

import util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*;

import java.util.ArrayList;

//-----------------------------------------------//

public class PanneauGraphe extends Panneau implements Listener{
			
	private KamadaKawai kk;
	private int zoom;
	
	private Bouton computeB;
			
	public PanneauGraphe(Menu frame, EcranMenu menu, KamadaKawai kk, int zoom){
		super(frame, menu);
			
		this.kk = kk;
		kk.addListener(this);			
		this.zoom = zoom;	
		
		this.computeB = new Bouton(this, this.kk, "Calculer");	
		this.buildContentPane(new VueGraphe(this.kk, this.zoom));
	}
	
	public PanneauGraphe(Menu frame, EcranMenu menu, KamadaKawai kk){
		this(frame, menu, kk, 500/((int) kk.getRes()));
	}	
	
	public PanneauGraphe(Menu frame, EcranMenu menu){
		this(frame, menu, new Presets().grapheCube(), 500/((int) new Presets().grapheCube().getRes()));
	}
	
	@Override
	public JPanel buildMenu(){
		JPanel menu = new JPanel();
		menu.add(new JLabel("Note : petit temps de latence"));
		menu.add(this.computeB);
		menu.add(super.retourB);				
		menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
		menu.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Menu")));	
		return menu;
	}	
	
	public void buildContentPane(VueGraphe vg){
		this.add(this.buildMenu());
		this.add(this.buildGraphe(vg));
	}
	
	public JScrollPane buildGraphe(VueGraphe vg){	
		JPanel aff = new JPanel();
		aff.add(vg);
		JScrollPane sp = new JScrollPane(aff);
		sp.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Graphe")));
		return sp;
	}
				
	@Override
	public void update(Object o){
		repaint();
	}
}