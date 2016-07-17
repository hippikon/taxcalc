package digital.places.root;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class FileOps
{
	static void writeFile(final String ofile, final String outtext)
	{
		BufferedWriter writer = null;
		FileWriter fw = null;
		try
		{
			File file = new File(ofile);

			file.createNewFile();

			fw = new FileWriter(file.getAbsoluteFile(),true);
			writer = new BufferedWriter(fw);
			writer.write(outtext);
			writer.newLine();
		} 
		catch (IOException x) 
		{
		    System.err.format("IOException: %s%n", x);
		}
		finally
		{
			try
			{
				if (writer != null) writer.close();
				if (fw != null) fw.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	static String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}	
}