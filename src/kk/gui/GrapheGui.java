
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.gui;

import kk.geometrie.Point;
import kk.KamadaKawai;
import kk.gui.*;
import kk.gui.util.*;

import java.awt.*; 
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;

//-----------------------------------------------//

public class GrapheGui extends JFrame implements ActionListener{
	
	private KamadaKawai kk;
	
	public GrapheGui(KamadaKawai kk){
		super("Graphe");
		this.kk = kk;
		
		JPanel content = new JPanel();		
		JButton b = new JButton("Calculer");
		b.addActionListener(this);
		content.add(b);
		content.add(new VueGraphe(kk));
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		
		super.setContentPane(content);
		super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
		super.pack();
		super.setVisible(true);
	}
	
	//-----------------------------------------------//

	@Override
	public void actionPerformed(ActionEvent e){
		this.kk.fireChange();
		repaint();
	}	
}