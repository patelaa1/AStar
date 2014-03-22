package code.patelaa.pathfind;



/**
 * 
 * @author Abdul Patel
 *
 */
public class GameMap implements TileBasedMap{
	/** The map width in tiles */
	private int mWidth;
	/** The map height in tiles */
	private int mHeight;
	
	
	/** The terrain settings for each tile in the map */
	private Terrain[][] terrain;
	/** The unit (special objects) in each tile of the map */
	private int[][] units;
	/** Indicator if a given tile has been visited during the search */
	private boolean[][] visited;
	
	/**
	 * Create a new map
	 */
	public GameMap(int width, int height) {
		this.mHeight = height;
		this.mWidth = width;
	  
		terrain = new Terrain[mWidth][mHeight];
		units = new int[mWidth][mHeight];
		visited = new boolean[mWidth][mHeight];
	}
	


	/**
	 * Fill an area with a given terrain type
	 * 
	 * @param x The x coordinate to start filling at
	 * @param y The y coordinate to start filling at
	 * @param width The width of the area to fill
	 * @param height The height of the area to fill
	 * @param type The terrain type to fill with
	 */
	public void fillArea(int x, int y, int width, int height, Terrain type) {
		for (int xp=x;xp<x+width;xp++) {
			for (int yp=y;yp<y+height;yp++) {
				terrain[xp][yp] = type;
			}
		}
	}
	
	/**
	 * Clear the array marking which tiles have been visted by the path 
	 * finder.
	 */
	public void clearVisited() {
		for (int x=0;x<getWidthInTiles();x++) {
			for (int y=0;y<getHeightInTiles();y++) {
				visited[x][y] = false;
			}
		}
	}
	
	/**
	 * @see TileBasedMap#visited(int, int)
	 */
	public boolean visited(int x, int y) {
		return visited[x][y];
	}
	
	/**
	 * Get the terrain at a given location
	 * 
	 * @param x The x coordinate of the terrain tile to retrieve
	 * @param y The y coordinate of the terrain tile to retrieve
	 * @return The terrain tile at the given location
	 */
	public Terrain getTerrain(int x, int y) {
		return terrain[x][y];
	}
	
	/**
	 * Get the unit at a given location
	 * 
	 * @param x The x coordinate of the tile to check for a unit
	 * @param y The y coordinate of the tile to check for a unit
	 * @return The ID of the unit at the given location or 0 if there is no unit 
	 */
	public int getUnit(int x, int y) {
		return units[x][y];
	}
	
	/**
	 * Set the unit at the given location
	 * 
	 * @param x The x coordinate of the location where the unit should be set
	 * @param y The y coordinate of the location where the unit should be set
	 * @param unit The ID of the unit to be placed on the map, or 0 to clear the unit at the
	 * given location
	 */
	public void setUnit(int x, int y, int unit) {
		units[x][y] = unit;
	}
	
	/**
	 * @see TileBasedMap#blocked(UnitMover, int, int)
	 */
	public boolean blocked(UnitMover mover, int x, int y) {
		
		 int unit = ((UnitMover) mover).getType();
		
		 // man(starter) can't move across water
		 if (unit == Terrain.START.getId()) {
			//return terrain[x][y] != GRASS;
			if (terrain[x][y] == Terrain.WATER) {
			  return true;
			} else {
			  return false;	
			}
		 }
		
		// unknown unit so everything blocks

		return true;
	}

	/**
	 * @see TileBasedMap#getCost(UnitMover, int, int, int, int)
	 */
	public float getCost(UnitMover mover, int sx, int sy, int tx, int ty) {
		
		if(Math.abs(sx-tx) == 1 && Math.abs(sy-ty) == 1)
			return (float)Math.sqrt(1 + 1) * terrain[tx][ty].getCost();  //Diagonal move
		return 1 * terrain[tx][ty].getCost();
		
		
	}

	/**
	 * @see TileBasedMap#getHeightInTiles()
	 */
	public int getHeightInTiles() {
		return mHeight;
	}

	/**
	 * @see TileBasedMap#getWidthInTiles()
	 */
	public int getWidthInTiles() {
		return mWidth;
	}

	/**
	 * @see TileBasedMap#pathFinderVisited(int, int)
	 */
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}
	

	
}
