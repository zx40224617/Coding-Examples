/**
 * LinkedListtest.java
 * @author Yun Chung Chang
 * @author Francis Chan
 * CIS 22C, Lab2
 */
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	@Test
	void testLinkedList() {
		LinkedList<Double> l = new LinkedList<>();
		assertTrue(l.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {
			l.getFirst();
		});
		assertThrows(NoSuchElementException.class, () -> {
			l.getLast();
		});
		assertThrows(NullPointerException.class, () -> {
			l.getIterator();
		});

	}

	@Test
	void testLinkedListTArray() {
		String[] nums = { "3", "2", "1" };
		LinkedList<Integer> l = new LinkedList(nums);
		assertEquals("3", l.getFirst()); // test 1
		assertEquals("1", l.getLast()); // test 2
		l.positionIterator();
		l.advanceIterator();
		assertEquals("2", l.getIterator()); // test 3
	}

	@Test
	void testLinkedListLinkedListOfT() {
		LinkedList<Integer> l = new LinkedList<>();
		LinkedList<Integer> L = new LinkedList(l);
		assertTrue(L.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {
			L.getFirst();
		});
		assertThrows(NoSuchElementException.class, () -> {
			L.getLast();
		});
		assertThrows(NullPointerException.class, () -> {
			L.getIterator();
		}); // test 1
		LinkedList<Integer> l1 = new LinkedList<>();
		l1.addFirst(1);
		l1.removeFirst();
		LinkedList<Integer> L1 = new LinkedList(l1);
		assertTrue(L1.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {
			L1.getFirst();
		});
		assertThrows(NoSuchElementException.class, () -> {
			L1.getLast();
		});
		assertThrows(NullPointerException.class, () -> {
			L1.getIterator();
		}); // test 2
		LinkedList<Integer> l2 = new LinkedList<>();
		l2.addFirst(1);
		l2.addFirst(2);
		l2.addFirst(3);
		LinkedList<Integer> L2 = new LinkedList(l2);
		assertEquals(3, L2.getFirst());
		assertEquals(1, L2.getLast());
		assertThrows(NullPointerException.class, () -> {
			L.getIterator();
		});

	}

	@Test
	void testGetFirst() {
		LinkedList<String> list = new LinkedList<>();
		assertThrows(NoSuchElementException.class, () -> {
			list.getLast();
		}); // test 1
		list.addFirst("A");
		assertEquals("A", list.getFirst()); // test 2
		list.addFirst("B");
		assertTrue(list.getFirst().equals("B")); // test 3
		list.removeFirst();
		assertNotEquals("B", list.getFirst()); // test 4
	}

	@Test
	void testGetLast() {
		LinkedList<String> list = new LinkedList<>();
		assertThrows(NoSuchElementException.class, () -> {
			list.getLast();
		}); // test 1
		list.addFirst("A");
		assertEquals("A", list.getLast()); // test 2
		list.addLast("B");
		assertTrue(list.getLast().equals("B")); // test 3
		list.removeLast();
		assertNotEquals("B", list.getLast()); // test 4
	}

	@Test
	void testGetIterator() {
		LinkedList<Integer> l = new LinkedList<>();
		assertThrows(NullPointerException.class, () -> {
			l.getIterator();
		});
		l.addFirst(2);
		l.positionIterator();
		assertEquals(2, l.getIterator());
		l.addLast(3);
		l.advanceIterator();
		assertEquals(3, l.getIterator());
	}

	@Test
	void testGetLength() {
		LinkedList<Integer> l = new LinkedList<>();
		assertEquals(0, l.getLength());
		l.addFirst(2);
		assertEquals(1, l.getLength());
		l.addFirst(3);
		assertEquals(2, l.getLength());
	}

	@Test
	void testIsEmpty() {
		LinkedList<Integer> l = new LinkedList<>();
		assertTrue(l.isEmpty());
		l.addFirst(1);
		assertFalse(l.isEmpty());
		l.removeFirst();
		assertTrue(l.isEmpty());

	}

	@Test
	void testOffEnd() {
		LinkedList<Integer> l = new LinkedList<>();
		assertTrue(l.offEnd());
		l.addFirst(1);
		l.positionIterator();
		assertFalse(l.offEnd());
		l.advanceIterator();
		assertTrue(l.offEnd());

	}

	@Test
	void testAddFirst() {
		LinkedList<Double> l = new LinkedList<>();
		l.addFirst(3.5);
		assertEquals("3.5 \n", l.toString()); // test1
		l.addFirst(4.9);
		assertEquals("4.9 3.5 \n", l.toString()); // test2
		// add a third test here - try calling getFirst
		l.addFirst(5.5);
		assertEquals(5.5, l.getFirst()); // test3

	}

	@Test
	void testAddLast() {
		LinkedList<Double> l = new LinkedList<>();
		l.addLast(3.5);
		assertEquals("3.5 \n", l.toString()); // test1
		l.addLast(4.9);
		assertEquals("3.5 4.9 \n", l.toString()); // test2
		// add a third test here - try calling getFirst
		l.addLast(5.5);
		assertEquals(5.5, l.getLast()); // test3
	}

	@Test
	void testAddIterator() {
		LinkedList<Integer> l = new LinkedList<>();
		assertThrows(NullPointerException.class, () -> {
			l.addIterator(0);
		}); // precondition
		l.addFirst(1);
		l.positionIterator();
		l.addIterator(2);
		assertEquals(2, l.getLast()); // edge case
		l.addFirst(3);
		l.addFirst(5);
		l.positionIterator();
		l.addIterator(4);
		l.advanceIterator();
		assertEquals(4, l.getIterator());// general case

	}

	@Test
	void testRemoveFirst() {
		LinkedList<String> L = new LinkedList<>();
		// testing precondition:
		assertThrows(NoSuchElementException.class, () -> {
			L.removeFirst();
		});
		// add 3 more tests below
		L.addFirst("1");
		L.removeFirst();
		// test 1
		assertThrows(NoSuchElementException.class, () -> {
			L.getFirst();
		});
		assertThrows(NoSuchElementException.class, () -> {
			L.getLast();
		});
		assertThrows(NullPointerException.class, () -> {
			L.getIterator();
		});
		L.addFirst("2");
		L.addFirst("3");
		L.removeFirst();
		// test 2
		assertThrows(NullPointerException.class, () -> {
			L.getIterator();
		});
		L.addFirst("4");
		L.addFirst("5");
		L.removeFirst();
		// test 3
		assertEquals("4", L.getFirst());

	}

	@Test
	void testRemoveLast() {
		LinkedList<String> L = new LinkedList<>();
		// testing precondition:
		assertThrows(NoSuchElementException.class, () -> {
			L.removeLast();
		});
		// add 3 more tests below
		L.addFirst("1");
		L.removeLast();
		// test 1 (edge case)
		assertThrows(NoSuchElementException.class, () -> {
			L.getFirst();
		});
		assertThrows(NoSuchElementException.class, () -> {
			L.getLast();
		});
		assertThrows(NullPointerException.class, () -> {
			L.getIterator();
		});
		L.addFirst("2");
		L.addFirst("3");
		L.positionIterator();
		L.advanceIterator();
		L.removeLast();
		// test 2
		assertThrows(NullPointerException.class, () -> {
			L.getIterator();
		});
		L.addFirst("4");
		L.addFirst("5");
		L.removeLast();
		// test 3
		assertEquals("4", L.getLast());
		L.positionIterator();
		L.advanceIterator();
		L.removeLast();
		assertThrows(NullPointerException.class, () -> {
			L.getIterator();
		});
	}

	@Test
	void testRemoveIterator() {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("Hi");

		list.addLast("Bye");
		list.positionIterator();
		list.removeIterator();
		assertThrows(NullPointerException.class, () -> {
			list.removeIterator();
		});
		list.positionIterator();
		assertEquals(list.getIterator(), "Bye");
		list.addIterator("What's Up");
		list.removeIterator();
		list.positionIterator();
		assertEquals(list.getIterator(), "What's Up");
		list.addLast("Test");
		list.removeIterator();
		list.positionIterator();
		assertEquals(list.getIterator(), "Test");

	}

	@Test
	void testPositionIterator() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addFirst(1);
		list.positionIterator();
		assertEquals(list.getIterator(), 1);
		list.addLast(2);
		list.removeFirst();
		list.positionIterator();
		assertEquals(list.getIterator(), 2);
		list.addFirst(5);
		list.addLast(10);
		list.positionIterator();
		assertEquals(list.getIterator(), 5);

	}

	@Test
	void testAdvanceIterator() {
		LinkedList<String> list = new LinkedList<String>();
		assertThrows(NullPointerException.class, () -> {
			list.advanceIterator();
		});
		list.addFirst("One");
		list.addFirst("Two");
		list.addFirst("Three");
		list.positionIterator();
		list.advanceIterator();
		assertEquals(list.getIterator(), "Two");
		list.addIterator("Test");
		list.advanceIterator();
		assertEquals(list.getIterator(), "Test");
		list.advanceIterator();
		assertEquals(list.getIterator(), "One");

	}

	@Test
	void testReverseIterator() {
		LinkedList<String> list = new LinkedList<String>();
		assertThrows(NullPointerException.class, () -> {
			list.reverseIterator();
		});
		list.addFirst("First");
		list.addFirst("Second");
		list.addFirst("Third");
		list.positionIterator();
		list.advanceIterator();
		list.advanceIterator();
		list.reverseIterator();
		assertEquals(list.getIterator(), "Second");
		list.reverseIterator();
		assertEquals(list.getIterator(), "Third");
		list.addFirst("Fourth");
		list.reverseIterator();
		assertEquals(list.getIterator(), "Fourth");

	}

	@Test
	void testToString() {
		LinkedList<Integer> L = new LinkedList<>();
		assertEquals("\n", L.toString()); // test 1
		L.addFirst(1);
		assertEquals("1 \n", L.toString()); // test2
		L.addFirst(2);
		assertEquals("2 1 \n", L.toString()); // test3
	}

	@Test
	void testEqualsObject() {
		LinkedList<Integer> l = new LinkedList<>();
		l.addFirst(1);
		l.addFirst(2);
		LinkedList L = l;
		assertTrue(l.equals(L));
		int[] num = new int[10];
		num[0] = 2;
		num[1] = 1;
		assertFalse(l.equals(num));
		LinkedList<Integer> G = new LinkedList<>();
		G.addFirst(0);
		assertFalse(l.equals(G));
		LinkedList<Integer> l2 = new LinkedList<>();
		l2.addFirst(1);
		l2.addFirst(2);
		assertTrue(l.equals(l2));
	}

	@Test
	void testRevolvingList() {
		LinkedList<String> nullList = null;
		LinkedList<String> iList = new LinkedList<>();
		LinkedList<String> sList1 = new LinkedList<>(new String[] { "!", "?", "." });
		LinkedList<String> sList2 = new LinkedList<>(new String[] { "!", "?", ".", "," });

		assertEquals(sList1.interlockLists(nullList).toString(), sList1.toString());
		assertEquals(iList.interlockLists(sList1).toString(), sList1.toString());
		LinkedList<String> temp = sList1.interlockLists(sList2);
		assertEquals(sList1.toString(), "! ? . \n");
		assertEquals(sList2.toString(), "! ? . , \n");
		assertEquals(temp.toString(), "! ! ? ? . . , \n");

		temp = sList2.interlockLists(sList1);
		assertEquals(sList1.toString(), "! ? . \n");
		assertEquals(sList2.toString(), "! ? . , \n");
		assertEquals(temp.toString(), "! ! ? ? . . , \n");

	}

	@Test
	void testInterlockLists() {
		LinkedList<String> nullList = null;
		LinkedList<String> iList = new LinkedList<>();
		LinkedList<String> sList1 = new LinkedList<>(new String[] { "!", "?", "." });
		LinkedList<String> sList2 = new LinkedList<>(new String[] { "!", "?", ".", "," });

		
		assertEquals(sList1.interlockLists(nullList).toString(), sList1.toString());
		assertEquals(iList.interlockLists(sList1).toString(), sList1.toString());

		LinkedList<String> temp = sList1.interlockLists(sList2);
		assertEquals(sList1.toString(), "! ? . \n");
		assertEquals(sList2.toString(), "! ? . , \n");
		assertEquals(temp.toString(), "! ! ? ? . . , \n");

		temp = sList2.interlockLists(sList1);
		assertEquals(sList1.toString(), "! ? . \n");
		assertEquals(sList2.toString(), "! ? . , \n");
		assertEquals(temp.toString(), "! ! ? ? . . , \n");

	}
	
	@Test
    void testGetIteratorIndex() {
        LinkedList<String> sList = new LinkedList<>(new String[] {"!", "?", ".", ","});
        assertThrows(NullPointerException.class, ()->{sList.getIteratorIndex();});
        sList.positionIterator();
        assertEquals(0, sList.getIteratorIndex());
        sList.advanceIterator();
        sList.advanceIterator();
        assertEquals(2, sList.getIteratorIndex());
        sList.advanceIterator();
        assertEquals(3, sList.getIteratorIndex());
        sList.advanceIterator();
        assertThrows(NullPointerException.class, ()->{sList.getIteratorIndex();});
        
    }
    
    @Test
    void testFindIndex() {
        LinkedList<Integer> iList = new LinkedList<>();
        assertEquals(-1, iList.findIndex(4));
        LinkedList<String> sList = new LinkedList<>(new String[] {"!", "?", ".", ","});
        assertEquals(0, sList.findIndex("!"));
        assertEquals(1, sList.findIndex("?"));
        assertThrows(NullPointerException.class, ()->{sList.getIterator();});
        assertEquals(2, sList.findIndex("."));
        assertEquals(3, sList.findIndex(","));
        
    }
    
    @Test 
    void testAdvanceIteratorToIndex() {
        LinkedList<Integer> iList = new LinkedList<>();
        assertThrows(NullPointerException.class, ()->{iList.advanceIteratorToIndex(3);});
        LinkedList<String> sList = new LinkedList<>(new String[] {"!", "?", ".", ","});
        assertThrows(NullPointerException.class, ()->{sList.advanceIteratorToIndex(2);});
        sList.positionIterator();
        sList.advanceIteratorToIndex(2);
        assertEquals(".", sList.getIterator());
        assertThrows(NullPointerException.class, ()->{sList.advanceIteratorToIndex(4);});
        sList.positionIterator();
        sList.advanceIteratorToIndex(3);
        assertEquals(",", sList.getIterator());
        sList.positionIterator();
        sList.advanceIteratorToIndex(0);
        assertEquals("!", sList.getIterator());
        
    }

}