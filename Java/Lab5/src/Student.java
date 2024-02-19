/**
 * Student.java
 * @author jenniferparrish
 * CIS 22C, Lab 5
 */

public class Student implements Comparable<Student>{
    private int id;
    private String name;
    
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    @Override public int hashCode() {
        String key = ""; //this will store the "key" for this class
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i);
        }
        return sum + id;
    }
    
    @Override public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if (!(o instanceof Student)) {
            return false;
        } else {
            Student s = (Student) o;
            return this.id == s.id && s.name.equals(this.name);
        }
    }
    
    @Override public int compareTo(Student s) {
        if(this.equals(s)) {
            return 0;
        } else if (this.id != s.id) {
            return Integer.compare(this.id, s.id);
        } else {
            return this.name.compareTo(s.name);
        }
    }
    
    @Override public String toString() {
        return name + ": " + id;
    }
}