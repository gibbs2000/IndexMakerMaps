import java.util.TreeMap;

/**
 * This class consists of a TreeMap of IndexEntrys keyed to the String word that
 * they describe
 * 
 * @author Sean Gibbons
 *
 */

/**
 * Not sure why but eclipse told me I need this (something about "does not
 * declare a static final serialVersionUID field of type long")
 */
@SuppressWarnings("serial")

public class DocumentIndex extends TreeMap<String, IndexEntry> {
	/**
	 * Default constructor, creates a DocumentIndex of a default size
	 */
	public DocumentIndex() {
		super();

	}

	/**
	 * Adds a line number to a given word in the DocumentIndex. If that word does
	 * not exist in the DocumentIndex, adds a new IndexEntry and inserts in the
	 * appropriate order
	 * 
	 * @param word
	 *            the word which is receiving a new line number
	 * @param num
	 *            the line number to be added to a given word
	 */
	public void addWord(String word, int num) {
		word = word.toUpperCase();
		if (!this.containsKey(word))
			this.put(word, new IndexEntry(word));
		this.get(word).add(num);
	}

	/**
	 * Extracts all the words from a given String and calls addWord for each word in
	 * that String
	 * 
	 * @param str
	 *            the String from which words will be extracted
	 * @param num
	 *            the line number which the given String came from
	 */
	public void addAllWords(String str, int num) {
		String[] words = str.split("\\W+");
		for (String s : words) {
			if (!s.isEmpty())
				addWord(s, num);

		}
	}

	@Override
	public String toString() {
		String s = "";
		for (IndexEntry i : this.values())
			s += i;
		return s;
	}

}
