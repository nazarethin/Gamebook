package util;

//------------------------------------------------------------------//

public interface Listenable{
  public abstract void addListener(Listener l);
  public abstract void dropListener(Listener l);
}
