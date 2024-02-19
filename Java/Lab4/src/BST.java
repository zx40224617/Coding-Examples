
/**
 * BST.java
 * @author Yun Chung Chang
 * @author Francis Chan
 * CIS 22C Lab 4
 */

import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
	private class Node {
		private T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST sets root to null
	 */
	public BST() {
		root = null;
	}

	/**
	 * Copy constructor for BST
	 * 
	 * @param bst the BST to make a copy of
	 */
	public BST(BST<T> bst) {
		if (bst == null) {
			return;
		} else {
			this.copyHelper(bst.root);
		}
	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing data to copy
	 */
	private void copyHelper(Node node) {
		if (node == null) {
			return;
		} else {
			this.insert(node.data);
			this.copyHelper(node.left);
			this.copyHelper(node.right);
		}

	}

	/**
	 * Creates a BST of minimal height from an array of values
	 * 
	 * @param array the list of values to insert
	 * @precondition array must be sorted in ascending order
	 * @throws IllegalArgumentException when the array is unsorted
	 */
	public BST(T[] array) throws IllegalArgumentException {
		if (!isSorted(array)) {
			throw new IllegalArgumentException("BSTArray(): List is not sorted");
		} else {
			if (array == null) {
				root = null;
			} else {
				root = arrayHelper(0, array.length - 1, array);
			}

		}
	}

	/**
	 * Private helper method for array constructor to check for a sorted array
	 * 
	 * @param array the array to check
	 * @return whether the array is sorted
	 */
	private boolean isSorted(T[] array) {
		if (array == null) {
			return true;
		} else {
			for (int i = 0; i < array.length - 1; i++) {
				for (int j = 0; j < array.length - 1; j++) {
					if (array[j].compareTo(array[j + 1]) > 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Recursive helper for the array constructor
	 * 
	 * @param begin beginning array index
	 * @param end   ending array index
	 * @param array array to search
	 * @return the newly created Node
	 */
	private Node arrayHelper(int begin, int end, T[] array) {
		if (begin > end) {
			return null;
		}
		int mid = (begin + end) / 2;
		Node n = new Node(array[mid]);
		n.left = arrayHelper(begin, mid - 1, array);
		n.right = arrayHelper(mid + 1, end, array);
		return n;

	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getRoot() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException("getRoot(): There is no root");
		} else {
			return root.data;
		}
	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		if (this.getSize() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + getSize(node.right) + getSize(node.left);
		}
	}

	/**
	 * Returns the height of tree by counting edges.
	 * 
	 * @return the height of the tree
	 */
	public int getHeight() {
		if (isEmpty()) {
			return -1;
		} else {
			return getHeight(root);
		}
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		} else {
			int a = 1 + getHeight(node.right);
			int b = 1 + getHeight(node.left);
			if (a > b) {
				return a;
			} else {
				return b;
			}
		}
	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMin() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMin(): The BSt is empty, can't find min");
		} else {
			return findMin(root);
		}
	}

	/**
	 * Helper method to findMin method
	 * 
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private T findMin(Node node) {
		if (node.left != null) {
			return findMin(node.left);
		} else {
			return node.data;
		}
	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMaz(): The BSt is empty, can't find max");
		} else {
			return findMax(root);
		}
	}

	/**
	 * Helper method to findMax method
	 * 
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private T findMax(Node node) {
		if (node.right != null) {
			return findMax(node.right);
		} else {
			return node.data;
		}
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 */
	public void insert(T data) {
		if (isEmpty()) {
			root = new Node(data);
		} else {
			insert(data, root);
		}
	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */
	private void insert(T data, Node node) {
		if (data.compareTo(node.data) <= 0) {
			if (node.left == null) {
				node.left = new Node(data);
			} else {
				insert(data, node.left);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(data);
			} else {
				insert(data, node.right);
			}
		}
	}

	/**
	 * Removes a value from the BST
	 * 
	 * @param data the value to remove
	 */
	public void remove(T data) {
		root = remove(data, root);
	}

	/**
	 * Helper method to the remove method
	 * 
	 * @param data the data to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node remove(T data, Node node) {
		if (node == null) {
			return node;
		} else if (data.compareTo(node.data) < 0) {
			node.left = remove(data, node.left);
		} else if (data.compareTo(node.data) > 0) {
			node.right = remove(data, node.right);
		} else {
			if (node.left == null && node.right == null) {
				node = null;
			} else if (node.left != null && node.right == null) {
				node = node.left;
			} else if (node.left == null && node.right != null) {
				node = node.right;
			} else {
				node.data = findMin(node.right);
				node.right = remove(findMin(node.right), node.right);
			}
		}
		return node;
	}

	/*** ADDITIONAL OPERATIONS ***/

	/**
	 * Searches for a specified value in the tree
	 * 
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	public boolean search(T data) {
		if (root == null) {
			return false;
		} else {
			return search(data, root);
		}
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	private boolean search(T data, Node node) {
		if (data.equals(node.data)) {
			return true;
		} else if (data.compareTo(node.data) < 0) {
			if (node.left == null) {
				return false;
			} else {
				return search(data, node.left);
			}
		} else {
			if (node.right == null) {
				return false;
			} else {
				return search(data, node.right);
			}
		}
	}

	/**
	 * Returns a String containing the data in post order
	 * 
	 * @return a String of data in post order
	 */
	public String preOrderString() {
		if (root == null) {
			return "\n";
		} else {
			StringBuilder sb = new StringBuilder("");
			preOrderString(root, sb);
			return sb.toString() + "\n";
		}
	}

	/**
	 * Helper method to preOrderString Inserts the data in pre order into a String
	 * 
	 * @param node     the current Node
	 * @param preOrder a String containing the data
	 */
	private void preOrderString(Node node, StringBuilder preOrder) {
		if (node == null) {
			return;
		} else {
			preOrder.append(node.data + " ");
			preOrderString(node.left, preOrder);
			preOrderString(node.right, preOrder);
		}
	}

	/**
	 * Returns a String containing the data in order
	 * 
	 * @return a String of data in order
	 */
	public String inOrderString() {
		if (root == null) {
			return "\n";
		} else {
			StringBuilder sb = new StringBuilder("");
			inOrderString(root, sb);
			return sb.toString() + "\n";
		}
	}

	/**
	 * Helper method to inOrderString Inserts the data in order into a String
	 * 
	 * @param node    the current Node
	 * @param inOrder a String containing the data
	 */
	private void inOrderString(Node node, StringBuilder inOrder) {
		if (node == null) {
			return;
		} else {
			inOrderString(node.left, inOrder);
			inOrder.append(node.data + " ");
			inOrderString(node.right, inOrder);
		}
	}

	/**
	 * Returns a String containing the data in post order
	 * 
	 * @return a String of data in post order
	 */
	public String postOrderString() {
		if (root == null) {
			return "\n";
		} else {
			StringBuilder sb = new StringBuilder("");
			postOrderString(root, sb);
			return sb.toString() + "\n";
		}
	}

	/**
	 * Helper method to postOrderString Inserts the data in post order into a String
	 * 
	 * @param node      the current Node
	 * @param postOrder a String containing the data
	 */
	private void postOrderString(Node node, StringBuilder postOrder) {
		if (node == null) {
			return;
		} else {
			postOrderString(node.left, postOrder);
			postOrderString(node.right, postOrder);
			postOrder.append(node.data + " ");
		}

	}

	/**
	 * Creates a String that is a height order traversal of the data in the tree
	 * starting at the Node with the largest height (the root) down to Nodes of
	 * smallest height - with Nodes of equal height added from left to right
	 * 
	 * @return the height order traversal as a String
	 */
	public String heightTraverseString() {
		Queue q = new Queue<Integer>();
		StringBuilder s = new StringBuilder();
		if (root == null) {
			return "\n";
		} else {
			q.enqueue(root);
		}
		heightTraverseString(q, s);
		return s.toString() + "\n";
	}

	/**
	 * Helper method to heightrderString Inserts the data in height order into a
	 * String
	 * 
	 * @param q              the Queue in which to store the data
	 * @param heightTraverse a StringBuilder containing the data
	 */

	private void heightTraverseString(Queue<Node> q, StringBuilder heightTraverse) {

		if (!q.isEmpty()) {
			Node temp = q.getFront();
			heightTraverse.append(temp.data + " ");
			if (temp.left != null) {
				q.enqueue(temp.left);

			}
			if (temp.right != null) {
				q.enqueue(temp.right);

			}
			q.dequeue();
			heightTraverseString(q, heightTraverse);
		}
	}

	/**
	 * Returns the data of the Node who is the shared precursor to the two Nodes
	 * containing the given data If the either data1 or data2 is a duplicate value,
	 * the method will find the precursor of the duplicate with greatest height
	 * 
	 * @param data1 the data contained in one Node of the tree
	 * @param data2 the data contained in one Node of the tree
	 * @return the data stored by the shared precursor or null if no precursor
	 *         exists.
	 * @precondition data1 and data2 must exist in the BST
	 * @throws IllegalArgumentException when one or both values do not exist in the
	 *                                  BST
	 */
	public T sharedPrecursor(T data1, T data2) throws IllegalArgumentException {
		if (!(search(data1) && search(data2))) {
			throw new IllegalArgumentException("sharedPrecursor(): data is not in the tree.");
		} else {
			return sharedPrecursor(data1, data2, root);
		}

	}

	/**
	 * Private helper method to sharedPrecursor, which recursively locates the
	 * shared precursor
	 * 
	 * @param data1     the data contained in one Node of the tree
	 * @param data2     the data contained in one Node of the tree
	 * @param currLevel the current Node
	 * @return the data stored by the shared precursor
	 */
	private T sharedPrecursor(T data1, T data2, Node currLevel) {
		if (currLevel == null) {
			return null;
		} else if (currLevel.data.compareTo(data1) > 0 && currLevel.data.compareTo(data2) > 0) {
			return sharedPrecursor(data1, data2, currLevel.left);

		} else if (currLevel.data.compareTo(data1) < 0 && currLevel.data.compareTo(data2) < 0) {
			return sharedPrecursor(data1, data2, currLevel.right);
		} else {
			return currLevel.data;
		}
	}

}