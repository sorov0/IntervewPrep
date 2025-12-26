package collections.listInterface;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // toString method for printing
    @Override
    public String toString() {
        return this.name + ": " + this.age;
    }
}

