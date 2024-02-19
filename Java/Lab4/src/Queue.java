
/**
* @author
 * @author
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

public class Queue<T> {
	private class Node {
		private T data;
		private Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node front;
	private Node end;
	private int size;

	/**
	 * Default constructor for the Queue class
	 */
	public Queue() {
		front = end = null;
		size = 0;
	}

	/**
	 * Converts an array into a Queue
	 * 
	 * @param array the array to copy into the Queue
	 */
	public Queue(T[] array) {
		if (array == null) {
			return;
		} else {
			for (int i = 0; i < array.length; i++) {
				enqueue(array[i]);
			}
		}
	}

	/**
	 * Copy constructor for the Queue class Makes a deep copy of the parameter
	 * 
	 * @param aQueue another Queue to copy
	 */
	public Queue(Queue<T> aQueue) {
		if (aQueue == null) {
			return;
		}
		if (aQueue.size == 0) {
			this.size = 0;
			this.front = null;
			this.end = null;
		} else {
			Node temp = aQueue.front;
			while (temp != null) {
				enqueue(temp.data);
				temp = temp.next;
			}
		}
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored at the front of the Queue
	 * 
	 * @return the value at the front of the queue
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T getFront() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("The Queue is empty");
		} else {
			return front.data;
		}
	}

	/**
	 * Returns the size of the Queue
	 * 
	 * @return the size from 0 to n
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Determines whether a Queue is empty
	 * 
	 * @return whether the Queue contains no elements
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**** MUTATORS ****/

	/**
	 * Inserts a new value at the end of the Queue
	 * 
	 * @param data the new data to insert
	 * @postcondition a new node at the end of the Queue
	 */
	public void enqueue(T data) {
		Node n = new Node(data);
		if (size == 0) {
			front = end = n;
		} else {
			end.next = n;
			end = n;
		}
		size++;
	}

	/**
	 * Removes the front element in the Queue
	 * 
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the precondition is violated
	 * @postcondition the front element has been removed
	 */
	public void dequeue() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("The Queue is empty");
		} else if (size == 1) {
			front = null;
			end = null;
		} else {
			front = front.next;
		}
		size--;

	}

	/**** ADDITONAL OPERATIONS ****/

	/**
	 * Returns the values stored in the Queue as a String, separated by a blank
	 * space with a new line character at the end
	 * 
	 * @return a String of Queue values
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node temp = front;
		while (temp != null) {
			result.append(temp.data + " ");
			temp = temp.next;
		}
		return result.toString() + "\n";
	}

	/**
	 * Determines whether two Queues contain the same values in the same order
	 * 
	 * @param o the Object to compare to this
	 * @return whether o and this are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof Queue)) {
			return false;
		} else {
			Queue<T> q = (Queue<T>) o;
			if (this.size != q.size) {
				return false;
			} else {
				Node temp1 = this.front;
				Node temp2 = q.front;
				while (temp1 != null) {
					if (!(temp1.equals(temp2))) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}
	}

	/**
	 * Creates a String of the Queue in reverse order
	 * 
	 * @return a Queue in reverse order
	 */
	public String reverseQueue() {
		Node temp = this.front;
		return reverseQueue(temp) + "\n";

	}

	/**
	 * Determines whether the Queue is sorted from smallest to largest by calling
	 * its recursive helper
	 * 
	 * @return whether the Queue is sorted
	 */

	/**
	 * public boolean isSorted() { return isSorted(front);
	 * 
	 * 
	 * }
	 **/

	/**
	 * Uses the iterative linear search algorithm to locate an element
	 * 
	 * @param element the value to search for
	 * @return whether the element is present in the Queue Note that in the case
	 *         length==0 the element is considered not found
	 */
	public boolean linearSearch(T element) {
		if (this.size == 0) {
			return false;
		}
		for (int i = 0; i < this.size; i++) {
			if (this.getFront().equals(element)) {
				return true;
			}
			front = front.next;
		}
		return false;
	}

	/**
	 * Uses the recursive binarySearch algorithm to determine if a specific element
	 * is in the Queue by calling the private helper method binarySearch
	 * 
	 * @param value the value to search for
	 * @return whether the element is present in the Queue
	 * @precondition isSorted()
	 * @throws IllegalStateException when the precondition is violated.
	 */
	/**
	 * public boolean binarySearch(T value) throws IllegalStateException { if
	 * (!this.isSorted()) { throw new IllegalStateException("binarySearch(): The
	 * queue is not sorted"); } else { if (binarySearch(0, this.size - 1, value) !=
	 * -1) { return true; } else { return false; } }
	 * 
	 * }
	 **/

	/** RECURSIVE HELPER METHODS */

	/**
	 * Recursively (no loops) creates a String where the data is in reverse order
	 * 
	 * @param n the current node
	 */
	private String reverseQueue(Node n) {
		if (n == null) {
			return "";
		} else {
			return reverseQueue(n.next) + n.data + " ";
		}
	}

	/**
	 * Helper method to isSorted Recursively determines whether data is sorted from
	 * smallest to largest
	 * 
	 * @param node the current node
	 * @return whether the data is sorted in ascending order
	 */
	/**
	 * private boolean isSorted(Node node) { if (size == 0 || node.data == null) {
	 * return false; }else if(node.next == null) { return true; } else
	 * if(node.data.compareTo(node.next.data) > 0) { return false; } else { return
	 * isSorted(node.next); } }
	 **/

	/**
	 * Searches for the specified value in by implementing the recursive
	 * binarySearch algorithm
	 * 
	 * @param low   the lowest bounds of the search
	 * @param high  the highest bounds of the search
	 * @param value the value to search for
	 * @return the location at which value is located from 1 to length or -1 to
	 *         indicate not found
	 */
	/**
	 * private int binarySearch(int low, int high, T value) { if(low > high) {
	 * return -1; } int mid = (low + high) / 2; Node temp = front; for(int i = 0; i
	 * < mid; i++) { temp = temp.next; } if(temp.data.equals(value)) { return mid +
	 * 1; } else if(temp.data.compareTo(value) < 0) { return binarySearch(mid + 1,
	 * high, value); } else { return binarySearch(low, mid - 1, value); }
	 * 
	 * }
	 **/
	

	
}