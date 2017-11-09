import java.util.TreeSet;

/**
 * This class consists of a String word and all the line numbers that word
 * appears on
 * 
 * @author Sean Gibbons
 *
 */
public class IndexEntry implements Comparable<IndexEntry> {
	private String word;
	private TreeSet<Integer> numsList;

	public IndexEntry(String word) {
		this.word = word.toUpperCase();
		numsList = new TreeSet<Integer>();
	}

	/**
	 * Adds a line number to the IndexEntry if it does not already exist
	 * 
	 * @param num
	 *            the line number to be added
	 */
	public void add(int num) {
		if (!(numsList.contains(num)))
			numsList.add(num);
	}

	/**
	 * An accessor method that returns the word
	 * 
	 * @return the word for this entry
	 */
	public String getWord() {
		return word;
	}

	@Override
	public String toString() {
		String s = word + " ";
		for (Integer n : numsList) {
			if (!(n.equals(numsList.last())))
				s += n + ", ";
			else
				s += n + "\n";
		}

		return s;
	}

	@Override
	public int compareTo(IndexEntry o) {
		return this.word.compareTo(o.getWord());
	}
}
