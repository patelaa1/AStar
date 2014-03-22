package code.patelaa.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;


public class MapFileReader 
{
	protected BufferedReader reader;
	protected String line;
	protected int index;
	
	/**
	 * Creates a reader
	 * @param file
	 * @throws java.io.IOException
	 *
	 */
	public MapFileReader(File file) throws java.io.IOException
	{
		this(new BufferedReader(new FileReader(file)));
	}

	
	private MapFileReader(BufferedReader aReader)
	{
		reader = aReader;
	}


	/**
	 *  Check if reader ready to give content
	 */
	public boolean isReady()
	{
		return (reader != null && line != null);
	}

	/**
	 * * Read line
	 */
	public String nextLine()
	{
		readNextLine();
		if (line == null) 
			return (null);
		while (line.length() == 0)  // skip blank lines
		{		
			readNextLine();
			if (line == null) 
				return (null);
		}

		return line;
	}

	/**
	 * Close reader
	 * @throws java.io.IOException
	 */
	public void close() throws java.io.IOException
	{
		reader.close();
		reader = null;
		line = null;
		index = 0;
	}
	
	
	/**
	 * * Read the next line from the file
	 */
	private void readNextLine()
	{
		try
		{
			line = reader.readLine();
		} catch (Exception ex)
		{
			line = null;
		}
	}
	

	/**
	 * @param file
	 * @return lines in file (rows)
	 * @throws IOException
	 */
	public static int countLines(File file) throws IOException {
	    LineNumberReader reader  = new LineNumberReader(new FileReader(file));
		int cnt = 0;
		@SuppressWarnings("unused")
		String lineRead;
		while ((lineRead = reader.readLine()) != null) {}
	
		cnt = reader.getLineNumber(); 
		reader.close();
		return cnt;
	}	

	
}