
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

public abstract class Panneau extends JPanel{
				
    public Menu frame;
    public EcranMenu menu;	
	
	public Bouton retourB;
			
	public Panneau(Menu frame, EcranMenu menu){
		super();	
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.frame = frame;
		this.menu = menu;
		this.retourB = new Bouton(this, null, "Retour");	
	}
		
	public abstract JPanel buildMenu();
}