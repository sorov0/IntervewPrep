package javaStreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    // Stream API
    // Streams: A sequence of elements supporting functional and declarative programing
    // Feature introduced in java 8
    // process collections of data in functional and declarative manner
    // Simplify Data Processing
    // Embrace functional programing
    // Improve maintainability and readability
    // Enable easy parallelism without dealing with the complexities of multithreading

    // How to use stream
    // Source -> Intermediate Operation -> Terminal Operation

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(numbers.stream().filter(num -> num%2==0).count());

        // Creating Streams
        // 1. From collections
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();

        // 2. From Arrays
        String[] array = {"a", "b", "c"};
        Stream<String> stream1 = Arrays.stream(array);

        // 3. Using Stream.of()
        Stream<String> stream2 = Stream.of("a", "b");

        // 4. Infinite streams
        Stream<Integer> stream3 = Stream.generate(() -> 1);
        Stream<Integer> stream4 = Stream.generate(() -> 1).limit(100);
        Stream<Integer> stream5 = Stream.iterate(1, x -> x + 1);
        Stream<Integer> stream6 = Stream.iterate(1, x -> x + 1).limit(1000);

        List<Integer> integerList = stream6.collect(Collectors.toList());




    }
}
