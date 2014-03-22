package code.patelaa.pathfind;

import java.io.File;
import java.io.IOException;

import code.patelaa.helpers.MapFileReader;

/**
 * Load map from file
 * @author Abdul Patel
 * 
 */
public class FileMapLoader extends MapLoader {
 
	private String mapFilename;
	
	/**
	 * 
	 * @param mapAbsoluteFilename - requires the absolute filename
	 * @throws Exception
	 */
	public FileMapLoader(String mapAbsoluteFilename) throws Exception {
		super();
		this.mapFilename = mapAbsoluteFilename;	
		this.construct();
	}
	
	@Override
	public void construct() throws IOException {
		
		File file = new File(mapFilename);
		if (!file.exists()) {
			throw new IOException("Map Creation Exception. Could not find file " + file.getAbsolutePath());
		}	
		int mapHeight;
		mapHeight = MapFileReader.countLines(file);
		System.out.println("***DEBUG::LINES=" + mapHeight);
		MapFileReader reader = new MapFileReader(file);  
		
		String line = reader.nextLine();
		int y = 0;
		
		this.setMap(new GameMap(line.length(), mapHeight)) ;
		while (line != null) {
			
			
			//build up the terrain
			for (int x = 0; x < line.length(); x++) {
				char terrainSymbol = line.charAt(x);
				Terrain t = Terrain.getTerrainBySymbol(terrainSymbol);
				getMap().fillArea(x, y, 1, 1, t);
				if (t == Terrain.START) {
				  getMap().setUnit(x, y, Terrain.START.getId());	
				  this.setStartPos(new int[2]);
				  this.getStartPos()[0] = x;
				  this.getStartPos()[1] = y;
				} 
				if (t == Terrain.END) {
					getMap().setUnit(x, y, Terrain.END.getId());
					  this.setEndPos(new int[2]);
					  this.getEndPos()[0] = x;
					  this.getEndPos()[1] = y;
				}
					
				
			}  // line
			line = reader.nextLine();
			y++;
		} // row
		try {
			reader.close();
		} catch (Exception e) {
		}

	}
	
}
