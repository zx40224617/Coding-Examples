
/**
 * HashTable.java
 * @author Yun Chung Chang
 * @author	Francis Chang
 * CIS 22C, Lab 5
 */
import java.util.ArrayList;

public class HashTable<T> {

	private int numElements;
	private ArrayList<LinkedList<T>> Table;

	/**
	 * Constructor for the HashTable class. Initializes the Table to be sized
	 * according to value passed in as a parameter Inserts size empty Lists into the
	 * table. Sets numElements to 0
	 * 
	 * @param size the table size
	 * @precondition size > 0
	 * @throws IllegalArgumentException when size <= 0
	 */
	public HashTable(int size) throws IllegalArgumentException {
		if (size < 1) {
			throw new IllegalArgumentException("HashTable(int size): size cannot be less than 1");
		} else {
			Table = new ArrayList<LinkedList<T>>(size);
			for (int i = 0; i < size; i++) {
				LinkedList<T> list = new LinkedList<T>();
				Table.add(list);
			}
			numElements = 0;

		}

	}

	/**
	 * Constructor for HashTable class Inserts the contents of the given array into
	 * the Table at the appropriate indices
	 * 
	 * @param array an array of elements to insert
	 * @param size  the size of the Table
	 * @precondition size > 0
	 * @throws IllegalArgumentException when size <= 0
	 */
	public HashTable(T[] array, int size) throws IllegalArgumentException {
		if (size < 1) {
			throw new IllegalArgumentException("HashTable(T[] array, int size): size cannot be less than 1");
		} else {
			Table = new ArrayList<LinkedList<T>>(size);
			for (int i = 0; i < size; i++) {
				Table.add(new LinkedList<T>());
			}
			if (array == null) {
				return;
			}
			for (int i = 0; i < array.length; i++) {
				add(array[i]);
			}

		}

	}

	/** Accessors */

	/**
	 * returns the hash value in the Table for a given Object
	 * 
	 * @param t the Object
	 * @return the index in the Table
	 */
	private int hash(T t) {
		int code = t.hashCode();
		return code % Table.size();

	}

	/**
	 * Counts the number of elements at this index
	 * 
	 * @param index the index in the Table
	 * @precondition index >= 0 && index < table.size
	 * @return the count of elements at this index
	 * @throws IndexOutOfBoundsException when the precondition is violated
	 */
	
	/**
	 * 
	public int countBucket(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= Table.size()) {
			throw new IndexOutOfBoundsException("countBucket(): index must in range");
		} else {
			int count = 0;
			Table.get(index).positionIterator();
			for(int i = 0; i < Table.get(index).getLength(); i++) {
				count++;
				Table.get(index).advanceIterator();
				
			}
			return count;
			}
		
	}
	 */
	public int countBucket(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= Table.size()) {
			throw new IndexOutOfBoundsException("countBucket(): index must in range");
		} else {

			return Table.get(index).getLength();
		}

	}

	/**
	 * Determines total number of elements in the Table
	 * 
	 * @return total number of elements
	 */
	public int getNumElements() {
		return numElements;
	}

	/**
	 * Accesses a specified element in the Table
	 * 
	 * @param t the element to locate
	 * @return the bucket number where the element is located or -1 if it is not
	 *         found
	 * @precondition element cannot be null
	 * @throws NullPointerException when the precondition is violated
	 */
	
	/**
	 * 
	 * public int find(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("find(): element cannot be null");
		} else {
			int code = hash(t);
			Table.get(code).positionIterator();
			
			for (int i = 0; i < Table.get(code).getLength(); i++) {
				if (t.equals(Table.get(code).getIterator())) {
					return code;
				} else {
					Table.get(code).advanceIterator();
				}
			}
			return -1;
		}
	}
	 */

	public int find(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("find(): element cannot be null");
		} else {
			int bucket = hash(t);
			Table.get(bucket).positionIterator();
			int num = Table.get(bucket).findIndex(t);
			if (num != -1) {
				return bucket;
			}

			return -1;
		}
	}

	/**
	 * Determines whether a specified element is in the Table
	 * 
	 * @param t the element to locate
	 * @return whether the element is in the Table
	 * @precondition element cannot be null
	 * @throws NullPointerException when the precondition is violated
	 */

     /**
      * 
   public boolean contains(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("contains(): element cannot be null");
		} else {
			int bucket = hash(t);
			Table.get(bucket).positionIterator();
			for (int i = 0; i < Table.get(bucket).getLength(); i++) {
				if (Table.get(bucket).getIterator().equals(t)) {
					return true;
				}
				Table.get(bucket).advanceIterator();
			}
			return false;
		}
	}
      */
	public boolean contains(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("contains(): element cannot be null");
		} else {
			int bucket = hash(t);
			Table.get(bucket).positionIterator();
			int num = Table.get(bucket).findIndex(t);
			if (num != -1) {
				return true;
			}

			return false;
		}
	}

	/** Mutators */

	/**
	 * Inserts a new element in the Table at the end of the chain of the correct
	 * bucket
	 * 
	 * @param t the element to insert
	 * @precondition <you fill in here>
	 * @throws NullPointerException when the precondition is violated
	 */
	public void add(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("add(): Element cannot be null");
		} else {
			int bucket = hash(t);
			Table.get(bucket).addLast(t);
			numElements++;
		}
	}

	/**
	 * Removes the given element from the Table
	 * 
	 * @param t the element to remove
	 * @precondition <you fill in here>
	 * @return whether t exists and was removed from the Table
	 * @throws NullPointerException when the precondition is violated
	 * 
	 * 
	 **/
	
	/**
	 * public boolean delete(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("delete(): Element can't be null ");
		} else {
			int bucket = hash(t);
			Table.get(bucket).positionIterator();
			for (int i = 0; i < Table.get(bucket).getLength(); i++) {
				if (Table.get(bucket).getIterator().equals(t)) {
					Table.get(bucket).removeIterator();
					numElements--;
					return true;
				}
				Table.get(bucket).advanceIterator();
			}
			return false;
		}
	}
	 * 
	 * */
	
	public boolean delete(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("delete(): Element can't be null ");
		} else {
			int bucket = hash(t);
			int num = Table.get(bucket).findIndex(t);
			Table.get(bucket).positionIterator();
			if (num != -1) {
				Table.get(bucket).advanceIteratorToIndex(num);
				Table.get(bucket).removeIterator();
				numElements--;
				return true;
			}

			return false;
		}
	}

	
	/**
	 * Resets the hash table back to the empty state, as if the one argument
	 * constructor has just been called.
	 */
	public void clear() {

		for (int i = 0; i < Table.size(); i++) {
				Table.set(i, new LinkedList<T>());

			
		}
		numElements = 0;
	}

	/** Additional Methods */

	/**
	 * Computes the load factor
	 * 
	 * @return the load factor
	 */
	public double getLoadFactor() {
		return ((double) numElements) / Table.size();
	}

	/**
	 * Creates a String of all elements at a given bucket
	 * 
	 * @param bucket the index in the Table
	 * @return a String of elements, separated by spaces with a new line character
	 *         at the end
	 * @precondition bucket >= 0 || bucket < Table.size()
	 * @throws IndexOutOfBoundsException when bucket is out of bounds
	 */
	public String bucketToString(int bucket) throws IndexOutOfBoundsException {
		if (bucket < 0 || bucket >= Table.size()) {
			throw new IndexOutOfBoundsException("bucketToString(): bucket out of bounds");
		} else {
			return Table.get(bucket).toString();
		}

	}

	/**
	 * Creates a String of the bucket number followed by a colon followed by the
	 * first element at each bucket followed by a new line For empty buckets, add
	 * the bucket number followed by a colon followed by empty
	 * 
	 * @return a String of all first elements at each bucket
	 */
	public String rowToString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < Table.size(); i++) {
			result.append("Bucket " + i + ": ");
			if (Table.get(i).isEmpty() == true) {
				result.append("empty\n");
			} else {
				result.append(Table.get(i).getFirst() + "\n");
			}
		}

		return result.toString();
	}

	/**
	 * Starting at the 0th bucket, and continuing in order until the last
	 * bucket,concatenates all elements at all buckets into one String, with a new
	 * line between buckets and one more new line at the end of the entire String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < Table.size(); i++) {
			if (!(Table.get(i).isEmpty())) {
				result.append(bucketToString(i));
			}

		}
		return result.toString() + "\n";
	}
}