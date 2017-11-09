import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the main class that handles file reading and tests the other classes
 * 
 * @author Sean Gibbons
 *
 */
public class IndexMaker {

	public static void main(String[] args) {

		// Creates the input Scanner and the output PrintWriter files
		Scanner input = fileToScanner(args[0]);
		PrintWriter output = outputFile("fishIndex.txt");

		// Tests IndexEntry by itself
		output.println("Tests for IndexEntry" + "\n***********************\n");

		IndexEntry w1 = new IndexEntry("dog");
		w1.add(4);
		w1.add(2);
		w1.add(34);
		w1.add(4); // should not be added a second time
		output.println(w1);

		output.println("Adds another line number");
		w1.add(17);
		output.println(w1);

		output.println(
				"Attempts to add another line number, but since number is already in the entry, IndexEntry should remain unchanged");
		w1.add(17);
		output.println(w1);
		
		
		
		
		
		
		// Tests DocumentIndex thoroughly
		output.println("Tests for DocumentIndex" + "\n***********************\n");
		DocumentIndex doc = new DocumentIndex();
		int currentLine = 1;
		while (input.hasNextLine()) {
			doc.addAllWords(input.nextLine(), currentLine++);
		}

		output.println(doc);

		// Closes open Scanners and PrintWriters
		input.close();
		output.close();

	}

	/**
	 * Converts the given file to Scanner
	 * 
	 * @param fName
	 *            The String name of a file
	 * @param fileNum
	 *            The file number, used in case of exceptions or errors to tell user
	 *            which file failed
	 * @return A Scanner of the file with the given file name
	 */
	public static Scanner fileToScanner(String fName) {

		File fileName = new File(fName);
		Scanner words = null;

		try {
			words = new Scanner(fileName);
		} catch (FileNotFoundException ex) {
			System.out.print("Unable to Open Given File");
			return null;

		}
		if (!words.hasNext())
			throw new IllegalArgumentException("Given File is empty");
		return words;

	}

	/**
	 * Creates a file of the given name
	 * 
	 * @param fName
	 *            The name of the file to be created
	 * @return A PrintWriter of the same name as fName which can be manipulated and
	 *         then saved
	 */
	public static PrintWriter outputFile(String fName) {
		File fileName = new File(fName);

		PrintWriter output = null;

		try {
			output = new PrintWriter(fileName);
		} catch (FileNotFoundException ex) {
			System.out.print("Cannot open " + fName);
			return null;

		}

		return output;
	}
}
