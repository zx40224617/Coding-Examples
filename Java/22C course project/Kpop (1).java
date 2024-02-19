/**
 * Kpop.java
 * @author Wei Quan Lai
 * @author Emir Elzein
 * @author Yun Chung Chang
 * @author Francis Chan
 * @author Gavin Nguyen
 */

public class Kpop implements Comparable<Kpop>{
	private String title;
	private String artist;
	private int year;
	private String lyrics;
	
	/**
	 * Default constructor for Kpop
	 */
	public Kpop() {
		title = "none"; 
		artist = "none"; 
		lyrics = "none";
		year = 0;
	}
	
	/**
	 * Parameter constructor for Kpop
	 * @param title
	 * @param artist
	 * @param year
	 * @param lyrics
	 */
	public Kpop(String title, String artist) {
		this.title = title;
		this.artist = artist; 
		lyrics = "none";
		year = 0;
	}
	
	/**
	 * Parameter constructor for Kpop
	 * @param title
	 * @param artist
	 * @param year
	 * @param lyrics
	 */
	public Kpop(String title, String artist, int year, String lyrics) {
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.lyrics = lyrics;
	}
	
	/**
	 * returns the title of the song
	 * @return title of the song
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * returns the artist of the song
	 * @return artist of the song
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * return year of the song produced
	 * @return year of the song produced
	 */
	public int getYear() {
		return year;
	}
	
	
	/**
	 * returns the lyrics of the song
	 * @return lyrics of the kpop song
	 */
	public String getLyrics() {
		return lyrics;
	}
	
	/**
	 * Update the title of the song
	 * @param title a new song title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Update the name of the artist
	 * @param artist name of the new artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * Update the year of the song produced
	 * @param year new year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Update the lyrics of the song
	 * @param lyrics the new lyrics
	 */
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
	/**
	 * returns the title, artist, year, name of leader, 
	 * and lyrics of the kpop song
	 * @return the details of a kpop song
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Title: " + title + "\n");
		result.append("Artist: " + artist + "\n");
		result.append("Year: " + year + "\n");
		result.append("Lyrics: " + lyrics + "\n");
		return result.toString() + "\n";
	}
	
	/**
     * Overrides the equals method for Card
     * Compares Title, artist, Year, Leader, Lyrics
     * @param obj another Object to compare for
     * equality
     * @return whether if the obj given is equals with the Kpop class
     */
    @Override public boolean equals(Object obj) {
        if (obj == this) {
        	return true;
        	
        } else if (!(obj instanceof Kpop)) { 
        	return false;
        	
        } else {
        	Kpop o = (Kpop)obj;
        	//Check
        	if(title.equalsIgnoreCase(o.title) && artist.equalsIgnoreCase(o.artist)) {
        		return true;
        	} else {
        		return false;
        	}
        }
    }
	
	/**
	 * Overides the compareTo method for Kpop
	 * compares by Title 
	 * followed by artist, Year, Leader and Lyrics
	 * @param kpop the class Kpop to compare
	 * @return a negative number if it's smaller,
	 * a positive number if it's bigger
	 * 0 if they are equal
	 */
    @Override public int compareTo(Kpop kpop) {
    	if (this == kpop) return 0;
    	else if (title.compareTo(kpop.title) < 0) return -1;
    	else if (title.compareTo(kpop.title) > 0) return 1;
    	else if (artist.compareTo(kpop.artist) < 0) return -1;
    	else if (artist.compareTo(kpop.artist) > 0) return 1;
    	else if (year < kpop.year) return -1;
    	else if (year > kpop.year) return 1;
    	else if (lyrics.compareTo(kpop.lyrics) < 0) return -1;
    	else if (lyrics.compareTo(kpop.lyrics) < 0) return 1;
    	else return 0;
    }
    
    /**
     * Overides the hashCode method for Kpop
     * It hashes by title
     * @return the hashCode
     */
    @Override public int hashCode() {
    	String key = title.toLowerCase() + artist.toLowerCase();
    	int sum = 0;
    	for(int i = 0; i < key.length(); i++) {
    		sum+= (int)key.charAt(i);
    	}
    	return sum;
    }
}
