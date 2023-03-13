
/**
 * @author LARRIVAIN Daphne 22112005
 */

package kk.gui.util;

import kk.gui.util.*;
import java.util.ArrayList;

//------------------------------------------------------------------//

public abstract class ListenableModel implements Listenable{

	protected ArrayList<Listener> listeners;

	public ListenableModel(){
    this.listeners = new ArrayList<Listener>();
	}

  @Override
  public void addListener(Listener l){
    this.listeners.add(l);
  }

  @Override
  public void dropListener(Listener l){
    this.listeners.remove(l);
  }

  public abstract void fireChange();
}
