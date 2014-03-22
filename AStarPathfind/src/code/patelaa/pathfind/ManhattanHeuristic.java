package code.patelaa.pathfind;

public class ManhattanHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, UnitMover, int, int, int, int)
	 */

	private int minimumCost;


	public ManhattanHeuristic(int minimumCost) {
		this.minimumCost = minimumCost;
	}

	public float getCost(TileBasedMap map, UnitMover mover, int x, int y, int tx, int ty) {
		return this.minimumCost * (Math.abs(x - tx) + Math.abs(y - ty));
	}

}
