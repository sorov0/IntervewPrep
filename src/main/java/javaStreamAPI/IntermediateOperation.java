package javaStreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOperation {

    public static void main(String[] args) {

        // Intermediate operations transform a stream into another stream
        // They are lazy, meaning they don't execute until a terminal operation is invoked.

        // 1. filter (takes predicate)
        List<String> list = Arrays.asList("Akshit", "Ram", "Shyam", "Ghanshyam", "Akshit");

        // No filtering at this point so Intermediate operation
        Stream<String> filteredStream = list.stream().filter(name -> name.startsWith("A"));

        // Actual filtering happen here and this operation is called terminal operation
        List<String> filteredRes = filteredStream.collect(Collectors.toList());
        long count = filteredStream.count();

        // OR
        List<String> newRes = list.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());

        // 2. map (takes function)
        Stream<String> stringStreamLE = list.stream().map(x -> x.toUpperCase());
        Stream<String> stringStreamMR = list.stream().map(String::toUpperCase);
        List<String> collect = stringStreamLE.collect(Collectors.toList());

        // 3. sorted
        Stream<String> sortedStreamNO = list.stream().sorted(); // Natural Ordering
        Stream<String> sortedStreamCO = list.stream().sorted((a,b) -> a.length() - b.length()); // Custom Orderding with comparato

        // 4. distinct
        Stream<String> distinctStream = list.stream().distinct();
        long distinctName = distinctStream.count();

        System.out.println(list.stream().filter(name -> name.startsWith("A")).distinct().count());

        // 5. limit
        System.out.println(Stream.iterate(1, x -> x + 1).limit(100).count()); // 100

        // 6. skip
        System.out.println(Stream.iterate(1, x -> x + 1).skip(10).limit(100).count()); // 110

        // 7. peek
        // Performs an action on each element as it is consumed.
        Stream.iterate(1, x -> x + 1).skip(10).limit(100).peek(System.out::println).count();


        // 8. flatMap
        // Handle streams of collections, lists, or arrays where each element is itself a collection
        // Flatten nested structures (e.g., lists within lists) so that they can be processed as a single sequence of elements
        // Transform and flatten elements at the same time.
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("pear", "grape")
        );

        System.out.println(listOfLists.get(1).get(1));
        Stream<String> stringStream = listOfLists.stream().flatMap(x -> x.stream());
        System.out.println(stringStream.map(String::toUpperCase).collect(Collectors.toList()));

        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are powerful",
                "flatMap is useful"
        );
        Stream<String> stringStream1 = sentences.stream().flatMap(s -> Arrays.stream(s.split(" ")));
        System.out.println(stringStream1.map(String::toUpperCase).collect(Collectors.toList()));


    }
}
