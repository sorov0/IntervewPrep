package javaStreamAPI;


import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Java8Demo {

    public static void main(String[] args) {

        // Stream
        // Java 8: Minimal code, functional programing (a function when assigned in a variable and is used further)
        // Java 8: Line 35 to 39
        // Java 8: Lambda expression, Streams API, Date and Time API

        // Lambda Expression is an anonymous function (no name, no return type and no access modifier)

        // eg. Below three declaration are same

        Thread thread1 = new Thread(new Task());

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Lambda expression 2");
            }
        });

        Thread thread3 = new Thread(() -> {
                System.out.println("Lambda expression 3");
        });

        thread1.start();
        thread2.start();
        thread3.start();

        MathOperation sumOperation = (a,b) -> (a+b);
        MathOperation subtractOperation = (a,b) -> (a-b);

        int res1 = sumOperation.operate(5,3);
        int res2 = subtractOperation.operate(5,3);

        // Predicate: Functional interface (Boolean valued function), check and condition and return only true
        // Method name is test()
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(4));
        System.out.println(isEven.test(5));

        Predicate<String> startsWithA = str -> str.toLowerCase().startsWith("a");
        Predicate<String> endsWithB = str -> str.toLowerCase().endsWith("t");

        Predicate<String> and = startsWithA.and(endsWithB);
        System.out.println(and.test("Ankit"));
        System.out.println(and.test("Vipul"));

        // Function: It works for your. again a functional interface but it takes an input return an output
        // Method name is apply
        Function<Integer, Integer> doubleIt = x -> x * x;
        System.out.println(doubleIt.apply(4));
        System.out.println(doubleIt.apply(5));

        Function<Integer, Integer> tripleIt = x -> x * x * x;
        System.out.println(doubleIt.andThen(tripleIt).apply(10));
        System.out.println(doubleIt.compose(tripleIt).apply(10));

        Function<Integer, Integer> identity = Function.identity();
        System.out.println(identity.apply(5)); // returns the same value

        // Consumer: It takes an input and then use it process it but returns nothing, again a functional interface
        // Method name is accept
        Consumer<String> print = x -> System.out.println(x);
        print.accept("Saruav");

        List<Integer> list = Arrays.asList(1,3,5);
        Consumer<List<Integer>> printList = x -> {
            for(int i : x){
                System.out.println(i);
            }
        };
        printList.accept(list);

        // Supplier: It takes as input nothing but returns a value, again a function Interface
        // Method name get
        Supplier<String> giveHellowWorld = () -> "Hello World";
        System.out.println(giveHellowWorld.get());

        // combined example
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Function<Integer, Integer> function = x -> x * x;
        Consumer<Integer> consumer = x -> System.out.println(x);
        Supplier<Integer> supplier = () -> 100;

        if (predicate.test(supplier.get())) {
            consumer.accept(function.apply(supplier.get()));
        }

        // BiPredicate, BiConsumer, BiFunction
        BiPredicate<Integer, Integer> isSumEven = (x, y) -> (x + y) % 2 == 0;
        System.out.println(isSumEven.test(5, 5));

        BiConsumer<Integer, String> biConsumer = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
        };

        BiFunction<String, String, Integer> biFunction = (x, y) -> (x + y).length();
        System.out.println(biFunction.apply("a", "bc"));

        // UnaryOperator, BinaryOperator
        UnaryOperator<Integer> a = x -> 2 * x;
        BinaryOperator<Integer> b = (x, y) -> x + y;

        // Method reference : we can use method without explicit invoking and is used in place of lambda expression
        List<String> students = Arrays.asList("Ram", "Shyam", "Ghanshyam");

        students.forEach(name -> System.out.println(name)); // This is lamba expression

        // Method reference is getting used in place of lamda expression and is being used without invocation
        // just by passing reference to it.
        students.forEach(System.out::println);              // This is method reference

        // Constructor reference
        List<String> names = Arrays.asList("Samsung", "Apple", "Nokia");
        List<Phone> phoneListLE = names.stream().map(name -> new Phone(name)).collect(Collectors.toList());
        List<Phone> phoneListCR = names.stream().map(Phone::new).collect(Collectors.toList());



    }


}

class Task implements Runnable{

    @Override
    public void run() {
        System.out.println("Lambda expression 1");
    }
}

interface MathOperation {

    int operate(int a, int b);
}

class Phone {

    public Phone(String name) {
        this.name = name;
    }

    String name;
}
