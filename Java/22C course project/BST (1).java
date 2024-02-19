/**
 * BST.java
 * @author Emir Elzein
 * @author Wei Quan Lai
 * CIS 22C Lab 4
 */

import java.util.ArrayList;
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
    
    /***CONSTRUCTORS***/
    
    /**
     * Default constructor for BST
     * sets root to null
     */
    public BST() { 
    	root = null;
    }
   
    /**
     * Copy constructor for BST
     * @param bst the BST to make
     * a copy of 
     */
    public BST(BST<T> bst) {
       if(bst == null || bst.root == null) {
    	   return;
       } else {
    	   copyHelper(bst.root);
       }
    }
    
    /**
     * Helper method for copy constructor
     * @param node the node containing
     * data to copy
     */
    private void copyHelper(Node node) {
		if(node == null) {
			return;
		} else {
			insert(node.data);
			copyHelper(node.left);
			copyHelper(node.right);
		}
    }

   /**
     * Creates a BST of minimal height from an array of values
     * @param array the list of values to insert
     * @precondition array must be sorted in ascending order
     * @throws IllegalArgumentException when the array is
     * unsorted
     */
    public BST(T[] array) throws IllegalArgumentException {
    	if (array == null || array.length == 0) {
     	   return;
        }
    	if(!isSorted(array)) {
    	   throw new IllegalArgumentException("BST(T[] array): " + "Array is not sorted");
       }
       root = arrayHelper(0, array.length - 1, array);
    }
    
    /**
     * Private helper method for array constructor
     * to check for a sorted array
     * @param array the array to check 
     * @return whether the array is sorted
     */
    private boolean isSorted(T[] array) {
        for(int i = 0; i < array.length - 2; i++) {
        	if(array[i].compareTo(array[i + 1]) > 0) {
        		return false;
        	}
        }
        return true;
    }
    
    /**
     * Recursive helper for the array constructor
     * @param begin beginning array index
     * @param end ending array index
     * @param array array to search
     * @return the newly created Node
     */
    private Node arrayHelper(int begin, int end, T[] array) {
    	if (begin > end) {
        	return null;
        }
        int mid = (begin + end) / 2;
        Node node = new Node(array[mid]);
        node.left = arrayHelper(begin, mid - 1, array);
        node.right = arrayHelper(mid + 1, end, array);
        return node;
    }
    
    /***ACCESSORS***/
    
    /**
     * Returns the data stored in the root
     * @precondition !isEmpty()
     * @return the data stored in the root
     * @throws NoSuchElementException when
     * precondition is violated
     */
    public T getRoot() throws NoSuchElementException { 
        if(isEmpty()) {
        	throw new NoSuchElementException("getRoot: " + "Tree is Empty, No root to get");
        }
        return root.data;
    }
    
    /**
     * Determines whether the tree is empty
     * @return whether the tree is empty
     */
    public boolean isEmpty() {
    	return root == null;
    }
    
    /**
     * Returns the current size of the 
     * tree (number of nodes)
     * @return the size of the tree
     */
    public int getSize() {
        return getSize(root);
    }
    
    /**
     * Helper method for the getSize method
     * @param node the current node to count
     * @return the size of the tree
     */
    private int getSize(Node node) {
    	if (node == null) {
    		return 0;
    	}
    	return getSize(node.left) + getSize(node.right) + 1;
    }
    
    /**
     * Returns the height of tree by
     * counting edges.
     * @return the height of the tree
     */
    public int getHeight() {
    	return getHeight(root);
    }
    
    /**
     * Helper method for getHeight method
     * @param node the current
     * node whose height to count
     * @return the height of the tree
     */
    private int getHeight(Node node) {
        if (node == null) {
        	return -1;
        }
        int leftSide = getHeight(node.left);
        int rightSide = getHeight(node.right);
        if (leftSide > rightSide) {
        	return leftSide + 1;
        } else {
        	return rightSide + 1;
        }
    }
    
    /**
     * Returns the smallest value in the tree
     * @precondition !isEmpty()
     * @return the smallest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T findMin() throws NoSuchElementException{
    	if(isEmpty()) {
        	throw new NoSuchElementException("findMin: " + "Tree is empty no value");
        }
        return findMin(root);
    }
    
    /**
     * Helper method to findMin method
     * @param node the current node to check
     * if it is the smallest
     * @return the smallest value in the tree
     */
    private T findMin(Node node) {
    	if(node.left == null) {
    		return node.data;
    	} else {
    		return findMin(node.left);
    	}
    }
    
    /**
     * Returns the largest value in the tree
     * @precondition !isEmpty()
     * @return the largest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T findMax() throws NoSuchElementException{
        if(isEmpty()) {
        	throw new NoSuchElementException("findMax: " + "Tree is empty no value");
        }
        return findMax(root);
    }
    
    /**
     * Helper method to findMax method
     * @param node the current node to check
     * if it is the largest
     * @return the largest value in the tree
     */
    private T findMax(Node node) {
    	if(node.right == null) {
    		return node.data;
    	} else {
    		return findMax(node.right);
    	}
    }
    
    /***MUTATORS***/
    
    /**
     * Inserts a new node in the tree
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
     * Helper method to insert
     * Inserts a new value in the tree
     * @param data the data to insert
     * @param node the current node in the
     * search for the correct location
     * in which to insert
     */
    private void insert(T data, Node node) {
    	if(search(data)) {
    		return;
    	}
    	if (data.compareTo(node.data) <= 0) {
    		if(node.left == null) {
    			node.left = new Node(data);
    		} else {
    		    insert(data, node.left);
            }
    	} else {
    		if(node.right == null) {
    			node.right = new Node(data);
    		} else {
    		    insert(data, node.right);
            }
    	}
    }

    /**
     * Removes a value from the BST
     * @param data the value to remove
     */
    public void remove(T data) {
    	root = remove(data, root);
    }
    
    /**
     * Helper method to the remove method
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    private Node remove(T data, Node node) {
        if(node == null) {
        	return node;
        } else if(data.compareTo(node.data) < 0) {
        	node.left = remove(data, node.left);
        } else if(data.compareTo(node.data) > 0) {
        	node.right = remove(data, node.right);
        } else {
        	if(node.left == null && node.right == null) {
        		node = null;
        	} else if (node.right == null) {
        		node = node.left;
        	} else if (node.left == null) {
        		node = node.right;
        	} else {
        		T minimum = findMin(node.right);
        		node.data = minimum;
        		node.right = remove(minimum, node.right);		
        	}
        }
        return node;
    }
    
        
    /***ADDITIONAL OPERATIONS***/
    
    /**
     * Searches for a specified value
     * in the tree
     * @param data the value to search for
     * @return whether the value is stored
     * in the tree
     */
    public boolean search(T data) {
        if (isEmpty()) {
            return false;
        } else {
            return search(data, root);
        }
    }
    
    /**
     * Helper method for the search method
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     * in the tree
     */
    private boolean search(T data, Node node) {
    	if (node.data == data) {
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
     * Returns a String containing the data
     * in post order
     * @return a String of data in post order
     */
    public String preOrderString() {
    	StringBuilder result = new StringBuilder();
    	preOrderString(root, result);
        return result + "\n";
    }
    
    /**
     * Helper method to preOrderString
     * Inserts the data in pre order into a String
     * @param node the current Node
     * @param preOrder a String containing the data
     */
    private void preOrderString(Node node, StringBuilder preOrder) {
    	if(node == null) {
    		return;
    	}
    	preOrder.append(node.data + " ");
    	preOrderString(node.left, preOrder);
    	preOrderString(node.right, preOrder);
    }
    
    /**
     * Returns a String containing the data
     * in order
     * @return a String of data in order
     */
    public String inOrderString() {
    	StringBuilder result = new StringBuilder();
    	inOrderString(root, result);
        return result + "\n";
    }
    
    /**
     * Helper method to inOrderString
     * Inserts the data in order into a String
     * @param node the current Node
     * @param inOrder a String containing the data
     */
    private void inOrderString(Node node, StringBuilder inOrder) {
    	if(node == null) {
    		return;
    	}
    	inOrderString(node.left, inOrder);
    	inOrder.append(node.data + " ");
    	inOrderString(node.right, inOrder);
    }
    
    /**
     * Returns a String containing the data
     * in post order
     * @return a String of data in post order
     */
    public String postOrderString() {
    	StringBuilder result = new StringBuilder();
    	postOrderString(root, result);
        return result + "\n";
    }
    
    /**
     * Helper method to postOrderString
     * Inserts the data in post order into a String
     * @param node the current Node
     * @param postOrder a String containing the data
     */
    private void postOrderString(Node node, StringBuilder postOrder) {
    	if(node == null) {
    		return;
    	}
    	postOrderString(node.left, postOrder);
    	postOrderString(node.right, postOrder);
    	postOrder.append(node.data + " ");
    }
    
//    /***CHALLENGE METHODS***/
//    
//    /**
//     * Creates a String that is a height order
//     * traversal of the data in the tree starting at 
//     * the Node with the largest height (the root)
//     * down to Nodes of smallest height - with
//     * Nodes of equal height added from left to right
//     * @return the height order traversal as a String
//     */
//    public String heightTraverseString() {
//        Queue<Node> q = new Queue<>();
//        StringBuilder heightTraverse = new StringBuilder();
//        if(root != null) {
//    		q.enqueue(root);
//    	}
//        heightTraverseString(q, heightTraverse);
//        return heightTraverse + "\n";
//    }
//    
//    /**
//     * Helper method to heightrderString
//     * Inserts the data in height order into a String
//     * @param q the Queue in which to store the data
//     * @param heightTraverse a StringBuilder containing the data
//     */
//    private void heightTraverseString(Queue<Node> q, StringBuilder heightTraverse) {
//    	Node temp = root;
//		if(q.isEmpty()) {
//			return;  	
//		}
//		temp = q.getFront();
//		heightTraverse.append(temp.data + " ");
//		if(temp.left != null) {
//			q.enqueue(temp.left);
//		}
//		if(temp.right != null) {
//			q.enqueue(temp.right);
//		}
//		q.dequeue();
//		heightTraverseString(q, heightTraverse);
//    }
//    /**
//     * Returns the data of the Node who is the shared precursor
//     * to the two Nodes containing the given data
//     * If the either data1 or data2 is a duplicate value, the method
//     * will find the precursor of the duplicate with greatest height
//     * @param data1 the data contained in one Node of the tree
//     * @param data2 the data contained in one Node of the tree
//     * @return the data stored by the shared precursor or null if no
//     * precursor exists.
//     * @precondition data1 and data2 must exist in the BST
//     * @throws IllegalArgumentException when one or both values do not exist
//     * in the BST
//     */
//    public T sharedPrecursor(T data1, T data2) throws IllegalArgumentException {
//        if(!search(data1) || !search(data2)) {
//        	throw new IllegalArgumentException("sharedPrecursoe: " + "One of the element does not exist");
//        }
//    	return sharedPrecursor(data1, data2, root);
//    }
//    
//    /**
//     * Private helper method to sharedPrecursor, which recursively locates
//     * the shared precursor
//     * @param data1 the data contained in one Node of the tree
//     * @param data2 the data contained in one Node of the tree
//     * @param currLevel the current Node
//     * @return the data stored by the shared precursor
//     */
//    private T sharedPrecursor(T data1, T data2, Node currLevel) {
//    	if(currLevel == null) {
//    		return null;
//    	} else if(currLevel.data.equals(data1)) {
//    		return data1;
//    	} else if(currLevel.data.equals(data2)) {
//    		return data2;
//    	} else {
//    		T left = sharedPrecursor(data1, data2, currLevel.left);
//    		T right = sharedPrecursor(data1, data2, currLevel.right);
//    		if(left != null && right != null) {
//    			return currLevel.data;    
//    		} else if(right == null) {
//    			return left;
//    		} else {
//    			return right;
//    		}
//    	}
//    }
    
    /**
     * Returns an ArrayList containing the data
     * in order
     * @return an ArrayList of data in order
     */
    public ArrayList<T> inOrderArrayList() {
    	ArrayList<T> result = new ArrayList<>();
    	inOrderArrayList(root, result);
        return result;
    }
    
    /**
     * Helper method to inOrderArray
     * Inserts the data in order into an ArrayList
     * @param node the current Node
     * @param inOrder an ArrayList containing the data
     */
    private void inOrderArrayList(Node node, ArrayList<T> inOrder) {
    	if(node == null) {
    		return;
    	}
    	inOrderArrayList(node.left, inOrder);
    	inOrder.add(node.data);
    	inOrderArrayList(node.right, inOrder);
    }
}