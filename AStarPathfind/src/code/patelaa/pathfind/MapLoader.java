package code.patelaa.pathfind;

/**
 * 
 * @author Abdul Patel
 *
 */
public abstract class MapLoader {

	
	//TODO: Create Coordinate Class
	//store co-ordinates of start and end markers
	private int[] startPos;
	private int[] endPos;
	private GameMap map;
	
	public void setStartPos(int[] startPos) {
		this.startPos = startPos;
	}

	public void setEndPos(int[] endPos) {
		this.endPos = endPos;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}


	public int[] getStartPos() {
		return startPos;
	}

	public int[] getEndPos() {
		return endPos;
	}

	public GameMap getMap() {
		return map;
	}
	
	/**
	 * Any specific implementations to load/construct a GameMap
	 * @throws Exception
	 */
	public abstract void construct() throws Exception;
	
}
