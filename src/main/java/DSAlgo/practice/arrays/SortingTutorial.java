package DSAlgo.practice.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Person {
    String name;
    int age;

    String getName(){
        return this.name;
    }

    int getAge(){
        return this.age;
    }

    void setName(String name){
        this.name = name;
    }

    void setAge(int age){
        this.age = age;
    }

    public Person(String name , int age){
        this.name = name;
        this.age = age;
    }
}

public class SortingTutorial {

    static ArrayList sort1(ArrayList<Person> personList){

        Comparator<Person> comparator = Comparator.comparing(person -> person.name);
        comparator = comparator.thenComparing(Comparator.comparing(person -> person.age));
        Collections.sort(personList , comparator);
        return personList;

    }

    static ArrayList sort2(ArrayList<Person> personList){

        personList.sort(Comparator.comparing(Person::getName).thenComparing(Person::getAge));
        return personList;

    }

    static ArrayList sort3(ArrayList<Person> personList){

        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                String name1 = o1.getName();
                String name2 = o2.getName();
                int nameCompare = name1.compareTo(name2);
                if(nameCompare != 0){
                    return nameCompare;
                }
                Integer age1 = o1.getAge();
                Integer age2 = o2.getAge();
                int ageCompare = age1.compareTo(age2);
                return ageCompare;
            }
        });
        return personList;

    }

    public static void main(String[] args) {

        Person person1 = new Person("Saurav" , 1);
        Person person2 = new Person("Saurav" , 8);
        Person person3 = new Person("Abhishek" , 2);
        Person person4 = new Person("Saurav" , 6);
        Person person5 = new Person("Abhishek" , 3);
        Person person6 = new Person("Saurav" , 1);
        Person person7 = new Person("Saurav" , 4);

        ArrayList<Person> personList = new ArrayList<Person>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);

        sort2(personList);
        for(Person person : personList){
            System.out.println(person.getName() + " " + person.getAge() );
        }
    }
}
