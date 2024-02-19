/**
 * @author Jennifer Parrish
 * CIS 22C, Lab 3
 * IMPORTANT: DO NOT ALTER THIS FILE
 */

import java.util.NoSuchElementException;

public interface LIFO<T> {
    /**
     * Returns the value stored at the front
     * of the Stack
     * @return the value at the front of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    T peek() throws NoSuchElementException;
    
    /**
     * Returns the size of the Stack
     * @return the size from 0 to n
     */
    int getSize();
    
    /**
     * Determines whether a Stack is empty
     * @return whether the Stack contains
     * no elements
     */
    boolean isEmpty();
    
    /**
     * Inserts a new value in the Stack
     * 
     * @param data the new data to insert
     * @postcondition a new node in the Stack
     */
    void push(T data);
    
    /**
     * Removes the top element in the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition the front element has 
     * been removed
     */
    void pop() throws NoSuchElementException;
    
    /**
     * Creates a String of the Stack in reverse order
     * @return a Stack in reverse order
     */
    String reverseStack();
}