import java.util.Scanner;
import java.io.*;

public class FileWriter {
	private static final Scanner reader = new Scanner(System.in);

	
	public static void main(String[] args) throws FileNotFoundException {
		String string = " ";
		PrintWriter writeToFile = new PrintWriter("\"C:\\\\Users\\\\Eve\\\\Documents\\\\TurkuAMK\\\\Ohjelmoinnin perusteet toisto\\\\PetWeight\\\\data.txt\"");
		System.out.println("Anna paino: ");
		string = reader.nextLine();
		writeToFile.println(string);
		writeToFile.close();

	}

}
