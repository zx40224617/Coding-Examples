import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class QueueTest {

	@Test
	void testQueue() {
		fail("Not yet implemented");
	}

	@Test
	void testQueueTArray() {
		fail("Not yet implemented");
	}

	@Test
	void testQueueQueueOfT() {
		fail("Not yet implemented");
	}

	@Test
	void testGetFront() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSize() {
		fail("Not yet implemented");
	}

	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	void testEnqueue() {
		Queue q = new Queue<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		assertEquals(q.getSize(), 3);
		assertEquals(q.getFront(), 1);
		assertFalse(q.isEmpty());
	}

	@Test
	void testDequeue() {
		Queue q = new Queue<Integer>();
		assertThrows(NoSuchElementException.class, () -> {
			q.dequeue();
		});
		q.enqueue(1);
		q.dequeue();
		assertThrows(NoSuchElementException.class, () -> {
			q.getFront();
		});
		assertTrue(q.isEmpty());
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.dequeue();
	    assertEquals(q.getFront(), 2);
	    assertEquals(q.getSize(), 2);
	    
		
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		
	}

	@Test
	void testReverseQueue() {
		 Queue<String> two = new Queue<>();
	        two.enqueue("C");
	        two.enqueue("B");
	        two.enqueue("A");
	        assertEquals("A B C \n", two.reverseQueue());
	        
	       
	}

	@Test
	void testIsSorted() {
		Queue<Integer> s = new Queue<Integer>();
        s.enqueue(5);
        s.enqueue(6);
        s.dequeue();
        assertTrue(s.isSorted());
        s.enqueue(7);
        assertTrue(s.isSorted());
        s.enqueue(2);
        assertFalse(s.isSorted());

	}

	@Test
	void testLinearSearch() {
		Queue<Integer> s = new Queue<Integer>();
        s.enqueue(1);
        s.enqueue(2);
        s.dequeue();
        assertFalse(s.linearSearch(1));
        s.enqueue(5);
        assertTrue(s.linearSearch(5));
	}

	@Test
	void testBinarySearch() {
		Queue q = new Queue<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		assertTrue(q.binarySearch(3));

}
}
