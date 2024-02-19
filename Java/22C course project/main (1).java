import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * mainMenu
 * @author Wei Quan Lai
 * @author Emir Elzein
 * @author Yun Chung Chang
 * @author Francis Chan
 * @author Gavin Nguyen
 */
 
public class main {
	private static int index = 0;
	private static HashTable<Kpop> songList = new HashTable<>(100000);
	private static HashTable<WordID> idList = new HashTable<>(100000);
	private static ArrayList<BST<Kpop>> invertedList = new ArrayList<>(100000);
	
	private static String stopwordlist[] = {"a", "ah", "an", "and","are", "around", "be", 
	"every", "for", "from","he", "i", "in", "is", "it", "my", "not", "oh", "on", "one","she", 
	"the","they","to", "uh", "under","we" ,"you"};
	
	public static void main(String[] args) {
		for(int i = 0; i < 100000; i++) {
			invertedList.add(new BST<Kpop>());
		}
		createSongListAndInvertedList();

		String song = "";
		String artist = "";
		String keyWord = "";
		

		// Repeats until 'q' (for quit) is inputed 
		char response = '\0';
		while (response != 'q') {
			Scanner input = new Scanner(System.in);
			response = displayMainMenu();
			/**
			 * u : uploadNewSong
			 * d : deleteSong
			 * s : displaySongDetails
			 * l : findSongWithKeyword
			 * m : modifySong
			 * t : displayStatistics
			 * q : quit
			 */

			//Query for what song to upload
			if (response == 'u') {
				System.out.print("Enter the name of the song you wish to upload: ");
				song = input.nextLine();
				uploadNewSong(song);
				System.out.println("Song has been added to list");
			} 
			
			//Query for what song to delete
			else if (response == 'd') {
				System.out.print("Enter the name of the song you wish to delete: ");
				song = input.nextLine();
				System.out.print("Enter the artist of the song you wish to delete: ");
				artist = input.nextLine();
				boolean exist  = deleteSong(song, artist);
				if(exist) {
					System.out.println("Song is deleted");
				} else {
					System.out.println("Song does not exist");
				}
			} 
			
			// Query for what song details to display
			else if (response == 's') {
				System.out.print("Enter the name of the song you are interested: ");
				song = input.nextLine();
				System.out.print("Enter the artist of the song you are interested: ");
				artist = input.nextLine();
				boolean exist  = displaySongDetails(song, artist);
				//it will return  back to here and print out the statement below
				if(!exist) {
					System.out.println("Song does not exist");
				}
			} 
			
			// Query for which song to modify
			else if(response == 'm') {
				System.out.print("Enter the name of the song you wish to modify: ");
				song = input.nextLine();
				System.out.print("Enter the artist of the song you wish to modify: ");
				artist = input.nextLine();
				boolean exist = modifySong(song, artist);
				if(!exist) {
					System.out.println("Song does not exist");
				} else {
					System.out.println("Song is modified");
				}
			} 
			
			// Query to find song with a keyword
			else if (response == 'l') {
				System.out.print("Enter a keyword: ");
				keyWord = input.next();
				boolean exist = findSongWithKeyWord(keyWord);
				if(!exist) {
					System.out.println("Keyword does not exist");
				}
			} 

			// Query to display statistics
			else if (response == 't') {
				displayStatistics();
			}
		}
		saveFile();
	}
	
	/**
	 * Reads file into the songList, invertedList and idList
	 */
	public static void createSongListAndInvertedList()  {
		//FIXME Francis
		uploadNewSong("danger");
		uploadNewSong("diggity");
		uploadNewSong("gangnam_style");
		uploadNewSong("gentleman");
		uploadNewSong("girls_girls_girls");
		uploadNewSong("i_need_u"	);
		uploadNewSong("that_that");
		uploadNewSong("yet_to_come");
		uploadNewSong("your_light");
		uploadNewSong("bang_bang_bang");
		uploadNewSong("candy");
		uploadNewSong("fantastic_baby");
		uploadNewSong("ice_cream");
		uploadNewSong("knock_knock");
		uploadNewSong("nanana");
		uploadNewSong("pretty_savage");
	}
	
	/**
	 * Query user for input
	 * u : uploadNewSong
	 * d : deleteSong
	 * s : displaySongDetails
	 * m : modifySong
	 * l : findSongWithLyrics
	 * t : displayStatistics
	 * q : quit
	 * @return reponse given by user
	 */
	public static char displayMainMenu() {
		System.out.println("\nChoose one of the following options to continue" + "\n"
							+   "_________________________________"
							+ "\n|BUTTON| OPTION                 |"
							+ "\n|======|========================|"
							+ "\n|u     | Upload new song        |"
							+ "\n|d     | Delete a song          |" 
							+ "\n|s     | Display song details   |" 
							+ "\n|l     | Find song with lyrics  |"
							+ "\n|m     | Modify song            |" 
							+ "\n|t     | Display statistics     |" 
							+ "\n|q     | QUIT                   |"
							+ "\n|_______________________________|" 
							+ "\n");
		System.out.print("Type option here: ");
		Scanner temp = new Scanner(System.in);
		String userInputString = "x";
		char userInputChar = 'x';
		userInputString = temp.nextLine();

		if (userInputString.length() > 1) {
			System.out.println("invalid entry, entry cannot be longer than one character\n");
		} else if (userInputString.equalsIgnoreCase("u") || userInputString.equalsIgnoreCase("d")
				|| userInputString.equalsIgnoreCase("s") || userInputString.equalsIgnoreCase("l")
				|| userInputString.equalsIgnoreCase("t") || userInputString.equalsIgnoreCase("q")
				|| userInputString.equalsIgnoreCase("m")) {
			userInputChar = userInputString.toLowerCase().charAt(0);
		}

		return userInputChar;
	}
	
	/**
	 * upload song to songList and invertedList and idList
	 * @param song
	 * @throws FileNotFoundException 
	 */
	public static void uploadNewSong(String song)  {
		Kpop k;
		File file = new File(song.toLowerCase().replaceAll(" ", "_")  +".txt");
		// Reading the song from a text file

		try{
			Scanner scan = new Scanner(file);

			// Stores title and artist of song
			String title = scan.nextLine();
			String artist = scan.nextLine();
			k = new Kpop(title, artist);

			// Checks if the given file features a song that is already stored
			if (songList.contains(k)) {
				System.out.println("The song is already in the song list.");
				scan.close();
				return;
			} 

			// Stores release year of song
			int year = scan.nextInt();
			scan.nextLine(); //remove buffer
			k.setYear(year);

			// Stores lyrics of song
			StringBuilder build = new StringBuilder();
			String line;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				build.append(line + "\n");
			}
			k.setLyrics(build.toString());
			scan.close();
		} catch(FileNotFoundException e) {
			Scanner input = new Scanner(System.in);
			System.out.println("Sorry, the song: " + song + " does not exist in the database\n"
					+ "Please manually enter the song");

			// Stores title of song
			System.out.println("Please enter the name of the song: ");
			String title = input.nextLine();

			// Stores artist of song
			System.out.println("Please enter the artist of the song: ");
			String artist = input.nextLine();

			// Stores release year of song
			System.out.println("Please enter the created year of the song: ");
			int year = input.nextInt();
			input.nextLine(); // remove buffer

			// Stores lyrics of song
			System.out.println("Please enter the lyrics of the song: ");
			System.out.println("When finished entering, press \"Enter\",followed by \"@\"");
			StringBuilder build = new StringBuilder();
			String line;
			while (true) {
				line = input.nextLine();
				if (line.equals("@")) {
					break;
				}
				build.append(line + "\n");
			}
			String lyrics = build.toString(); 
			k = new Kpop(title, artist, year, lyrics);
		}
		
		// Adds the song (kpop object) to the songList (hashTable)
		songList.add(k);

		// Adds the song to the invertedList and idList
		addToInvertedList(k);
	}

	
	/**
	 * Query what song to delete
	 * delete song from songList and invertedList
	 * @param song song title
	 * @param artist artist name
	 * @return true if song found, false if song is not found
	 */
	public static boolean deleteSong(String song, String artist) {
//		//ivertedList id List
//		//Check if the song, can be found in the songList
//		//if not, return false
//		//if exist, go to the Kpop object, and acess the lyrics
//		//While through the lyrics
//		//just delete from the invertedList first
//		//delete kpop object from songList
		Kpop k = new Kpop(song, artist);
		k = songList.find(k);
		if (k == null) {
			return false;
		}
		removeFromInvertedList(k);
		songList.delete(k);
		return true;
	}
	
	/**
	 * Modify the details of the song
	 * @param song
	 * @param artist
	 * @return true if song is modified, false if song is not found
	 */
	public static boolean modifySong(String songs, String artists) {
		Kpop k = new Kpop(songs, artists);
		k = songList.find(k);
		//if not found in song list
		String response = "";
		if(k == null) return false;
		Scanner scan = new Scanner(System.in);
		while (!response.equalsIgnoreCase("q")) {
			System.out.println("What would you like to modify");
			System.out.println("_____________________________"
						  + "\n|BUTTON| OPTION             |"
						  + "\n|======|====================|"
						  + "\n|t     |title               |"
						  + "\n|a     |artist              |"
						  + "\n|y     |year                |"
						  + "\n|l     |lyrics              |"
						  + "\n|q     |return to Main Menu |"
						  + "\n|______|____________________|");
			response = scan.nextLine();
			if(response.equalsIgnoreCase("t")) {
				System.out.print("Enter new title: ");
				String title;
				title = scan.nextLine();
				//Title will influence the hashcode
				Kpop newK = k;
				newK.setTitle(title);
				songList.delete(k);
				songList.add(newK);
			} else if(response.equalsIgnoreCase("a")) {
				System.out.print("Enter new artist: ");
				String artist;
				artist = scan.nextLine();
				//Artist will influence the hashcode
				Kpop newK = k;
				newK.setArtist(artist);
				songList.delete(k);
				songList.add(newK);
			} else if(response.equalsIgnoreCase("y")) {
				System.out.print("Enter new year: ");
				int year;
				year = scan.nextInt();
				// remove buffer
				scan.nextLine();
				k.setYear(year);
			} else if(response.equalsIgnoreCase("l")) {
				System.out.println("Enter new lyrics: ");
				System.out.println("When finished entering, press \"Enter\",followed by \"@\"");
				String lyrics;
				StringBuilder build = new StringBuilder();
				String line;
				while (true) {
					line = scan.nextLine();
					if(line.equals("@")) {
						break;
					}
					build.append(line + "\n");
				}
				lyrics = build.toString(); 
				//delete old information from invertedList
				removeFromInvertedList(k);
				//add new information to invertedList
				k.setLyrics(lyrics);
				addToInvertedList(k);
			} 
		}
		return true;
		
	}
	
	/**
	 * Query for what songs to display
	 * Displays the details of the song with the given title
	 * @param song
	 * @return true if song is found, false if song is not found
	 */
	public static boolean displaySongDetails(String song, String artist) {
		// FIXME Gavin
		Kpop temp = new Kpop(song, artist);

		if (songList.contains(temp) == false) {
			return false;
		} else {
			System.out.println(songList.find(temp) + "\n");
			return true;
		}
	}
	
	/**
	 * Display the songs that contains the lyrics
	 * Query for which song details they would like to know
	 * !!This part you can use DisplaySongDetails()
	 * Query until user enters q
	 * @param keyWord word to look for
	 * @return true if songs are found, false if song is not found
	 */
	public static boolean findSongWithKeyWord(String keyWord) {
		//Access idList, find the WordID object
		//if does not exist, return false
		//if exist, retrive id from the WordID object
		//use the id to and display the songs in the index
		//need to access, BST inOrderArray();
		//Print out songs that exist in the bst in ascending order.
		//Query use for the songs they wanna know more about
		//display details
		//until user enters q


		WordID w = new WordID(keyWord.toLowerCase().replaceAll("\\p{Punct}",""));
		Scanner userInput = new Scanner(System.in);
		String response = "";
		String artist = "";
		//search the keyword from the idList
		WordID word = idList.find(w);
		if(word == null) {
			//if the keyword doens't exist in the database, return false and return to main
			return false;
		} else {
			//if the keyword is found, retrieve the BST of Kpop object from the inverted list using the keyword
			BST<Kpop> k = invertedList.get(word.getId());
			//then returns an arraylist containing the Kpop object in the particular BST in ascending order
			ArrayList<Kpop> l = k.inOrderArrayList();
			if (l.size() == 0) {
				return false;
			}
			//use a for loop to print out the title and artist of songs that contain the keyword
			for(int i = 0; i < l.size(); i++){
				System.out.println(i+1 + ": " + l.get(i).getTitle() + " - " + l.get(i).getArtist());
			}
			//prompt the user
			System.out.println("Do you want to know more about the songs?");
			System.out.print("Y for yes and N for no: ");
			response = userInput.nextLine();
			while(!(response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N"))) {
				System.out.print("Please enter Y or N: ");
				response = userInput.nextLine();
			} 
			if(response.equalsIgnoreCase("Y")){
				do {
					//if yes, prompt the user to enter title and artist
					System.out.print("Enter title of song or enter q to quit: ");
					response = userInput.nextLine();
					if(response.equalsIgnoreCase("q")) break;
					System.out.print("Name of artist: ");
					artist = userInput.nextLine();
					//display the song details of given title and artist
					displaySongDetails(response, artist);
					for(int i = 0; i < l.size(); i++){
						System.out.println(i+1 + ": " + l.get(i).getTitle() + " - " + l.get(i).getArtist());
					}
				 } while(!response.equalsIgnoreCase("q"));
			}
			return true;
		}
		
	}
	
	/**
	 * 
	 */
	public static void displayStatistics() {
		// Display percentage of the songs produced by a particular artist
		// Display the percentage of a the occurence of a particular keyword
		// Display the percentage of the songs produced in that particular year
		Scanner input = new Scanner(System.in);
		boolean cont = true;

		while (cont) {
			System.out.println("What statistics do you wish to know more about: \n");
			System.out.println("___________________________________________________");
			System.out.println("|BUTTON| OPTION                                   |");
			System.out.println("|======|==========================================|");
			System.out.println("|1     | Current number of songs                  |");
			System.out.println("|2     | Occurence of a keyword in a song         |");
			System.out.println("|3     | Percentage of songs that contains keyword|");
			System.out.println("|q     | go back to main menu                     |");
			System.out.println("|______|__________________________________________|");
			
			System.out.print("Type option here: ");
			String choice = input.next();
			while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equalsIgnoreCase("q"))){
				System.out.println("Please enter 1, 2 or 3 only!");
				System.out.print("Please enter your choice: ");
				choice = input.next();
			}
			 
			if(choice.equals("1")){
				System.out.println("There are " + (songList.getNumElements()) +" currently stored in this program\n");
				
			} else if(choice.equals("2")){
				String keyword = "";
				String artist = "";
				String title = "";
				String lyrics = "";
				int countKeyword = 0;

				// I would say enter the keyword
				input.nextLine();
				System.out.print("Enter the title of the song: ");
				title = input.nextLine();

				System.out.print("\nEnter the artist of the song: ");
				artist = input.nextLine();

				Kpop temp = new Kpop(title, artist);
				System.out.print("\nEnter the keyword: ");
				keyword = input.next();

				try {

					lyrics = songList.find(temp).getLyrics();

					Scanner tempScan = new Scanner(lyrics);

					while (tempScan.hasNext()) {
						if (tempScan.next().replaceAll("\\p{Punct}","").equalsIgnoreCase(keyword)) {
							countKeyword++;
						}
					}

					System.out.println("\nThe word " + keyword + " pops up " + countKeyword + " times\n\n");

				} catch (NullPointerException e) {
					System.out.println("\nThat was not valid, returning to sub menu\n");
				}

			} else if(choice.equals("3")){
				
				String keyword = "";
				input.nextLine();
				System.out.print("Enter the keyword: ");
				keyword = input.nextLine();
				System.out.println("");
				
				WordID word = new WordID(keyword.toLowerCase().replaceAll("\\p{Punct}",""));
				word = idList.find(word);
				if(word == null || invertedList.get(word.getId()).isEmpty()) {
					System.out.println("Word not found");
					continue;
				} 
				if(songList.getNumElements() == 0) {
					System.out.println("SongList is empty, please upload new songs");
				}
				double numSong = invertedList.get(word.getId()).getSize();
				System.out.println("Percentage of song that contains the word " + word.getWord());
				
				double percentage = (numSong / songList.getNumElements()) * 100;
				System.out.printf("%.2f", percentage);
				System.out.println("%");
			} else {
				cont = false;
			}
		
		}
		
		
		
	}
	
	/**
	 * Adds all the words of a song's lyrics to invertedList
	 * @param k Kpop object
	 */
	public static void addToInvertedList(Kpop k) {
		Scanner in = new Scanner(k.getLyrics());
		String temp = new String();
		WordID id;
		HashTable<String> stopwordtable = new HashTable<>(stopwordlist, stopwordlist.length * 3);
		
		while (in.hasNext()) {
			// Converts word in the lyrics to lowercase and removes punctuation
			temp = in.next();
			temp = temp.toLowerCase();
			temp = temp.replaceAll("\\p{Punct}","");
			
			// Checks if the word in the lyrics is in the stopword list
			if (stopwordtable.contains(temp)) {
				continue;
			}

			id = new WordID(temp, index);
			WordID word = idList.find(id);
			// Adds a new wordID using the word of the lyrics to the idList and adds the song to the invertedList 
			if (word == null) {
				idList.add(id);
				invertedList.get(index++).insert(k);
			} 
			// Inserts the song into invertedList (word already exists in idList)
			else {
				invertedList.get(word.getId()).insert(k);
			}
		}
	}
	
	/**
	 * Removes all the words that are contained in the lyrics from the storage
	 * @param k Kpop object
	 */
	public static void removeFromInvertedList(Kpop k) {
		Scanner scan = new Scanner(k.getLyrics());
		while (scan.hasNext()) {
			// Converts word in the lyrics to lowercase and removes punctuation
			String temp = scan.next();
			temp = temp.toLowerCase();
			temp = temp.replaceAll("\\p{Punct}","");

			WordID word = new WordID(temp);
			word = idList.find(word);
			// Might be in stopword
			if (word != null) {
				invertedList.get(word.getId()).remove(k);
			}
		}
	}
	
	/**
	 * Write all records to a file of user choice
	 */
	public static void saveFile() {
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to save your file? ");
		System.out.print("Y for yes and N for no: ");
		String response = input.nextLine();
		while(!(response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N"))) {
			System.out.print("Please enter Y or N: ");
			response = input.nextLine();
		} 
		if(response.equalsIgnoreCase("Y")){
			System.out.print("What would  you like to name your file: ");
			String fileName = input.nextLine();
			File file = new File(fileName + ".txt");
			try {
				PrintWriter out = new PrintWriter(file);
				out.print(songList.toString());
				System.out.println("File Saved!!!!");
			} catch (FileNotFoundException e) {
				System.out.println("File failed to open");
			}
		} 
	} 
}

	
