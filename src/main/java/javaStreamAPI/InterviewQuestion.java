package javaStreamAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Employee {

    String name;
    int salary;
    String dept;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getDept() {
        return dept;
    }
}


public class InterviewQuestion {

    public static void main(String[] args) {

        // Filter Even Numbers and Square Them
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> res = numbers.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .collect(Collectors.toList());

        System.out.println(res);

        // Count Strings Starting with "A" (Case-Insensitive)
        List<String> names = Arrays.asList("Apple", "ant", "Ball", "air");
        long res1 = names.stream()
                .filter(x -> x.toUpperCase().startsWith("A"))
                .count();

        System.out.println(res1);


        // Find Minimum and Maximum Element
        List<Integer> nums = Arrays.asList(10, 5, 20, 8);
        int mini1 = nums.stream().min(Comparator.naturalOrder()).get();
        int mini2 = nums.stream().min(Integer::compareTo).get();
        int mini3 = nums.stream().min((a, b) -> (a - b)).get();
        int mini4 = nums.stream().min((Comparator.comparingInt(Integer::intValue))).get();
        int mini5 = nums.stream()
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalStateException("List is empty"));

        int maxi1 = nums.stream().max(Comparator.naturalOrder()).get();
        int maxi2 = nums.stream().max(Integer::compareTo).get();
        int maxi3 = nums.stream().max((a, b) -> (a - b)).get();
        int maxi4 = nums.stream().max((Comparator.comparingInt(Integer::intValue))).get();
        int maxi5 = nums.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalStateException("List is empty"));


        // Remove Duplicates and Sort Descending
        nums = Arrays.asList(5, 3, 5, 2, 8, 3);
        List<Integer> collect = nums.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(collect);

        // Flatten a List of Lists
        List<List<Integer>> list = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5)
        );

        List<Integer> collect1 = list.stream()
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        List<Integer> collect2 = list.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(collect1);


        // Find First Number Greater Than 50
        nums = Arrays.asList(10, 20, 60, 40, 80);
        Integer integer = nums.stream()
                .filter(x -> x > 50)
                .findFirst().get();

        System.out.println(integer);

        // Group Strings by Length
        List<String> words = Arrays.asList("Java", "Go", "Python", "AI");

        Map<Integer, List<String>> collect4 = words.stream()
                .collect(Collectors.groupingBy(x -> x.length()));

        Map<Integer, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(collect4);

        // Group Strings by Word
        words = Arrays.asList("Java", "Go", "Python", "AI", "Java");
        Map<String, List<String>> collect3 = words.stream()
                .collect(Collectors.groupingBy(x -> x));

        System.out.println(collect3);


        // Find Second Highest Number
        nums = Arrays.asList(10, 20, 30, 40, 30);
        Optional<Integer> secondHighest = nums.stream()
                .distinct()
                .sorted(Comparator.reverseOrder()).skip(1)
                .findFirst();

        secondHighest.ifPresent(System.out::println);


        // Check if All Employees Earn Above 50,000
        List<Employee> employees = Arrays.asList(
                new Employee("A", 60000),
                new Employee("B", 70000),
                new Employee("C", 55000)
        );

        boolean ans = employees.stream().allMatch(x -> x.salary > 50000);
        System.out.println(ans);


        // Employee Analytics (Most Important)
        /*
        Problem Recap
        Filter IT department
        Sort by salary descending
        Pick top 3
        Return names
         */

        List<String> collect5 = employees.stream()
                .filter(x -> x.dept == "IT")
                .sorted((val1, val2) -> (val1.salary - val2.salary))
                .limit(3)
                .map(x -> x.name)
                .collect(Collectors.toList());

        List<String> collect6 = employees.stream()
                .filter(x -> x.dept == "IT")
                .sorted(Comparator.comparingInt((Employee e) -> e.salary).reversed())
                .limit(3)
                .map(x -> x.name)
                .collect(Collectors.toList());

        System.out.println(collect5);


        // Find Frequency of Each Character in a String
        String input = "programming";
        Map<Character, Long> collect7 = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        System.out.println(collect7);


        // Find First Non-Repeated Character
        input = "swiss";
        Optional<Character> result = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey).findFirst();

        System.out.println(result.orElse(null)); // w


        // Find Department-wise Highest Paid Employee
        Map<String, Employee> highestPaidByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.dept,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(
                                        Comparator.comparingInt(e -> e.salary)
                                ),
                                Optional::get
                        )
                ));


        // Sort Map by Values (Descending)
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("A", 10);
            put("B", 30);
            put("C", 20);
        }};

        Map<String, Integer> sortedMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println(sortedMap);

        // Partition Employees Based on Salary Threshold
        Map<Boolean, List<Employee>> partitioned =
                employees.stream()
                        .collect(Collectors.partitioningBy(
                                e -> e.salary > 70000
                        ));

        System.out.println(partitioned);


        // Find Top 3 Most Frequent Elements in a List
        nums = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        List<Integer> top3 = nums.stream()
                .collect(Collectors.groupingBy(
                        n -> n,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(top3); // [4, 3, 2]



        // Find Longest String in Each Group
        Map<Integer, Optional<String>> longestByLength =
                words.stream()
                        .collect(Collectors.groupingBy(
                                String::length,
                                Collectors.maxBy(
                                        Comparator.comparingInt(String::length)
                                )
                        ));




        // Check If a List Is a Palindrome
        List<Integer> listt = Arrays.asList(1, 2, 3, 2, 1);
        boolean isPalindrome = IntStream.range(0, list.size() / 2)
                .allMatch(i ->
                        list.get(i).equals(list.get(list.size() - 1 - i))
                );

        System.out.println(isPalindrome); // true



        // Merge Two Lists and Remove Duplicates
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);

        List<Integer> merged = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(merged); // [1, 2, 3, 4, 5]



        // Find Employees Whose Salary Is Greater Than Department Average
        Map<String, Double> avgSalaryByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                e -> e.dept,
                                Collectors.averagingInt(e -> e.salary)
                        ));

        List<Employee> resultt =
                employees.stream()
                        .filter(e -> e.salary > avgSalaryByDept.get(e.dept))
                        .collect(Collectors.toList());

        System.out.println(resultt);


        // Sum of digits of a number
        int num = 563;

        int sum = String.valueOf(num)
                .chars()
                .map(c -> c - '0')
                .sum();

        System.out.println(sum); // 14


        // Count vowels in a string
        String str = "hello world";

        long vowels = str.toLowerCase().chars()
                .filter(c -> "aeiou".indexOf(c) != -1)
                .count();

        System.out.println(vowels); // 3


        // Convert string to list of characters
        String s = "JAVA";

        List<Character> listc = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        System.out.println(listc); // [J, A, V, A]


        // Generate first 10 natural numbers
        List<Integer> ll = IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(ll);


        // ASCII sum of characters in a string
        String st = "ABC";

        int asciiSum = st.chars().sum();

        System.out.println(asciiSum); // 198


        // Max occurring character in string
        s = "programming";

        char maxChar = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        System.out.println(maxChar); // 'g'


        // Remove duplicate characters from string
        s = "banana";

        String result1 = s.chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        System.out.println(result1); // ban


        // Check if two strings are anagrams
        String a = "listen";
        String b = "silent";

        boolean isAnagram = a.length() == b.length() &&
                a.chars().sorted().toArray().length == b.chars().sorted().toArray().length &&
                Arrays.equals(a.chars().sorted().toArray(), b.chars().sorted().toArray());

        System.out.println(isAnagram); // true


        // Reverse a string (Stream + reduce)
        s = "HELLO";

        String reversed = s.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("", (a1, a2) -> a2 + a1);

        System.out.println(reversed); // OLLEH


        // Frequency of digits from 0–9 in a number
        num = 998120;

        Map<Integer, Long> freq =
                String.valueOf(num).chars()
                        .map(c -> c - '0')
                        .boxed()
                        .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        System.out.println(freq);


        // First repeated character in string
        s = "swiss";

        Set<Character> seen = new HashSet<>();

        Optional<Character> firstRepeated = s.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !seen.add(c))  // add returns false when already exists
                .findFirst();

        System.out.println(firstRepeated.orElse(null)); // 's'



        // Alternating case using IntStream
        //Input: "hello world"
        //Output: "HeLlO WoRlD"
        String chrs = "hello world";

        String resulttt = IntStream.range(0, chrs.length())
                .mapToObj(i -> i % 2 == 0
                        ? Character.toUpperCase(chrs.charAt(i))
                        : Character.toLowerCase(chrs.charAt(i)))
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(resulttt);


        // Sum of squares in comma-separated string
        s = "2,3,4";

        sum = Arrays.stream(s.split(","))
                .mapToInt(Integer::parseInt)
                .map(n -> n * n)
                .sum();

        System.out.println(sum); // 29


        // Run-Length Encoding (RLE)
        // Input:  "aaabbc"
        //Output: "a3b2c1"
        String word = "aaabbc";

/*        String encoded = IntStream.range(0, word.length())
                .filter(i -> i == 0 || word.charAt(i) != word.charAt(i - 1)) // new sequence start
                .mapToObj(i -> {
                    char ch = word.charAt(i);
                    long count = IntStream.range(i, word.length())
                            .takeWhile(j -> word.charAt(j) == ch)
                            .count();
                    return ch + String.valueOf(count);
                })
                .collect(Collectors.joining());

        System.out.println(encoded); // a3b2c1*/

        // 1️⃣ Count frequency of characters in a string
        s = "banana";

        Map<Character, Long> freqq = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        System.out.println(freqq); // {a=3, b=1, n=2}


        // 2️⃣ Count frequency of words in a sentence
        String sentence = "apple banana apple mango banana apple";

        Map<String, Long> freqqq = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        System.out.println(freqqq);


        // Count uppercase vs lowercase characters
        s = "HelloWorld123";

        Map<String, Long> result4 = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> Character.isUpperCase(c) ? "UPPER" : "LOWER",
                        Collectors.counting()
                ));

        System.out.println(result4);


        // Map each word to its length
        String sentenceq = "Java is powerful";

        Map<String, Integer> mapp = Arrays.stream(sentenceq.split(" "))
                .collect(Collectors.toMap(
                        w -> w,
                        w -> w.length()
                ));

        System.out.println(mapp);


        // Group strings by starting character
        List<String> lll = Arrays.asList("apple", "amazon", "ball", "cat", "car");

        Map<Character, List<String>> grouped1 = lll.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));

        System.out.println(grouped1);


        // Create map of word → frequency sorted by count DESC
        String sent = "apple banana apple cherry banana banana";

        Map<String, Long> mp =
                Arrays.stream(sent.split(" "))
                        .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        LinkedHashMap<String, Long> sorted = mp.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (p, q) -> p,
                        LinkedHashMap::new
                ));

        System.out.println(sorted);


        // Build frequency map of vowels only
        s = "communication";

        Map<Character, Long> mpc = s.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .filter(c -> "aeiou".contains(String.valueOf(c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        System.out.println(mpc);


        Map<String, Integer> mpp = new HashMap<>();
        map.put("A", 10);
        map.put("B", 30);
        map.put("C", 20);
        map.put("D", 30);

        // Print all entries as key=value
        map.entrySet()
                .forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));


        // Get all keys where value > 15
        List<String> keys = map.entrySet().stream()
                .filter(e -> e.getValue() > 15)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(keys); // [B, C, D]


        // Count entries with value == 30
        long count = map.entrySet().stream()
                .filter(e -> e.getValue() == 30)
                .count();

        System.out.println(count); // 2


        // Convert Map → List of keys
        List<String> keyss = map.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(keyss);


        // Check if any entry has negative value
        boolean hasNegative = map.entrySet().stream()
                .anyMatch(e -> e.getValue() < 0);

        System.out.println(hasNegative); // false


        // Sort map by values (ascending)
        Map<String, Integer> sortedd =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (p, q) -> p,
                                LinkedHashMap::new
                        ));

        System.out.println(sortedd);


        // Sort map by keys (descending)
        Map<String, Integer> sorteddd =
                map.entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (p, q) -> p,
                                LinkedHashMap::new
                        ));

        System.out.println(sorteddd);


        // Find entry with maximum value
        Map.Entry<String, Integer> maxEntry =
                map.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .get();

        System.out.println(maxEntry); // B=30 (or D=30)


        // Transform values (multiply each value by 2)
        Map<String, Integer> updated =
                map.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue() * 2
                        ));

        System.out.println(updated);


        // Find top 3 keys with highest values
        List<String> top3Keys =
                map.entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .limit(3)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

        System.out.println(top3Keys);


    }

}
