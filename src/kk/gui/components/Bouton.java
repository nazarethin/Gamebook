
/**
 * @author LARRIVAIN Daphne 22112005
 */
 
package kk.gui.components;

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

public class Bouton extends JPanel implements ActionListener{

	private KamadaKawai kk;

	public Bouton(KamadaKawai kk){
		super();
		this.kk = kk;
		
		JButton b = new JButton("Calculer");
		b.addActionListener(this);
				
		this.add(b);
		this.setOpaque(true);
		this.setBorder(new EmptyBorder(20, 0, 0, 0));			
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		this.kk.fireChange();
	}
}