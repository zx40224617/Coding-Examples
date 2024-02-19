/**
 * HashTable.java
 * @author Wei Quan Lai
 * @author Emir Elzein
 * CIS 22C, Lab 5
 */
import java.util.ArrayList;

public class HashTable<T> {
    
    private int numElements;
    private ArrayList<LinkedList<T>> Table;

    /**
     * Constructor for the HashTable class. Initializes the Table to be 
     * sized according to value passed in as a parameter
     * Inserts size empty Lists into the table. Sets numElements to 0
     * @param size the table size
     * @precondition size > 0
     * @throws IllegalArgumentException when size <= 0
     */
    public HashTable(int size) throws IllegalArgumentException {
    	if (size <= 0) {
    		throw new IllegalArgumentException("HashTable default constructor: size <= 0");
    	}
        Table = new ArrayList<LinkedList<T>>(size); 
        for (int i = 0; i < size; i++) {
        	Table.add(new LinkedList<T>());
        }
    }
    
    /**
     * Constructor for HashTable class
     * Inserts the contents of the given array 
     * into the Table at the appropriate indices
     * @param array an array of elements to insert
     * @param size the size of the Table
     * @precondition size > 0
     * @throws IllegalArgumentException when size <= 0
     */
    public HashTable(T[] array, int size) throws IllegalArgumentException {
        if (size <= 0) {
    		throw new IllegalArgumentException("HashTable array constructor: size <= 0");
    	}
        Table = new ArrayList<LinkedList<T>>(size);
        for (int i = 0; i < size; i++) {
        	Table.add(new LinkedList<T>());
        }
        if (array == null || array.length == 0) {
        	return;
        }
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }
       
    /**Accessors*/
    //Edited
    /**
     * returns the hash value in the Table
     * for a given Object 
     * @param t the Object
     * @return the index in the Table
     */
    private int hash(T t) {
    	int code;
        code = t.hashCode();
        return Math.abs(code % Table.size());
    }
    
    /**
     * Counts the number of elements at this index
     * @param index the index in the Table
     * @precondition index >= 0 and index < Table.size()
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException when
     * the precondition is violated
     */
    public int countBucket(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= Table.size()) {
            throw new IndexOutOfBoundsException("countBucket: index out of bounds");
        }
        return Table.get(index).getLength();
    }
    
    /**
     * Determines total number of elements in the Table
     * @return total number of elements
     */
    public int getNumElements() {
        return numElements;
    }
    
    /**
     * Accesses a specified element in the Table
     * @param t the element to locate
     * @return the element at the bucket
     * @precondition t != null
     * @throws NullPointerException when the
     * precondition is violated
     */
    public T find(T t) throws NullPointerException{
        if(t == null) {
            throw new NullPointerException("find: t is null");
        }
        int bucket = hash(t);
        int position = Table.get(bucket).findIndex(t);
        if(position == -1) {
            return null;
        } else {
            Table.get(bucket).positionIterator();
            Table.get(bucket).advanceIteratorToIndex(position);
            return Table.get(bucket).getIterator();
        }
    }

    
    /**
     * Determines whether a specified element is in 
     * the Table
     * @param t the element to locate
     * @return  whether the element is in the Table 
     * @precondition t != null
     * @throws NullPointerException when the 
     * precondition is violated
     */
    public boolean contains(T t) throws NullPointerException{
        if (t == null) {
            throw new NullPointerException("contains: t is null");
        }
        int bucket = hash(t);
        int position = Table.get(bucket).findIndex(t);
        if (position == -1) {
            return false;
        } else {
            return true;
        }
    }

     
    /**Mutators*/
    
    /**
     * Inserts a new element in the Table
     * at the end of the chain of the 
     * correct bucket
     * @param t the element to insert
     * @precondition t != null
     * @throws NullPointerException when the
     * precondition is violated
     */
    public void add(T t) throws NullPointerException{  
        if(t == null) {
            throw new NullPointerException("add: t is null");
        }
        int bucket = hash(t);
        Table.get(bucket).addLast(t);
        numElements++;
    }  
     
    /**
     * Removes the given element from the Table         
     * @param t the element to remove
     * @precondition t != null
     * @return whether t exists and was removed from the Table
     * @throws NullPointerException when the precondition
     * is violated
     */
    public boolean delete(T t) throws NullPointerException {
        if(t == null) {
            throw new NullPointerException("delete: t is null");
        }
        int location = hash(t);
        if (!contains(t)){
            return false;
        } else {
            int index = Table.get(location).findIndex(t);
            Table.get(location).positionIterator();
            Table.get(location).advanceIteratorToIndex(index);
            Table.get(location).removeIterator();
            numElements--;
            return true;
        }
    }

    /**
     * Resets the hash table back to the
     * empty state, as if the one argument
     * constructor has just been called.
     */
    public void clear() {
        numElements = 0;
        for (int i = 0; i < Table.size(); i++) {
        	Table.set(i, new LinkedList<T>());
        }
    }

    /**Additional Methods*/
    
    /**
     * Computes the load factor
     * @return the load factor
     */
    public double getLoadFactor() {
        return (double)numElements / Table.size();
    }

    /**
     * Creates a String of all elements at a given bucket
     * @param bucket the index in the Table
     * @return a String of elements, separated by spaces
     * with a new line character at the end
     * @precondition bucket >= 0 || bucket < Table.size()
     * @throws IndexOutOfBoundsException when bucket is
     * out of bounds
     */
    public String bucketToString(int bucket) throws IndexOutOfBoundsException{
        if(bucket < 0 || bucket >= Table.size()) {
            throw new IndexOutOfBoundsException("bucketToString: bucket is out of bounds");
        }
        return Table.get(bucket).toString();
    }
        
    /**
     * Creates a String of the bucket number
     * followed by a colon followed by the first
     * element at each bucket followed
     * by a new line
     * For empty buckets, add the bucket number
     * followed by a colon followed by empty
     * @return a String of all first elements
     * at each bucket
     */
    public String rowToString(){
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < Table.size(); i++) {
            if (Table.get(i).isEmpty()) {
                row.append("Bucket " + i + ": empty" + "\n");
            } else {
                row.append("Bucket " + i + ": " + Table.get(i).getFirst() + "\n");
            }
        }
        return row.toString();
    }
    
    /**
     * Starting at the 0th bucket, and continuing 
     * in order until the last bucket,concatenates all 
     * elements at all buckets into one String, with
     * a new line between buckets and one more new line at the
     * end of the entire String
     */
    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < Table.size(); i++) {
        	if(!Table.get(i).isEmpty()) {
        		ans.append(bucketToString(i));
        	}
        }
        return ans.toString() + '\n';
   }
}