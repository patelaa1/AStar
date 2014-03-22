package code.patelaa.pathfind;

public class UnitMover implements Mover{
	 private int type;
	  
	  public UnitMover(int type)
	  {
	    this.type = type;
	  }
	  
	  public int getType()
	  {
	    return this.type;
	  }
}
