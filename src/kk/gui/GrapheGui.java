
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.gui;

import kk.geometrie.Point;
import kk.gui.VueGraphe;

import java.awt.*; 
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;

//-----------------------------------------------//

public class GrapheGui extends JFrame{

	public GrapheGui(ArrayList<Point> points){
		super("Graphe");
					
		super.setContentPane(new VueGraphe(points));
		super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
		super.pack();
		super.setVisible(true);
	}	
}