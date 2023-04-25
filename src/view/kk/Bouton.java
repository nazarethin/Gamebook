
package view.kk;

import kk.geometrie.Point;
import kk.KamadaKawai;
import view.kk.*;

import util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*;

import kk.calcul.Presets;

import java.util.ArrayList;

//-----------------------------------------------//

public class Bouton extends JPanel implements ActionListener{

	private KamadaKawai kk;
	private Panneau p;
	
	public Bouton(Panneau p, KamadaKawai kk, String text){
		super();
		this.kk = kk;
		this.p = p;
		
		JButton b = new JButton(text);
		b.addActionListener(this);
				
		this.add(b);
		this.setOpaque(true);
		this.setBorder(new EmptyBorder(20, 0, 0, 0));			
	}
		
	@Override
	public void actionPerformed(ActionEvent e){
        if (this == this.p.retourB) {
			this.p.frame.set(this.p.menu);
        }
		else {
			if (this.p instanceof PanneauChoix){
				PanneauChoix pc = (PanneauChoix) this.p;
				if (this == pc.carreB){
					pc.frame.set(new PanneauGraphe(pc.frame, pc.menu, new Presets().grapheCube()));
				}
				else if (this == pc.temoinB){
					pc.frame.set(new PanneauGraphe(pc.frame, pc.menu, new Presets().grapheTemoin()));
				}
				else if (this == pc.txtB){
					pc.frame.set(new PanneauGraphe(pc.frame, pc.menu, new Presets().grapheTxt(), 1));
				}				
				else if (this == pc.jsonB){
					pc.frame.set(new PanneauGraphe(pc.frame, pc.menu, new Presets().grapheJson(), 1));
				}
			}
			else {
				this.kk.compute();
				this.kk.fireChange();
			}
		}
	}
}