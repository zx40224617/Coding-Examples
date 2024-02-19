
/**
 * Defines a doubly-linked list class
 * LinkedList.java
 * @author Yun Chung Chang
 * @author Francis Chan
 * CIS 22C, Lab6
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
	 * @postcondition a new LinkedList with default value is created
	 */
	public LinkedList() {
		first = null;
		last = null;
		iterator = null;
		length = 0;
	}

	/**
	 * Converts the given array into a LinkedList
	 * 
	 * @param array the array of values to insert into this LinkedList
	 * @postcondition array is converted into a LinkedList
	 */
	public LinkedList(T[] array) {
		if (array == null) {
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
	 * @postcondition a new List object, which is an identical, but separate, copy
	 *                of the LinkedList original
	 */
	public LinkedList(LinkedList<T> original) {
		if (original == null) {
			return;
		}
		if (original.length == 0) {
			this.length = 0;
			this.first = null;
			this.last = null;
			this.iterator = null;

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
	 * @precondition length !=0
	 * @return the value stored at node first
	 * @throws NoSuchElementException when list is empty
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst(): List is empty!");
		}
		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition length != 0
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when list is empty
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast(): List is empty!");
		}
		return last.data;
	}

	/**
	 * Returns the data stored in the iterator node
	 * 
	 * @precondition iterator is not null
	 * @throw NullPointerException when the iterator is null
	 */
	public T getIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIterator(): Iterator is null!");
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
	 * @postcondition a new first node is added; length ++
	 */
	public void addFirst(T data) {
		Node N = new Node(data);
		if (length == 0) {
			first = last = N;
		} else {
			N.next = first;
			first.prev = N;
			first = N;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the LinkedList
	 * @postcondition a new last node is added; length++
	 */
	public void addLast(T data) {
		Node N = new Node(data);
		if (length == 0) {
			first = last = N;
		} else {
			last.next = N;
			N.prev = last;
			last = N;
		}
		length++;
	}

	/**
	 * Inserts a new element after the iterator
	 * 
	 * @param data the data to insert
	 * @precondition iterator cannot be null
	 * @postcondition a new last node is added; length++
	 * @throws NullPointerException when the iterator is null
	 */
	public void addIterator(T data) throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("addIterator(): iterator is " + "null. Cannot add");
		} else if (iterator == last) {
			addLast(data);
		} else {
			Node n = new Node(data);
			n.prev = iterator;
			n.next = iterator.next;
			iterator.next.prev = n;
			iterator.next = n;
			length++;
		}

	}

	/**
	 * removes the element at the front of the LinkedList
	 * 
	 * @precondition length != 0
	 * @postcondition first node is removed
	 * @throws NoSuchElementException when the list is empty
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst(): Cannot remove " + "from an empty List!");
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
	 * removes the element at the end of the LinkedList
	 * 
	 * @precondition length != 0
	 * @postcondition the last node is removed
	 * @throws NoSuchElementException when the list is empty
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast(): Cannot remove" + "from an empty list");
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
	 * removes the element referenced by the iterator
	 * 
	 * @precondition iterator cannot be null
	 * @postcondition iterator = null
	 * @throws NullPointerException when the iterator is null
	 */
	public void removeIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("removeIterator():Iterator is null. " + "Cannot remove.");
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
	 * places the iterator at the first node
	 * 
	 * @postcondition the iterator is placed at first node
	 */
	public void positionIterator() {

		iterator = first;
	}

	/**
	 * Moves the iterator one node towards the last
	 * 
	 * @precondition the iterator cannot be null
	 * @postcondition the iterator moved one node towards the last
	 * @throws NullPointerException when the iterator is null
	 */
	public void advanceIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("advanceIterator():Iterator is null. Cannot advance.");
		}
		iterator = iterator.next;
	}

	/**
	 * Moves the iterator one node towards the first
	 * 
	 * @precondition the iterator cannot be null
	 * @postcondition the iterator moved one node towards the first
	 * @throws NullPointerException when the iterator is null
	 */
	public void reverseIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("reverseIterator(): Iterator is null");
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
		} else if (!(o instanceof LinkedList)) {
			return false;
		} else {
			LinkedList<T> L = (LinkedList<T>) o;
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;
				while (temp1 != null && temp2 != null) {
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
	 * Moves all nodes in the list towards the end of the list the number of times
	 * specified Any node that falls off the end of the list as it moves forward
	 * will be placed the front of the list For example: [1, 2, 3, 4, 5], numMoves =
	 * 2 -> [4, 5, 1, 2 ,3] For example: [1, 2, 3, 4, 5], numMoves = 4 -> [2, 3, 4,
	 * 5, 1] For example: [1, 2, 3, 4, 5], numMoves = 7 -> [4, 5, 1, 2 ,3]
	 * 
	 * @param numMoves the number of times to move each node.
	 * @precondition numMoves >= 0
	 * @postcondition iterator position unchanged (i.e. still referencing the same
	 *                node in the list, regardless of new location of Node)
	 * @throws IllegalArgumentException when numMoves < 0
	 */
	public void revolvingList(int numMoves) throws IllegalArgumentException {
		if (numMoves < 0) {
			throw new IllegalArgumentException("Number of moves must greater than 0");
		} else if (this == null || length == 0) {
			return;
		} else {
			int size = numMoves % length;
			Node temp = first;
			for (int i = 0; i < (length - size); i++) {
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
		int temp;
		if (list == null || list.length == 0) {
			return this;
		}
		if (this.length > list.length) {
			temp = this.length;
		} else {
			temp = list.length;
		}
		LinkedList<T> l = new LinkedList<T>();
		Node f = this.first;
		Node s = list.first;
		for (int i = 0; i < temp; i++) {

			if (f != null) {
				l.addLast(f.data);
				f = f.next;

			}
			if (s != null) {
				l.addLast(s.data);
				s = s.next;
			}

		}
		return l;

	}

	/**
	 * Determines at which index the iterator is located Indexed from 0 to length -
	 * 1
	 * 
	 * @return the index number of the iterator
	 * @precondition iterator cannot be null
	 * @throws NullPointerException when precondition is violated
	 */
	public int getIteratorIndex() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIteratorIndex(): iterator is null");
		} else {
			int count = 0;
			Node temp = first;
			for (int i = 0; i < length; i++) {
				if (temp.equals(iterator)) {
					return count;
				} else {
					temp = temp.next;
					count++;
				}
			}
			return -1;

		}

	}

	/**
	 * Searches the LinkedList for a given element's index
	 * 
	 * @param data the data whose index to locate
	 * @return the index of the data or -1 if the data is not contained in the
	 *         LinkedList
	 */
	public int findIndex(T data) {
		int count = 0;
		Node temp = first;
		for (int i = 0; i < length; i++) {
			if (data.equals(temp.data)) {
				return count;
			} else {
				temp = temp.next;
				count++;
			}
		}
		return -1;
	}

	/**
	 * Advances the iterator to location within the LinkedList specified by the
	 * given index
	 * 
	 * @param index the index at which to place the iterator.
	 * @precondition index >= 0 && index < length
	 * @throws NullPointerException when the precondition is violated
	 */
	public void advanceIteratorToIndex(int index) throws NullPointerException {
		if (index < 0 || index >= length) {
			throw new NullPointerException("advanceIteratorToIndex(): Index out of bounds");
		} else {
			for (int i = 0; i < index; i++) {
				iterator = iterator.next;
			}
		}
	}
}