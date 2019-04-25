/** Tämä tallentaa syötetyn painon tiedostoon ja tulostaa sen graafille. Käyttää xChartia.
 * https://knowm.org/open-source/XChart/
 * @author Eve
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
		readFiles(dataBr, datesBr, data, dates);
	 
	    // Create Chart
	    XYChart chart = QuickChart.getChart("Pet Weight", "Days", "Weight (g)", "y(x)", xData, yData);
	 
	    // Show it
	    new SwingWrapper(chart).displayChart();
	}
	
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
	
	public static void readFiles(BufferedReader dataBr, BufferedReader datesBr, Scanner data, Scanner dates) throws IOException {
			//collect 5 last lines from dates
				List<String> datesLastLines = new LinkedList<String>();
				for(String tmp; (tmp = datesBr.readLine()) != null;) {
				    if (datesLastLines.add(tmp) && datesLastLines.size() > noOfData) {
				    	datesLastLines.remove(0);
				    }}
				
				//collect 5 last lines from dates
						List<String> dataLastLines = new LinkedList<String>();
						for(String tmp; (tmp = dataBr.readLine()) != null;) {
						    if (dataLastLines.add(tmp) && dataLastLines.size() > noOfData) {
						    	dataLastLines.remove(0);
						    }}
		
//		List<String> datesLastLines = readLastLines(datesBr);
//		List<String> dataLastLines = readLastLines(dataBr);
		
				System.out.println("Dates:" + datesLastLines);
				System.out.println("Data:" + dataLastLines);
				
				//read string list lines
				for (int i = 0; i < datesLastLines.size(); i++) {
					//convert string to double
					Double lastLinesDouble = Double.parseDouble(datesLastLines.get(i));
					//save double
					Double datesrivi = lastLinesDouble;
					xData[i] = datesrivi;
				}//while
				
				//read string list lines
				for (int i = 0; i < dataLastLines.size(); i++) {
					//convert string to double
					Double lastLinesDouble = Double.parseDouble(dataLastLines.get(i));
					//save double
					Double datarivi = lastLinesDouble;
					yData[i] = datarivi;
				}//while
		
//		xData = readLastLines(datesBr);
//		yData = readLastLines(dataBr);
				
				data.close();
				dates.close();
	}
	
//	public static double[] readLastLines(BufferedReader br) throws IOException {
//		double rivi = 0;
//		
//		//collect 5 last lines from dates
//		List<String> lastLines = new LinkedList<String>();
//		for(String tmp; (tmp = br.readLine()) != null;) {
//		    if (lastLines.add(tmp) && lastLines.size() > noOfData) {
//		    	lastLines.remove(0);
//		    }//if
//		}//for
//		
//		//read string list lines
//		for (int i = 0; i < lastLines.size(); i++) {
//			//convert string to double
//			Double lastLinesDouble = Double.parseDouble(lastLines.get(i));
//			//save double
//			rivi = lastLinesDouble;
//			yData[i] = rivi;
//		}//while
//		return yData;
//		//return lastLines;
//	}
}
