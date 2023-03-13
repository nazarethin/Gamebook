
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.gui;

import kk.geometrie.Point;

import java.awt.*; 
import javax.swing.*;

//-----------------------------------------------//

public class Boule extends JComponent{

	private int DIAM;
	private Color color;

	public Boule(int diam, Color color){
		this.DIAM = diam;
		this.color = color;
	}

	public Boule(int diam){
		this(diam, Color.black);
	}	

	//-----------------------------------------------//

	public void setColor(Color color){
		this.color = color;
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(this.DIAM, this.DIAM);
	}

	@Override
	public Dimension getMinimumSize(){
		return new Dimension(this.DIAM, this.DIAM);
	}

	@Override
	protected void paintComponent(Graphics g){
		g.setColor(this.color);
		g.fillOval(0, 0, this.DIAM, this.DIAM);
	}
}