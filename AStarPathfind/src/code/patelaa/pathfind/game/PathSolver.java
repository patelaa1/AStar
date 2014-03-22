package code.patelaa.pathfind.game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import code.patelaa.pathfind.AStarPathFinder;
import code.patelaa.pathfind.FileMapLoader;
import code.patelaa.pathfind.GameMap;
import code.patelaa.pathfind.MapLoader;
import code.patelaa.pathfind.Path;
import code.patelaa.pathfind.PathFinder;
import code.patelaa.pathfind.Terrain;
import code.patelaa.pathfind.UnitMover;

/**
 * 
 * @author patelaa
 * This is my implementation, using the path finder
 * Generates a GUI with 16*16 icons depicting the terrain
 * Generates the SOLUTION text file.
 * 
 */
public class PathSolver extends JFrame {
	private static final long serialVersionUID = -7321655500356762440L;
	/** The map on which the units will move */
	private GameMap map;
	/** The path finder we'll use to search our map */
	private PathFinder finder;
	/** The last path found for the current unit */
	private Path path;

	/** The list of tile images to render the map */
	private Image[] tiles = new Image[6];
	/** The offscreen buffer used for rendering in the wonder world of Java 2D */
	private Image buffer;
	/** map filename, incl. path from application.home **/
	private String mapFilename;

	/**
	 * Create a new test game
	 */
	public PathSolver(String mapFilename) {
		super("PATHFIND Map");
		
		this.mapFilename = mapFilename;
		//load icons
		try {
			tiles[Terrain.GRASS.getId()] = ImageIO.read(getResource("res/grass.png"));
			tiles[Terrain.FORREST.getId()] = ImageIO.read(getResource("res/trees.png"));
			tiles[Terrain.MOUNTAIN.getId()] = ImageIO.read(getResource("res/mountain.png"));
			tiles[Terrain.WATER.getId()] = ImageIO.read(getResource("res/water.png"));
			tiles[Terrain.START.getId()] = ImageIO.read(getResource("res/hero.png"));
			tiles[Terrain.END.getId()] = ImageIO.read(getResource("res/home.png"));
		} catch (IOException e) {
			System.err.println("Failed to load resources: " + e.getMessage());
			System.exit(0);
		}

		int[] startPos = new int[2];
		int[] endPos = new int[2];

		try {
			//TODO: Load map from file open Dialogue 
			//this.mapFilename = "data/maps" + File.separator + "large_map2.txt";
			
			MapLoader mapLoader = new FileMapLoader(this.mapFilename);
			map = mapLoader.getMap();
			startPos = mapLoader.getStartPos();
			endPos = mapLoader.getEndPos();
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		
		 finder = new AStarPathFinder(map, 500, true);

		 addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		//TODO: centre dialogue on screen
		setSize(map.getWidthInTiles()*16+100, map.getHeightInTiles()*16+100);  //Dialog size
		setResizable(true);
		setVisible(true);
		
		
		path = finder.findPath(new UnitMover(Terrain.START.getId()), startPos[0], startPos[1], endPos[0], endPos[1]);
		repaint(0);
		
		try {
			this.writeSolutionMapFile();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	
	/**
     * prints solution to file <mapFilename>_solution.txt
     * <p>
     * @return 2D solution char array
     * @throws Exception
     */
    public char[][] writeSolutionMapFile() throws Exception  {
    	String outputFileName = this.mapFilename.replaceAll(".txt", "_")+"solution.txt";
    	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFileName)));
    	char[][] solution = new char[map.getWidthInTiles()][map.getHeightInTiles()];
		try {
    	System.out.println("\n\n*SOLUTION MAP:");
        for (int y1 = 0; y1 < map.getHeightInTiles(); y1++) {
        	String lineToLog = new String();
            for (int x1 = 0; x1 < map.getWidthInTiles(); x1++) {
                Terrain t = map.getTerrain(x1, y1);
                if (t == null)      // don't break if inconsistency in map data
                	solution[x1][y1] =' ';
                else
              
					if (path != null) {
						if (path.contains(x1, y1)) {
							solution[x1][y1] = '#';
						} else {
							solution[x1][y1] = t.getSymbol();
						}
					} 
                //TODO: put the path '#' traversed	
                
                writer.write(solution[x1][y1]);
                lineToLog += solution[x1][y1];
            }
            System.out.println(lineToLog);
            writer.newLine();
        }
        System.out.println("\nSolution file created: "+outputFileName);
    }  catch (Exception ex) {
    	throw ex;
    } finally {
    	try {
    		writer.close();
    	} catch (Exception ex) {
    	}
    }
        return solution;
    }	
	
	
	
	/**
	 * Utility method to load a resource based on a file reference
	 * 
	 * @param ref
	 *            The reference to the file to load
	 * @return The stream loaded from either the classpath or file system
	 * @throws IOException
	 *             Indicates a failure to read the resource
	 */
	private InputStream getResource(String ref) throws IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(ref);
		if (in != null) {
			return in;
		}

		return new FileInputStream(ref);
	}
	

	
	/**
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics graphics) {
		// create an offscreen buffer to render the map

		if (buffer == null) {
			buffer = new BufferedImage(map.getWidthInTiles()*16+100, map.getHeightInTiles()*16+100, BufferedImage.TYPE_INT_ARGB);
		}
		Graphics g = buffer.getGraphics();

		g.clearRect(0, 0, map.getWidthInTiles()*16+100, map.getHeightInTiles()*16+100);
		g.translate(50, 50);

		// cycle through the tiles in the map drawing the appropriate

		// image for the terrain and units where appropriate

		for (int x = 0; x < map.getWidthInTiles(); x++) {
			for (int y = 0; y < map.getHeightInTiles(); y++) {
				g.drawImage(tiles[map.getTerrain(x, y).getId()], x * 16, y * 16, null);
				if (map.getUnit(x, y) != 0) {
					g.drawImage(tiles[map.getUnit(x, y)], x * 16, y * 16, null);
				} else {
					if (path != null) {
						if (path.contains(x, y)) {
							g.setColor(Color.red);
							g.fillRect((x * 16) + 4, (y * 16) + 4, 7, 7);
						}
					}
				}
			}
		}


		// finally draw the buffer to the real graphics context in one
		// atomic action
		graphics.drawImage(buffer, 0, 0, null);
	}
	
}
