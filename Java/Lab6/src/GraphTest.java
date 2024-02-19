
/**
 * @author Jennifer Parrish
 * CIS 22C, Lab 6
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GraphTest {

	@Test
	void testGraph() {
		Graph g = new Graph(10);
		assertEquals(10, g.getNumVertices());
		assertEquals(0, g.getNumEdges());
		assertEquals('W', g.getColor(10));
		assertEquals(-1, g.getDistance(10));
		assertThrows(IllegalArgumentException.class, () -> {
			new Graph(0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Graph(-1);
		});

	}

	@Test
	void testGetNumEdges() {
		Graph g = new Graph(10);
		assertEquals(0, g.getNumEdges());
		g.addDirectedEdge(1, 10);
		g.addDirectedEdge(2, 4);
		assertEquals(2, g.getNumEdges());
		g = new Graph(5);
		g.addUndirectedEdge(2, 3);
		g.addUndirectedEdge(1, 4);
		g.addUndirectedEdge(4, 5);
		assertEquals(3, g.getNumEdges());
	}

	@Test
	void testGetNumVertices() {
		Graph g = new Graph(10);
		assertEquals(10, g.getNumVertices());
		g = new Graph(5);
		assertEquals(5, g.getNumVertices());
	}

	@Test
	void testIsEmpty() {
		Graph g = new Graph(10);
		assertTrue(g.isEmpty());
		g.addDirectedEdge(2, 4);
		assertFalse(g.isEmpty());
	}

	@Test
	void testGetDistance() {
		Graph g = new Graph(4);
		g.addUndirectedEdge(1, 2);
		g.addUndirectedEdge(2, 3);
		g.addUndirectedEdge(3, 4);
		assertEquals(-1, g.getDistance(3));
		g.BFS(1);
		assertEquals(0, g.getDistance(1));
		assertEquals(1, g.getDistance(2));
		assertEquals(2, g.getDistance(3));
		assertEquals(3, g.getDistance(4));

		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getDistance(5);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getDistance(0);
		});

	}

	@Test
	void testGetParent() {
		Graph g = new Graph(4);
		g.addUndirectedEdge(1, 2);
		g.addUndirectedEdge(2, 3);
		g.addUndirectedEdge(3, 4);
		assertEquals(0, g.getParent(3));
		g.BFS(1);
		assertEquals(0, g.getParent(1));
		assertEquals(1, g.getParent(2));
		assertEquals(2, g.getParent(3));
		assertEquals(3, g.getParent(4));

		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getParent(5);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getParent(0);
		});
	}

	@Test
	void testGetColor() {
		Graph g = new Graph(4);
		g.addUndirectedEdge(1, 2);
		g.addUndirectedEdge(2, 3);
		g.addUndirectedEdge(3, 4);
		assertEquals('W', g.getColor(3));
		g.BFS(1);
		assertEquals('B', g.getColor(1));
		assertEquals('B', g.getColor(2));
		assertEquals('B', g.getColor(3));
		assertEquals('B', g.getColor(4));

		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getColor(5);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getColor(0);
		});
	}

	@Test
	void testGetDiscoverTime() {
		Graph g = new Graph(5);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(2, 3);
		g.addDirectedEdge(3, 4);
		g.addDirectedEdge(2, 5);
		assertEquals(-1, g.getDiscoverTime(3));
		g.DFS();
		assertEquals(1, g.getDiscoverTime(1));
		assertEquals(2, g.getDiscoverTime(2));
		assertEquals(3, g.getDiscoverTime(3));
		assertEquals(4, g.getDiscoverTime(4));
		assertEquals(7, g.getDiscoverTime(5));

		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getDiscoverTime(6);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getDiscoverTime(0);
		});
	}

	@Test
	void testGetFinishTime() {
		Graph g = new Graph(5);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(2, 3);
		g.addDirectedEdge(3, 4);
		g.addDirectedEdge(2, 5);
		assertEquals(-1, g.getFinishTime(3));
		g.DFS();
		assertEquals(10, g.getFinishTime(1));
		assertEquals(9, g.getFinishTime(2));
		assertEquals(6, g.getFinishTime(3));
		assertEquals(5, g.getFinishTime(4));
		assertEquals(8, g.getFinishTime(5));

		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getFinishTime(6);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getFinishTime(0);
		});

	}

	@Test
	void testGetAdjacencyList() {
		Graph g = new Graph(4);
		g.addUndirectedEdge(1, 2);
		g.addUndirectedEdge(1, 3);
		g.addUndirectedEdge(2, 4);
		g.addUndirectedEdge(3, 4);
		assertEquals("2 3 \n", g.getAdjacencyList(1).toString());
		assertEquals("1 4 \n", g.getAdjacencyList(2).toString());
		assertEquals("1 4 \n", g.getAdjacencyList(3).toString());
		assertEquals("2 3 \n", g.getAdjacencyList(4).toString());

		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getAdjacencyList(5);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.getAdjacencyList(0);
		});
	}

	@Test
	void testAddDirectedEdge() {
		Graph g = new Graph(4);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(1, 3);
		g.addDirectedEdge(2, 4);
		g.addDirectedEdge(3, 4);
		assertEquals("1: 2 3 \n" + "2: 4 \n" + "3: 4 \n" + "4: \n", g.toString());
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.addDirectedEdge(5, 2);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.addDirectedEdge(0, 2);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.addDirectedEdge(2, 5);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.addDirectedEdge(2, 0);
		});
	}

	@Test
	void testAddUndirectedEdge() {
		Graph g = new Graph(4);
		g.addUndirectedEdge(1, 2);
		g.addUndirectedEdge(1, 3);
		g.addUndirectedEdge(2, 4);
		g.addUndirectedEdge(3, 4);
		assertEquals("1: 2 3 \n" + "2: 1 4 \n" + "3: 1 4 \n" + "4: 2 3 \n", g.toString());
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.addUndirectedEdge(5, 2);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.addUndirectedEdge(0, 2);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.addUndirectedEdge(2, 5);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.addUndirectedEdge(2, 0);
		});
	}

	@Test
	void testToString() {
		Graph g = new Graph(4);
		assertEquals("1: \n" + "2: \n" + "3: \n" + "4: \n", g.toString());
		g.addUndirectedEdge(1, 2);
		g.addUndirectedEdge(1, 3);
		g.addUndirectedEdge(2, 4);
		g.addUndirectedEdge(3, 4);
		assertEquals("1: 2 3 \n" + "2: 1 4 \n" + "3: 1 4 \n" + "4: 2 3 \n", g.toString());

	}

	@Test
	void testBFS() {
		Graph g = new Graph(7);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(1, 3);
		g.addDirectedEdge(2, 4);
		g.addDirectedEdge(3, 5);
		g.addDirectedEdge(3, 6);
		g.addDirectedEdge(5, 7);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.BFS(0);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			g.BFS(11);
		});
		g.BFS(1);
		assertEquals(3, g.getDistance(7));
		assertEquals('B', g.getColor(7));
		assertEquals(5, g.getParent(7));

		Graph g1 = new Graph(8);
		g1.addUndirectedEdge(1, 2);
		g1.addUndirectedEdge(1, 3);
		g1.addUndirectedEdge(2, 4);
		g1.addUndirectedEdge(3, 4);
		g1.addUndirectedEdge(5, 6);
		g1.addUndirectedEdge(6, 7);
		g1.addUndirectedEdge(6, 8);
		g1.BFS(4);
		assertEquals(-1, g1.getDistance(7));
		assertEquals('W', g1.getColor(7));
		assertEquals(0, g1.getParent(7));

		assertEquals(2, g1.getDistance(1));
		assertEquals('B', g1.getColor(1));
		assertEquals(2, g1.getParent(1));

	}

	@Test
	void testDFS() {
		Graph g1 = new Graph(8);
		g1.addUndirectedEdge(1, 2);
		g1.addUndirectedEdge(1, 3);
		g1.addUndirectedEdge(2, 4);
		g1.addUndirectedEdge(3, 4);
		g1.addUndirectedEdge(5, 6);
		g1.addUndirectedEdge(6, 7);
		g1.addUndirectedEdge(6, 8);
		g1.DFS();
		assertEquals(4, g1.getParent(3));
		assertEquals('B', g1.getColor(3));
		assertEquals(4, g1.getDiscoverTime(3));
		assertEquals(5, g1.getFinishTime(3));

		assertEquals(4, g1.getParent(3));
		assertEquals('B', g1.getColor(3));
		assertEquals(4, g1.getDiscoverTime(3));
		assertEquals(5, g1.getFinishTime(3));

		assertEquals(5, g1.getParent(6));
		assertEquals('B', g1.getColor(6));
		assertEquals(10, g1.getDiscoverTime(6));
		assertEquals(15, g1.getFinishTime(6));

	}

}