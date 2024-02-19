/**
 * WordID.java
 * @author Wei Quan Lai
 * @author Emir Elzein
 * @author Yun Chung Chang
 * @author Francis Chan
 * @author Gavin Nguyen
 */
public class WordID {
	private String word;
	private int id;
	
	/**
	 * Constructor for the WordID
	 */
	public WordID() {
		word = "";
		id = -1;
	}
	
	/**
	 * Constructor for the WordID
	 * @param word
	 * @param id
	 */
	public WordID(String word, int id) {
		this.word = word;
		this.id = id;
	}
	
	/**
	 * Overloading Constructor for WordID
	 * @param word
	 */
	public WordID(String word) {
		this.word = word;
		this.id = -1;
	}
	
	/**
	 * Returns the word of the WordID
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Returns the id of the wordID
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * updates the word of the wordID
	 * @param word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	
	/**
	 * updates the id of the wordID
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * returns the title, artist, year, name of leader, 
	 * and lyrics of the kpop song
	 * @return the details of a kpop song
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(word + " " + id);
		return result.toString();
	}
	
	/**
	 * Overides the equals method of kpop
	 * @return true if the words are the same
	 * false if words aren't the same
	 */
	@Override public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof WordID)) { // check data type
			return false;
		} else {
			WordID i = (WordID) o;
			if(i.word.equals(word)) {
				return true;
			}
			return false;
		}
	}
	
	/**
     * Overides the hashCode method for Kpop
     * It hashes by title
     * @return the hashCode
     */
    @Override public int hashCode() {
    	String key = word;
    	int sum = 0;
    	for(int i = 0; i < key.length(); i++) {
    		sum+= (int)key.charAt(i);
    	}
    	return sum;
    }
}
