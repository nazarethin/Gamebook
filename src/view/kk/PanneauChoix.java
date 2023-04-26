
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

public class PanneauChoix extends Panneau{
				
	
	public Bouton carreB;
	public Bouton temoinB;
	public Bouton txtB;
	public Bouton jsonB;
		
	public PanneauChoix(Menu frame, EcranMenu menu){
		super(frame, menu);
		this.carreB = new Bouton(this, null, "Graphe Cube");
		this.temoinB = new Bouton(this, null, "Graphe Temoin");	
		this.txtB = new Bouton(this, null, "Graphe Text");	
		this.jsonB = new Bouton(this, null, "Graphe Json");
		this.add(this.buildMenu());
	}
		
	@Override
	public JPanel buildMenu(){
		JPanel menu = new JPanel();
		menu.add(super.retourB);
		menu.add(this.carreB);
		menu.add(this.temoinB);
		menu.add(this.txtB);
		menu.add(this.jsonB);
		menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
		menu.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Choix du graphe")));	
		return menu;
	}
}