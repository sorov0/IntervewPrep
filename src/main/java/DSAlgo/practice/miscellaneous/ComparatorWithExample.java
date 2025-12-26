package DSAlgo.practice.miscellaneous;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Person {
    String name;
    int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // toString method for printing
    @Override
    public String toString() {
        return this.name + ": " + this.age;
    }
}

public class ComparatorWithExample {


        public static void main(String[] args) {
            // List of persons
            List<Person> persons = new ArrayList<>();
            persons.add(new Person("John", 25));
            persons.add(new Person("Alice", 30));
            persons.add(new Person("Bob", 20));

            // Sort by age using Comparator
            persons.sort(new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return Integer.compare(o1.age, o2.age);
                }
            });

            // Print sorted list
            for (Person person : persons) {
                System.out.println(person);
            }

            // Alternatively: Sort by name using lambda expression (Java 8+)
            persons.sort((p1, p2) -> p1.name.compareTo(p2.name)); // ascending order by name
            System.out.println("Sorted by name:");
            persons.forEach(System.out::println);
        }

}
