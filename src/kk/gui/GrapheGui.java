
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.gui;

import kk.geometrie.Point;
import kk.KamadaKawai;
import kk.gui.*;
import kk.gui.util.*;
import kk.gui.components.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*;

import java.util.ArrayList;

//-----------------------------------------------//

public class GrapheGui extends JFrame implements Listener{
					
	public GrapheGui(KamadaKawai kk, int zoom){
		super("Graphe");
		kk.addListener(this);
	
		//JPanel
		JPanel vueGraphe = new JPanel();
		vueGraphe.add(new VueGraphe(kk, zoom));
		vueGraphe.setBorder(new EmptyBorder(20, 20, 20, 20));
		JScrollPane sp = new JScrollPane(vueGraphe);
        sp.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize()); //DIMENSION DU MONITEUR		
		sp.setOpaque(true);
		sp.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Graphe")));
	
		//Content
		JPanel content = new JPanel();
		content.add(new Bouton(kk));
		content.add(sp);
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
						
		super.setContentPane(content);
		super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
		super.pack();
		super.setVisible(true);
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