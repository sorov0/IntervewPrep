package collections.listInterface;

// List interafce is implemented by ArrayList, LinkedList, Vector, Stack
// Vector and Stack are synchronised

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {

        int[] ar = new int[10];

        List<Integer> li = new ArrayList<>(10000);
        //Initial capacity is 10

        li.add(1);
        li.add(3);
        li.add(80);

        li.remove(2); // it removes element at prvided index
        li.remove(Integer.valueOf(80)); // it removes the provided element

        li.add(2,88); //append
        li.set(2,99); //replace

        List<String> stringList = Arrays.asList("sasa","sdsds","sdsds"); //We can update the element but cant add
        stringList.add("asda"); // It would give error
        String[] strArr = {"asa","asda","asdas"};
        List<String> newList = Arrays.asList(strArr);

        System.out.println(stringList);
        List<String> newStringList = new ArrayList<>(newList);

        //List<Integer> list = List.of(1,2,3,4); //from java 11 It created immutable object


        Object[] objects = newStringList.toArray();
        Integer[] ints = newStringList.toArray(new Integer[0]);

        Collections.sort(li);
        Collections.reverse(li);

        li.sort(null); //It will also sort, Well it takes a comparator object to perform sorting


    }
}
