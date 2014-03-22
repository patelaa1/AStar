package code.patelaa.pathfind.game;

public class Main {

	 /**
	 * Entry point to our simple test game
	 * 
	 * @param argv
	 *            The arguments passed into the game
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println ("Usage: code.patelaa.pathfind.game.Main <path-to-map\\mapfile>");
			System.exit(0);
		}

		PathSolver test = new PathSolver(args[0]);
	}

}
