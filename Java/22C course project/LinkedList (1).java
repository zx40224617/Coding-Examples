
/**
 * Defines a doubly-linked list class
 * @author Wei Quan Lai
 * @author Emir Elzein
 */
import java.util.NoSuchElementException;

public class LinkedList<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTORS ****/
	/**
	 * Instantiates a new LinkedList with default values
	 * 
	 * @postcondition a LinkedList is created with no nodes
	 */
	public LinkedList() {
		length = 0;
		first = last = iterator = null;
	}

	/**
	 * Converts the given array into a LinkedList
	 * 
	 * @param array the array of values to insert into this LinkedList
	 * @postcondition a LinkedList is created and its nodes hold the values of an
	 *                array
	 */
	public LinkedList(T[] array) {
		if (array == null || array.length == 0) {
			return;
		} else {
			for (int i = 0; i < array.length; i++) {
				addLast(array[i]);
			}
		}
	}

	/**
	 * Instantiates a new LinkedList by copying another List
	 * 
	 * @param original the LinkedList to copy
	 * @postcondition a new LinkedList object, which is an identical, but separate,
	 *                copy of the LinkedList original is created
	 */
	public LinkedList(LinkedList<T> original) {
		if (original == null || original.length == 0) {
			return;
		} else {
			Node temp = original.first;
			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}
			iterator = null;
		}
	}

	/**** ACCESSORS ****/
	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition length != 0
	 * @return the value stored at node first
	 * @throws NoSuchElementException when the LinkedList is empty
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst: " + "List is empty. There is no first node");
		}
		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition length != 0
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when the LinkedList is empty
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast: " + "List is empty. There is no last node");
		}
		return last.data;
	}

	/**
	 * Returns the data stored in the iterator node
	 * 
	 * @precondition iterator != null
	 * @throws NullPointerException when iterator is referencing to null
	 */
	public T getIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIterator: " + "Iterator is referencing to null");
		}
		return iterator.data;
	}

	/**
	 * Returns the current length of the LinkedList
	 * 
	 * @return the length of the LinkedList from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the LinkedList is currently empty
	 * 
	 * @return whether the LinkedList is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Returns whether the iterator is offEnd, i.e. null
	 * 
	 * @return whether the iterator is null
	 */
	public boolean offEnd() {
		return iterator == null;
	}

	/**** MUTATORS ****/
	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the LinkedList
	 * @postcondition a new node is added to the start of the LinkedList
	 */
	public void addFirst(T data) {
		if (length == 0) {
			first = last = new Node(data);
		} else {
			Node newNode = new Node(data);
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the LinkedList
	 * @postcondition a new node is added to the end of the LinkedList
	 */
	public void addLast(T data) {
		if (length == 0) {
			first = last = new Node(data);
		} else {
			Node newNode = new Node(data);
			newNode.prev = last;
			last.next = newNode;
			last = newNode;
		}
		length++;
	}

	/**
	 * Inserts a new element after the iterator
	 * 
	 * @param data the data to insert
	 * @precondition iterator != null
	 * @throws NullPointerException when iterator is referencing to null
	 */
	public void addIterator(T data) throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("addIterator " + "Iterator is referencing to null");
		} else if (iterator == last) {
			addLast(data);
		} else {
			Node newNode = new Node(data);
			newNode.next = iterator.next;
			newNode.prev = iterator;
			iterator.next.prev = newNode;
			iterator.next = newNode;
			length++;
		}
	}

	/**
	 * Removes the element at the front of the LinkedList
	 * 
	 * @precondition length != 0
	 * @postcondition the first node of the LinkedList will be removed
	 * @throws NoSuchElementException when LinkedList is empty
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst: " + "List is Empty. No node to delete");
		} else if (length == 1) {
			first = last = iterator = null;
		} else {
			if (iterator == first) {
				iterator = null;
			}
			first = first.next;
			first.prev = null;
		}
		length--;
	}

	/**
	 * Removes the element at the end of the LinkedList
	 * 
	 * @precondition length != 0
	 * @postcondition the last node of the LinkedList will be removed
	 * @throws NoSuchElementException if LinkedList is empty
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast: " + "List is empty.No node to delete");
		} else if (length == 1) {
			first = last = iterator = null;
		} else {
			if (iterator == last) {
				iterator = null;
			}
			last = last.prev;
			last.next = null;
		}
		length--;
	}

	/**
	 * Removes the element referenced by the iterator
	 * 
	 * @precondition iterator != null
	 * @postcondition the element that the iterator is referencing to will be 
delete
	 * @throws NullPointerException if iterator is referencing to null
	 */
	public void removeIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("removeIterator: " + "Iterator is referencing to null");
		} else if (iterator == first) {
			removeFirst();
		} else if (iterator == last) {
			removeLast();
		} else {
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			iterator = null;
			length--;
		}
	}

	/**
	 * Places the iterator at the first node
	 * 
	 * @postcondition iterator will be referencing to the first node
	 */
	public void positionIterator() {
		iterator = first;
	}

	/**
	 * Moves the iterator one node towards the last
	 * 
	 * @precondition iterator != null
	 * @postcondition iterator will be referencing to the next node
	 * @throws NullPointerException if iterator is referencing to null
	 */
	public void advanceIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("advanceIterator: " + "Iterator isreferencing to null");
		}
		iterator = iterator.next;
	}

	/**
	 * Moves the iterator one node towards the first
	 * 
	 * @precondition iterator != null
	 * @postcondition iterator will be referencing to the previous node
	 * @throws NullPointerException if iterator is referencing to null
	 */
	public void reverseIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("reverseIterator: " + "Iterator isreferencing to null");
		}
		iterator = iterator.prev;
	}

	/**** ADDITIONAL OPERATIONS ****/
	/**
	 * Converts the LinkedList to a String, with each value separated by a blank
	 * line At the end of the String, place a new line character
	 * 
	 * @return the LinkedList as a String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node temp = first;
		while (temp != null) {
			result.append(temp.data + " ");
			temp = temp.next;
		}
		return result.toString() + "\n";
	}

	/**
	 * Determines whether the given Object is another LinkedList, containing the
	 * same data in the same order
	 * 
	 * @param o another Object
	 * @return whether there is equality
	 */
	@SuppressWarnings("unchecked") // good practice to remove warning here
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof LinkedList)) { // check data type
			return false;
		} else {
			LinkedList<T> L = (LinkedList<T>) o;
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;
				while (temp1 != null) {
					if (!temp1.data.equals(temp2.data)) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}
	}

	/** CHALLENGE METHODS */
	/**
	 * Moves all nodes in the list towards the end of the list the number of
times
	 * specified Any node that falls off the end of the list as it moves forward
	 * will be placed the front of the list For example: [1, 2, 3, 4, 5],
numMoves =
	 * 2 -> [4, 5, 1, 2 ,3] For example: [1, 2, 3, 4, 5], numMoves = 4 -> [2, 3,
4,
	 * 5, 1] For example: [1, 2, 3, 4, 5], numMoves = 7 -> [4, 5, 1, 2 ,3]
	 * @param numMoves the number of times to move each node.
	 * @precondition numMoves >= 0
	 * @postcondition iterator position unchanged (i.e. still referencing the
same
	 * node in the list, regardless of new location of Node)
	 * @throws IllegalArgumentException when numMoves < 0
	 */
	public void revolvingList(int numMoves) throws IllegalArgumentException {
		if (numMoves < 0) {
			throw new IllegalArgumentException("revolvingList: " + "numMoves must be positive");
		}
		if (this == null || length == 0 || length == 1) return; //if is empty or only consists of one node just return
				numMoves %= length;
		Node temp = first;
		if (numMoves == 0) return;// if it is equals to the length or zero remains the same
		for (int i = 0; i < (length - numMoves); i++) {
			temp = temp.next;
		}
		last.next = first;
		first.prev = last;
		last = temp.prev;
		first = temp;
		last.next = null;
		first.prev = null;
		temp = null;
	}

	/**
	 * Splices together two LinkedLists to create a third List which contains
	 * alternating values from this list and the given parameter For example:
	 * [1,2,3] and [4,5,6] -> [1,4,2,5,3,6] For example: [1, 2, 3, 4] and [5, 6] ->
	 * [1, 5, 2, 6, 3, 4] For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
	 * 
	 * @param list the second LinkedList
	 * @return a new LinkedList, which is the result of interlocking this and list
	 * @postcondition this and list are unchanged
	 */
	public LinkedList<T> interlockLists(LinkedList<T> list) {
		if (list == null || list.length == 0)
			return this;
		if (this == null || this.length == 0)
			return list;
		Node temp1 = this.first;
		Node temp2 = list.first;
		LinkedList<T> ans = new LinkedList<>();
		while (temp1 != null || temp2 != null) {
			if (temp1 != null) {
				ans.addLast(temp1.data);
				temp1 = temp1.next;
			}
			if (temp2 != null) {
				ans.addLast(temp2.data);
				temp2 = temp2.next;
			}
		}
		return ans;
	}

	/**
	 * Determines at which index the iterator is located Indexed from 0 to length
-
	 * 1
	 * 
	 * @return the index number of the iterator
	 * @precondition !offEnd()
	 * @throws NullPointerException when precondition is violated
	 */
	public int getIteratorIndex() throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("getIteratorIndex: iterator is null");
		}
		if (iterator == last) {
			return length - 1;
		}
		int index = 0;
		for (Node i = first; i != iterator; i = i.next) {
			index++;
		}
		return index;
	}

	/**
	 * Searches the LinkedList for a given element's index
	 * 
	 * @param data the data whose index to locate
	 * @return the index of the data or -1 if the data is not contained in the
	 *         LinkedList
	 */
	public int findIndex(T data) {
		int index = 0;
		Node i = first;
		while (i != null && !i.data.equals(data)) {
			index++;
			i = i.next;
		}
		if (i == null) {
			return -1;
		} else {
			return index;
		}
	}

	/**
	 * Advances the iterator to location within
	 * the LinkedList specified by the given index
	 * @param index the index at which to place the
	 * iterator.
	 * @precondition iterator != offEnd or index < 0 or index >= length
	 * @throws NullPointerException when the precondition is
	 * violated
	 */
	public void advanceIteratorToIndex(int index) throws NullPointerException {
		if (offEnd() || index < 0 || index >= length) {
			throw new NullPointerException("advanceIteratorToIndex: iterator is null, or index given is out of bounds");
		}
		int iteratorIndex = getIteratorIndex();
		if (iteratorIndex < index) {
			for(int i = iteratorIndex; i < index; i++){
				advanceIterator();
			}
		} else {
			for(int i = iteratorIndex; i > index; i--){
				reverseIterator();
			}
		}
	}
}