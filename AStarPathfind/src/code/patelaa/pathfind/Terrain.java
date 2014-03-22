/**
 * 
 */
package code.patelaa.pathfind;

/**
 * @author patelaa
 *
 */
public enum Terrain {
	

	GRASS(0, 1, true, '.'),
	FORREST(1, 2, true, '*'),
	MOUNTAIN(2, 3, true, '^'),
	WATER(3, 99, false, '~'),
	START(4, 1, true, '@'),
	END(5, 1, true, 'X');

	private int id;
	private int cost;
	private boolean walkable;
	private char symbol;

	Terrain(int id, int cost, boolean walkable, char symbol) {
		this.id = id;
		this.cost = cost;
		this.walkable = walkable;
		this.symbol = symbol;
	}
    
	public int getId() {
		return id;
	}
	
	public int getCost() {
		return cost;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	public char getSymbol() {
		return symbol;
	}

	
	/**
	 * 'Factory' method to get correct Terrain from symbol
	 * @param symbol
	 * @return
	 */
	public static Terrain getTerrainBySymbol(char symbol) {
		
	  switch(symbol) {
	    case '.': return GRASS; 
	    case '*': return FORREST; 
	    case '^': return MOUNTAIN; 
	    case '~': return WATER; 
	    case '@': return START;   
	    case 'X': return END; 
	    default: { System.out.println("WARNING: UNKNOWN symbol(" + symbol + "), defaulting to GRASS");
	    		   return GRASS;
	    }
	  }
	}
}
