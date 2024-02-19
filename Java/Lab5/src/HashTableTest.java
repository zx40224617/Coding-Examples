/**
 * HashTableTest.java
 * @author Jennifer Parrish
 * CIS 22C, Lab 5
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HashTableTest {
    private final int SIZE = 10;

    @Test
    void testHashTableInt() {
        HashTable<Student> ht = new HashTable<>(SIZE);
        assertEquals(0, ht.getNumElements());
        ht.add(new Student("Gus", 44));
        assertEquals(1, ht.getNumElements());
        assertEquals(.1, ht.getLoadFactor());
        assertThrows(IllegalArgumentException.class, ()-> {new HashTable<String>(-1);});
    }

    @Test
    void testHashTableTArrayInt() {
        Student[] array = null;
        HashTable<Student> ht = new HashTable<>(array, SIZE);
        assertEquals(0, ht.getNumElements());
        array = new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66)};
        ht = new HashTable<>(array, 10);
        assertEquals(3, ht.getNumElements());
        assertEquals(0.3, ht.getLoadFactor());
        assertEquals(4, ht.find(new Student("Gus", 44)));
        assertEquals("Gus: 44 \n", ht.bucketToString(4));
        String[] array2 = {"a", "b", "c"};
        assertThrows(IllegalArgumentException.class, ()-> {new HashTable<String>(array2, -1);});
    }

    @Test
    void testCountBucket() {
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertEquals(0, ht.countBucket(0));
        assertEquals(1, ht.countBucket(4));
        assertEquals(2, ht.countBucket(5));
        
        assertThrows(IndexOutOfBoundsException.class, ()->{ht.countBucket(-1);});
        assertThrows(IndexOutOfBoundsException.class, ()->{ht.countBucket(10);});
    }

    @Test
    void testGetNumElements() {
        HashTable<Student> empty = new HashTable<>(SIZE);
        assertEquals(0, empty.getNumElements());
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertEquals(4, ht.getNumElements());
    }

    @Test
    void testFind() {
        HashTable<Student> empty = new HashTable<>(SIZE);
        assertEquals(-1, empty.find(new Student("Gus", 44)));
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertEquals(4, ht.find(new Student("Gus", 44)));
        assertEquals(-1, ht.find(new Student("Maria", 77)));
        Student noStu = null;
        assertThrows(NullPointerException.class, ()->{ht.find(noStu);});
    }

    @Test
    void testContains() {
        HashTable<Student> empty = new HashTable<>(SIZE);
        assertFalse(empty.contains(new Student("Gus", 44)));
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertTrue(ht.contains(new Student("Gus", 44)));
        assertFalse(ht.contains(new Student("Maria", 77)));
        Student noStu = null;
        assertThrows(NullPointerException.class, ()->{ht.contains(noStu);});
    }

    @Test
    void testAdd() {
        HashTable<Student> empty = new HashTable<>(SIZE);
        empty.add(new Student("Gus", 44));
        assertEquals(1, empty.getNumElements());
        assertTrue(empty.contains(new Student("Gus", 44)));
        empty.add(new Student("Tay", 45));
        assertEquals(2, empty.getNumElements());
        assertEquals("Tay: 45 \n", empty.bucketToString(5));
        Student noStu = null;
        assertThrows(NullPointerException.class, ()->{empty.add(noStu);});
    }

    @Test
    void testDelete() {
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertTrue(ht.delete(new Student("Gus", 44)));
        assertFalse(ht.contains(new Student("Gus", 44)));
        assertEquals(0, ht.countBucket(4));
        assertTrue(ht.delete(new Student("Tay", 45)));
        assertFalse(ht.contains(new Student("Tay", 45)));
        assertEquals(1, ht.countBucket(5));
        assertFalse(ht.delete(new Student("Tay", 45)));
        
        HashTable<Student> empty = new HashTable<>(SIZE);
        assertFalse(empty.contains(new Student("Tay", 45)));
        
        Student noStu = null;
        assertThrows(NullPointerException.class, ()->{ht.delete(noStu);});
    }

    @Test
    void testClear() {
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        ht.clear();
        for(int i = 0; i < SIZE; i++) {
            assertEquals(0, ht.countBucket(i));
        }
        assertEquals(0, ht.getNumElements());
        assertFalse(ht.contains(new Student("Gus", 44)));
    }

    @Test
    void testGetLoadFactor() {
        HashTable<Student> empty = new HashTable<>(SIZE);
        assertEquals(0.0, empty.getLoadFactor());
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertEquals(.4, ht.getLoadFactor());
        ht.delete(new Student("Gus", 44));
        assertEquals(.3, ht.getLoadFactor());
    }

    @Test
    void testBucketToString() {
        HashTable<Student> empty = new HashTable<>(SIZE);
        assertEquals("\n", empty.bucketToString(0));
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertEquals("Tanya: 55 Tay: 45 \n", ht.bucketToString(5));
        assertEquals("Gus: 44 \n", ht.bucketToString(4));
        assertEquals("\n", ht.bucketToString(0));
        
        assertThrows(IndexOutOfBoundsException.class, ()->{ht.bucketToString(-1);});
        assertThrows(IndexOutOfBoundsException.class, ()->{ht.bucketToString(10);});
    }

    @Test
    void testRowToString() {
        HashTable<Student> empty = new HashTable<>(SIZE);
        assertEquals("Bucket 0: empty\n" + 
                "Bucket 1: empty\n" + 
                "Bucket 2: empty\n" + 
                "Bucket 3: empty\n" + 
                "Bucket 4: empty\n" + 
                "Bucket 5: empty\n" + 
                "Bucket 6: empty\n" + 
                "Bucket 7: empty\n" + 
                "Bucket 8: empty\n" + 
                "Bucket 9: empty\n", 
                empty.rowToString());
        
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertEquals("Bucket 0: empty\n" + 
                "Bucket 1: empty\n" + 
                "Bucket 2: empty\n" + 
                "Bucket 3: empty\n" + 
                "Bucket 4: Gus: 44\n" + 
                "Bucket 5: Tanya: 55\n" + 
                "Bucket 6: Andrea: 66\n" + 
                "Bucket 7: empty\n" + 
                "Bucket 8: empty\n" + 
                "Bucket 9: empty\n", 
                ht.rowToString());
    }

    @Test
    void testToString() {
        HashTable<Student> empty = new HashTable<>(SIZE);
        System.out.print(empty);
        assertEquals("\n", empty.toString());
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44), 
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        assertEquals("Gus: 44 \n" + 
                "Tanya: 55 Tay: 45 \n" + 
                "Andrea: 66 \n\n", ht.toString());
    }

}