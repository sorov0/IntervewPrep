package company.xebia;

import java.util.*;

public class HashMapTest {

    static class Student implements Comparable {

        String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public int compareTo(Object o) {
            Student student = (Student) o;
            return name.compareTo(student.name);
        }
    }

    public static void main(String[] args) {
        Map<Student, Integer> mp = new TreeMap<>();

        mp.put(new Student("Saurav"), 1);
        mp.put(new Student("Abhishek"), 2);

        System.out.println(mp);




    }
}
