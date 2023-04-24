
/**
 * @author LARRIVAIN Daphne 22112005
 */
 
package view.kk;

import kk.geometrie.Point;
import kk.KamadaKawai;
import util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*;

import java.util.ArrayList;

//-----------------------------------------------//

public class BoutonGUI extends JPanel implements ActionListener{

	private KamadaKawai kk;
	private GrapheGui grapheGui;

	public BoutonGUI(GrapheGui grapheGui, KamadaKawai kk, String text){
		super();
		this.grapheGui = grapheGui;
		this.kk = kk;
		
		JButton b = new JButton(text);
		b.addActionListener(this);
				
		this.add(b);
		this.setOpaque(true);
		this.setBorder(new EmptyBorder(20, 0, 0, 0));			
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Bouton");
		this.kk.compute();
		this.kk.fireChange();
	}
}