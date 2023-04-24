
/**
 * @author LARRIVAIN Daphne 22112005
 */

package view.kk;

import kk.geometrie.Point;
import kk.KamadaKawai;
import kk.calcul.*;
import view.kk.*;
import util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*;

import java.util.ArrayList;

//-----------------------------------------------//

public class GrapheGui extends JFrame implements Listener{
			
	private KamadaKawai kk;
	private int zoom;
		
	public GrapheGui(KamadaKawai kk, int zoom){
		super("Graphe");
		this.kk = kk;
		this.zoom = zoom;
		kk.addListener(this);
										
		super.setContentPane(this.buildContentPane(new VueGraphe(this.kk, this.zoom)));
		super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
		super.pack();
		super.setVisible(true);
	}
	
	public JPanel buildContentPane(VueGraphe vg){
		JPanel content = new JPanel();
		content.add(this.buildMenu());
		content.add(this.buildGraphe(vg));
		content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
		return content;
	}
	
	public JScrollPane buildGraphe(VueGraphe vg){	
		//JPanel
		JPanel aff = new JPanel();
		aff.add(vg);
		//aff.setBorder(new EmptyBorder(20, 20, 20, 20));
		JScrollPane sp = new JScrollPane(aff);
		sp.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Graphe")));
		
		//Démarre au centre du scrollPane
		Rectangle bounds = sp.getViewport().getViewRect();
		Dimension size = sp.getViewport().getViewSize();
		int x = (size.width - bounds.width) / 6;
		int y = (size.height - bounds.height) / 6;
		sp.getViewport().setViewPosition(new java.awt.Point( x, y));	

		return sp;
	}
	
	public JPanel buildMenu(){
		Presets preset = new Presets();
		JPanel menu = new JPanel();
		menu.add(new BoutonGUI(this, this.kk, "Calculer"));
		
		menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
		menu.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Menu")));	
		return menu;
	}
	
	public void setKk(KamadaKawai newKk){	
		System.out.println("SetKK");
		if (this.kk != null){
			this.kk.dropListener(this);
		}
		newKk.addListener(this);
		this.kk = newKk;
	}
	
	@Override
	public void update(Object o){
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	@Override
	public Dimension getMaximumSize(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
}