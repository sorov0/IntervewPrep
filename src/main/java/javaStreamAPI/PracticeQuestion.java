package javaStreamAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PracticeQuestion {

    // Sum of all even numbers from 1 to N using IntStream
    static int sumOfEven(int n){
        int sum = IntStream.rangeClosed(1, n).filter(i -> i % 2 == 0).sum();
        return sum;
    }

    // Count numbers divisible by 3 between 1 and 100
    static long countDivisible(int n){
        long cnt = IntStream.rangeClosed(1, n).filter(i -> i%3 == 0).count();
        return cnt;
    }

    // Generate a list of first 10 odd numbers
    static List<Integer> generateOddNumber(int n){
        List<Integer> res = IntStream.rangeClosed(1, n).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());
        IntStream.rangeClosed(1, n).filter(i -> i%2 != 0).forEach(System.out::println);

        return res;
    }

    // Find maximum and minimum from a list using IntStream
    static void maxMin(List<Integer> list){

        int max = list.stream().mapToInt(i -> i).max().orElse(Integer.MIN_VALUE);
        int min = list.stream().mapToInt(i -> i).min().orElse(Integer.MAX_VALUE);

        System.out.println("Max = " + max + ", Min = " + min);

    }

    // Print squares of first 10 natural numbers
    static void printSquare(int n){
        IntStream.rangeClosed(1, n).map(i -> i*i).forEach(System.out::println);
    }

    // Check if all numbers in list are even
    static boolean checkIfAllEven(List<Integer> list){

        boolean b = list.stream().mapToInt(i -> i).allMatch(i -> i % 2 == 0);
        return b;
    }

    // Count how many strings start with a vowel
    static void solve1(){
        List<String> list = Arrays.asList("apple", "banana", "orange", "grape", "avocado");

        long count = list.stream()
                .filter(s -> !s.isEmpty() && "aeiouAEIOU".indexOf(s.charAt(0)) != -1)
                .count();

        System.out.println("Starts with vowel: " + count);

    }

    // Find the longest string from a list
    static void solve2(){
        List<String> list = Arrays.asList("cat", "elephant", "tiger", "hippopotamus");

        String longest = list.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("None");

        System.out.println("Longest word: " + longest);

    }

    // Find the longest string from a list
    static String longestString(List<String> str){

        String name = str.stream().reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2).orElse(null);
        return name;
    }

    // Find duplicate strings in a list
    static void solve3(){
        List<String> list = Arrays.asList("java", "python", "java", "c++", "python", "go");

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = list.stream()
                .filter(s -> !seen.add(s))
                .collect(Collectors.toSet());

        System.out.println("Duplicates: " + duplicates);

    }

    // Group strings by their length
    static void solve4(){
        List<String> list = Arrays.asList("a", "bb", "ccc", "dd", "ee", "ffff");

        Map<Integer, List<String>> grouped = list.stream()
                .collect(Collectors.groupingBy(str -> str.length()));

/*        Map<Integer, List<String>> grouped = list.stream()
                .collect(Collectors.groupingBy(str -> str.length()));*/

        System.out.println(grouped);

    }

    //  Sort strings by length, then alphabetically
    static void solve5(){
        List<String> list = Arrays.asList("banana", "apple", "kiwi", "cherry", "fig");

        List<String> sorted = list.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());

        System.out.println(sorted);

    }

    //  Convert list of strings to uppercase
    static void solve6(){
        List<String> list = Arrays.asList("java", "spring", "kafka");

        List<String> upper = list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(upper);

    }

    // Count frequency of each string (word count style)
    static void solve7(){
        List<String> list = Arrays.asList("apple", "banana", "apple", "kiwi", "banana", "apple");

        Map<String, Long> frequency = list.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        System.out.println(frequency);

    }

    // Join all strings with comma
    static void solve8(){
        List<String> list = Arrays.asList("Java", "Spring", "Kafka");

        String result = list.stream()
                .collect(Collectors.joining(", "));

        System.out.println(result); // Output: Java, Spring, Kafka

    }

    // Reverse each string in the list
    static void solve9(){
        List<String> list = Arrays.asList("java", "spring", "boot");

        List<String> reversed = list.stream()
                .map(s -> new StringBuilder(s).reverse().toString())
                .collect(Collectors.toList());

        System.out.println(reversed);

    }



}
