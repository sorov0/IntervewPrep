package Miscellaneous;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {



    static void secondHighest(List<Integer> salaries){


        Optional<Integer> secondHighest = salaries.stream()
                .distinct()  // Remove duplicate salaries
                .sorted(Comparator.reverseOrder())  // Sort in descending order
                .skip(1)  // Skip the first (highest) salary
                .findFirst();  // Get the second highest salary

        System.out.println(secondHighest.get());

    }

    /*
    Checked Exceptions – These are checked at compile-time. You must handle them using try-catch or declare them using throws.
    Example: IOException, SQLException.
    Unchecked Exceptions – These occur at runtime and are usually due to programming errors.
    Example: NullPointerException, ArrayIndexOutOfBoundsException.
     */
    static void checkedAndUnchecked() {
        // Checked Exception
        try {
            FileReader file = new FileReader("test.txt"); // Requires handling
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        // Unchecked Exception
        String str = null;
        System.out.println(str.length()); // Throws NullPointerException
    }



    static void throwVsThrows(int age) throws IllegalArgumentException {
        if (age < 18) {
            throw new IllegalArgumentException("Not eligible to vote");
        }
    }

    // The finally block is used to execute cleanup code (like closing resources), whether an exception occurs or not.
    public static void finallyExample(String[] args) {
        try {
            int result = 10 / 0; // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e);
        } finally {
            System.out.println("Finally block executed");
        }
    }

    /*
    5. Can a finally block have a return statement?
    Answer: Yes, but it is not recommended as it can override exceptions.
    The return value inside finally will replace the original return.
    Avoid returning from finally, as it may lead to unpredictable behavior.
     */
    public static int finallyReturn() {
        try {
            return 10;
        } finally {
            return 20; // This overrides the original return
        }
    }

    /*
    What is Multi-Catch Block?
    Answer: A single catch block can handle multiple exceptions using the | (OR) operator.
     */
    public static void multiCatchBlock(String[] args) {
        try {
            int arr[] = new int[5];
            arr[10] = 30 / 0; // May cause ArrayIndexOutOfBounds or ArithmeticException
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    /*
    7. Can we have multiple catch blocks?
Answer: Yes! Each catch block handles a different exception type.
     */
    public static void multipleCatchBlock(String[] args) {
        try {
            int num = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception caught");
        } catch (Exception e) {
            System.out.println("General Exception caught");
        }
    }

    /*
    8. What is a Custom Exception?
Answer: A custom exception is a user-defined exception class extending Exception or RuntimeException.

class AgeException extends Exception {
    public AgeException(String message) {
        super(message);
    }
}

public class CustomExceptionExample {
    static void checkAge(int age) throws AgeException {
        if (age < 18) {
            throw new AgeException("Age must be 18 or above");
        }
    }

    public static void main(String[] args) {
        try {
            checkAge(16);
        } catch (AgeException e) {
            System.out.println("Custom Exception: " + e.getMessage());
        }
    }
}

     */

    /*

Keyword	Meaning
final	Used for constants, preventing method overriding & inheritance.
finally	A block that always executes in exception handling.
finalize	A method used for garbage collection before an object is destroyed.
     */

    static void reverse(ArrayList<Integer> arr, int start, int end){

        while(start<=end){
            Collections.swap(arr, start, end);
            start++;
            end--;
        }

    }

    // RotateArrayByKElement
    // https://leetcode.com/problems/rotate-array/description/

    static void leftRotateByKEle(ArrayList<Integer> arr, int k){

        k = k % arr.size();
        reverse(arr, 0, k-1);
        reverse(arr, k, arr.size()-1);
        Collections.reverse(arr);

    }

    public static Character getFirstNonRepeatingChar(String input){
        //remove all the spaces
        input = input.replaceAll(" ", "");
        Character nonRptChar = null;
        //Will store each character and it's count
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i <input.length(); i++) {
            Character chr = input.charAt(i);
            int count = map.getOrDefault(chr, 0);
            map.put(chr, count+1);
        }
        //Iterate the string and return the character for which the count is 1 in map
        for (int i = 0; i <input.length() ; i++) {
            if(map.get(input.charAt(i))==1){
                nonRptChar = input.charAt(i);
                break;
            }
        }
        return nonRptChar;
    }




    public static void main(String[] args) {

        List<Integer> salaries = Arrays.asList(5000, 7000, 4000, 9000, 9000, 8000);
        secondHighest(salaries);

    }

}
