/**
 * @author Jennifer Parrish
 * CIS 22C, Lab 3
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class StackTest {
    private Stack<String> s1;
    private Stack<String> s2;
    private Stack<String> nullStack = null;
    private Stack<String> emptyStack;
    private Stack<String> s4;
    
    void setUp() {
        s1 = new Stack<>();
        for(int i = 'A'; i < 'M'; i++) {
            s1.push("" + (char)i);
            System.out.println(s1.peek());
        }
        
        s2 = new Stack<>(new String[] 
                {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"});
        
        emptyStack = new Stack<>();
        
        s4 = new Stack<>();
        for(int i = 'L'; i >= 'A'; i--) {
            s4.push("" + (char)i);
        }
    }
    @Test
    void testStack() {
        setUp();
        assertThrows(NoSuchElementException.class, ()->{emptyStack.peek();});
        assertTrue(emptyStack.isEmpty());
       
    }

    @Test
    void testStackTArray() {
        setUp();
        String[] array = null;
        Stack<String> stack = new Stack<>(array);
        assertTrue(stack.isEmpty());
        assertEquals("A", s2.peek());
        assertEquals(12, s2.getSize());
        
    }

    @Test
    void testStackStackOfT() {
        setUp();
        Stack<String> copy = new Stack<>(s1);
        assertEquals(s1.peek(), copy.peek());
        s1.pop();
        assertNotEquals(s1.peek(), copy.peek());
        copy = new Stack<>(nullStack);
        assertTrue(copy.isEmpty());
        copy = new Stack<>(emptyStack);
        assertTrue(copy.isEmpty());
    }

    @Test
    void testPeek() {
        setUp();
        assertThrows(NoSuchElementException.class, () -> {emptyStack.peek();});
        assertEquals("A", s2.peek());
        
    }

    @Test
    void testGetSize() {
        setUp();
        assertEquals(0, emptyStack.getSize());
        assertEquals(12, s2.getSize());
    }

    @Test
    void testIsEmpty() {
        setUp();
        assertTrue(emptyStack.isEmpty());
        assertFalse(s2.isEmpty());
    }

    @Test
    void testPush() {
        setUp();
        assertEquals(12, s1.getSize());
        assertEquals("L", s1.peek());
        assertEquals(12, s2.getSize());
        assertEquals("A", s2.peek());
        
    }

    @Test
    void testPop() {
        setUp();
        assertThrows(NoSuchElementException.class, () -> {
            emptyStack.pop();
            });
        s2.pop();
        assertEquals("B", s2.peek());
        emptyStack.push("A");
        emptyStack.pop();
        assertTrue(emptyStack.isEmpty());
        
    }

    @Test
    void testToString() {
        setUp();
        assertEquals("A B C D E F G H I J K L \n", s2.toString());
        assertEquals("\n", emptyStack.toString());
    }

    @Test
    void testEqualsObject() {
        Stack<String> one = new Stack<>();
        Stack<String> two = new Stack<>();
        String a = new String("A");
        String b = new String("B");
        String c = new String("C");
        one.push(a);
        two.push(a);
        one.push(b);
        two.push(b);
        one.push(c);
        assertFalse(one.equals(two));
        two.push(c);
        assertTrue(one.equals(two));
        assertFalse(one.equals(nullStack));
        assertFalse(one.equals(new Stack<String>()));
    }

    @Test
    void testReverseStack() {
        setUp();
        Stack<String> two = new Stack<>();
        two.push("C");
        two.push("B");
        two.push("A");
        assertEquals("C B A \n", two.reverseStack());
        Stack<String> one = new Stack<>();
        one.push("A");
        one.push("B");
        one.push("C");
        assertEquals("A B C \n", one.reverseStack());
        assertEquals("\n", emptyStack.reverseStack());
    }
}

