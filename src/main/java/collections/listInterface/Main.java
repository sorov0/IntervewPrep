package collections.listInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// ArrayList and LinkedList are not threadsafe meaning operations are not synchronised
// Vector and Stack are Threadsafe meaning operations are synchronised
// CopyOnWriteArrayList is threadSafe

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", 25));
        persons.add(new Person("Alice", 30));
        persons.add(new Person("Bob", 20));

        persons.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.age, o2.age);
            }
        });

        persons.sort((p1, p2) -> p1.name.compareTo(p2.name));
        persons.sort(new MyComparator());

        Comparator<Person> comparator = Comparator.comparing(Person::getAge).thenComparing(Person::getName);

        persons.sort(comparator);

        Comparator<Person> comparatorReverse = Comparator.comparing(Person::getAge).reversed().thenComparing(Person::getName);
    }
}
