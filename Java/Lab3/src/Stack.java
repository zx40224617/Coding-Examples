/**
 * Stack class - singly-linked list version
 * @author
 * @author
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

import LinkedList.Node;

public class Stack<T> implements LIFO<T> {
    private class Node {
        private T data;
        private Node next;
        
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node top;
    private int size;
    
    /****CONSTRUCTORS****/
    
    /**
     * Default constructor for the Stack class
     * @postcondition a new Stack object with all fields
     * assigned default values
     */
    public Stack() {
    	top = null;
    	size = 0;
    }
    
    /**
     * Constructor for the Stack class
     * Converts an array into a Stack in the same order
     * @param an array of elements to copy
     * e.g. [1,2,3] becomes 1->2->3->null
     */
    public Stack(T[] array) {
        if(array == null) {
        	return;
        } else {
        	for(int i = array.length - 1; i >= 0; i--) {
        		push(array[i]);
        	}
        }
    }
    
    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is
     * an identical, but distinct, copy of original
     * REQUIRED: THIS METHOD MUST BE IMPLEMENTED
     * IN O(N) TIME
     */
    public Stack(Stack<T> original) {
	   	if (original == null) {
			return;
		} 
		if (original.size == 0) {
			this.size = 0;
			this.top = null;
	    } else {
	    	Stack<T> s = new Stack<T>();
	    	Node temp = original.top;
	    	while(temp != null) {
	    		s.push(temp.data);
	    		temp = temp.next;
	    	} 
	    	
	    	temp = s.top;
	    	while(temp != null) {
	    		this.push(temp.data);
	    		temp = temp.next;
	    	}
	    	
	    }
}
    
    /****ACCESSORS****/
    
    /**FILL IN HERE*/
    
    /****MUTATORS****/
    
    /**FILL IN HERE*/
    
    /****ADDITONAL OPERATIONS****/
    
    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    public String toString() {
    	StringBuilder result = new StringBuilder();
    	Node temp = top;
    	while(temp != null) {
    		result.append(temp.data + " ");
    		temp = temp.next;
    	}
        return result.toString() + "\n";
    }
 
    
    /**
     * Determines whether two Stacks contain
     * the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this Stack are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	} else if (!(obj instanceof Stack)) {
    		return false;
    	} else {
    		Stack<T> s = (Stack<T>) obj;
    		if (this.size != s.size) {
    			return false;
    		} else {
    			Node temp1 = this.top;
    			Node temp2 = s.top;
    			while(temp1 != null && temp2 != null) {
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
  
    /**RECURSIVE HELPER METHOD*/
    
    /**
     * Recursively (no loops) creates a String
     * where the data is in reverse order
     * @param n the current node
     */
    private String reverseStack(Node n) {  
    	 if(n == null ) {
             return "";
         } else {
             return reverseStack(n.next) + n.data + " ";
         }    
    }

	@Override
	public T peek() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("peek(): The stack is emnpty!");
		} else {
			return top.data;
		}
		
	}

	@Override
	public int getSize() {
		return this.size;
	
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
		
	}

	@Override
	public void push(T data) {
		Node N = new Node(data);
		N.next = top;
		top = N;
		size++;
		
	}
	 /**
     * Removes the top element in the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition the front element has 
     * been removed
     */
	@Override
	public void pop() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("pop(): The stack is empty!");
		} else {
			top = top.next;
			size--;
		}
		
	}

	@Override
	public String reverseStack() {
		Node temp = this.top;
		return reverseStack(temp) + "\n";
	}
}