/** Tämä tallentaa syötetyn painon tiedostoon ja tulostaa sen graafille. Käyttää xChartia.
 * <a href="https://knowm.org/open-source/XChart/>xChart</a>
 * 
 * @author Eve
 * @version 0.1
 */

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class xchart_test2 {
	private static final Scanner reader = new Scanner(System.in);
	private static final String dataPath = System.getProperty("user.dir") + "\\data.txt";
	private static final String datesPath = System.getProperty("user.dir") + "\\dates.txt";
	public static int noOfData = 5;
	public static double[] xData = new double[noOfData]; //Time axis
	public static double[] yData = new double[noOfData]; //Value axis
	

	public static void main(String[] args) throws IOException {
		FileInputStream dataIn = new FileInputStream(dataPath);
		FileInputStream datesIn = new FileInputStream(datesPath);
		BufferedReader dataBr = new BufferedReader(new InputStreamReader(dataIn));
		BufferedReader datesBr = new BufferedReader(new InputStreamReader(datesIn));
		Scanner data = new Scanner(new File(dataPath));
		Scanner dates = new Scanner(new File(datesPath));
		
		writeToFile();
		xData = readLastLines(datesBr, noOfData);
		yData = readLastLines(dataBr, noOfData);
//		readFiles(dataBr, datesBr, data, dates);
	 
	    // Create Chart
	    XYChart chart = QuickChart.getChart("Pet Weight", "Days", "Weight (g)", "y(x)", xData, yData);
	 
	    // Show it
	    new SwingWrapper(chart).displayChart();
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public static void writeToFile() throws IOException {
		String string = "";
		FileWriter writeToFile = new FileWriter(dataPath, true);
		
		System.out.println("Anna paino: ");
		string = reader.nextLine();
		
		while (true) {
			try {
				double value = Double.parseDouble(string);
				break;
			} catch (NumberFormatException ignore) {
				System.out.println("Virheellinen syöte, huolii vain numeroita.");
				System.out.println("Anna paino: ");
				string = reader.nextLine();
			}
		}
		
		writeToFile.write("\n" + string);
	    writeToFile.close();
	}//writeToFile

	/**Reads the last five (noOfData) lines of both Data and Dates -files.
	 * 
	 * @param BufferedReader, either Data or Dates
	 * @return the last five lines as double list
	 * @throws IOException
	 */
	public static double[] readLastLines(BufferedReader br, int noOfData) throws IOException {
		double rivi = 0;
		double[] data = new double[noOfData];
		
		//collect 5 last lines from dates
		List<String> lastLines = new LinkedList<String>();
		for(String tmp; (tmp = br.readLine()) != null;) {
		    if (lastLines.add(tmp) && lastLines.size() > noOfData) {
		    	lastLines.remove(0);
		    }//if
		}//for
		
		//read string list lines
		for (int i = 0; i < lastLines.size(); i++) {
			//convert string to double
			Double lastLinesDouble = Double.parseDouble(lastLines.get(i));
			//save double
			rivi = lastLinesDouble;
			data[i] = rivi;
		}//while
		return data;
		//return lastLines;
	}
}
