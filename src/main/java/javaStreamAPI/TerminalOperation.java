package javaStreamAPI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TerminalOperation {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3);

        // 1. collect
        list.stream().skip(1).collect(Collectors.toList());
        //list.stream().skip(1).toList(); // this is implemented after Java 8

        // 2. forEach
        list.stream().forEach(System.out::println);

        // 3. reduce : Combine elements to produce a single result
        Optional<Integer> sumLE = list.stream().reduce((x, y) -> x + y); // Lambda Expression
        Optional<Integer> sumMR = list.stream().reduce(Integer::sum); // Reference method
        System.out.println(sumLE.get());

        // 4. count
        System.out.println(list.stream().count());

        // 5. anyMatch, allMatch, noneMatch (short circuit operation)
        boolean res = list.stream().anyMatch(x -> x % 2 == 0);

        boolean res1 = list.stream().allMatch(x -> x>0);

        boolean res2 = list.stream().noneMatch(x -> x<0);

        // 6. findFirst, findAny (short circuit operation)
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());

        // Example: Filtering and Collecting Names
        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");
        System.out.println(names.stream().filter(x -> x.length() > 3).collect(Collectors.toList()));

        // Example: Squaring and Sorting Numbers
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 6);
        System.out.println(numbers.stream().map(x -> x*x).sorted().collect(Collectors.toList()));

        // Example: Summing Values
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(integers.stream().reduce(Integer::sum).get());

        // Example:  Counting Occurrences of a Character
        String sentence = "Hello world"; // count occurrence of l
        IntStream charStream = sentence.chars();
        long count = charStream.filter(x -> x == 'l').count();
        System.out.println(sentence.chars().filter(x -> x=='l').count());

        // 7. toArray()
        Object[] array = Stream.of(1, 2, 3).toArray();

        // 8. min / max
        System.out.println("max: " + Stream.of(2, 44, 69).max((o1, o2) -> o2 - o1));
        System.out.println("min: " + Stream.of(2, 44, 69).min(Comparator.naturalOrder()));

        // Example
        // Streams cannot be reused after a terminal operation has been called : VVI
        Stream<String> stream = names.stream();
        stream.forEach(System.out::println); // Terminal operation has already been used here in stream, can't be reused again
        // List<String> list1 = stream.map(String::toUpperCase).toList(); // hence exception here


        // 9. forEachOrdered : VVI
        List<Integer> numbers0 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Using forEach with parallel stream:");
        numbers0.parallelStream().forEach(System.out::println);
        System.out.println("Using forEachOrdered with parallel stream:");
        numbers0.parallelStream().forEachOrdered(System.out::println);

        // stateful and stateless operation




    }
}
