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

		String a1 = "";
		String a2 = "";

		// Checks if there are supplied file names, and, if not, then asks the user for
		// the necessary files
		if (args.length < 2) {
			System.out.println("You did not input enough files");
			Scanner kb = new Scanner(System.in);
			System.out.println("Please type the name of the input file to be indexed");
			a1 = kb.nextLine();
			System.out.println("Please type the name of the output file where the index is to be sent");
			a2 = kb.nextLine();
			kb.close();

			// Creates a new output file if the user refused to input one by taking the
			// input file and concatenating "Index" to the end
			if ("".equals(a2)) {
				int ext = a1.lastIndexOf(".");
				try {
					a2 = a1.substring(0, ext) + "Index" + a1.substring(ext);
				} catch (IndexOutOfBoundsException ex) {
					// if there are no periods in the, creates it with the default extension of
					// ".txt"
					a2 = a1 + "Index.txt";
				}
			}
		} else {
			a1 = args[0];
			a2 = args[1];
		}
		// Creates the input Scanner and the output PrintWriter files
		Scanner input = fileToScanner(a1);
		PrintWriter output = outputFile(a2);

		// Tests IndexEntry by itself
		output.println("Tests for IndexEntry" + "\n***********************\n");

		output.println("Create a new IndexEntry");
		IndexEntry w1 = new IndexEntry("dog");
		w1.add(4);
		w1.add(2);
		w1.add(34);
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

		output.println("Creates a new DocumentIndex, which should be empty at this point");
		DocumentIndex doc = new DocumentIndex();
		output.println(doc);

		output.println("Indexes all the lines in the given file");
		int currentLine = 1;
		while (input.hasNextLine()) {
			doc.addAllWords(input.nextLine(), currentLine++);
		}

		// Prints the index to the output file
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
			System.out.print("That file \"" + fName + "\" does not exist ");
			return null;

		}
		if (!words.hasNext())
			throw new IllegalArgumentException("That file is empty");
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
			System.out.print("Cannot open \"" + fName + " \"");
			return null;

		}

		return output;
	}
}
