package InterviewQuestion.stringLiteralVSstringObject;


/*

📌 Difference Between String Literal and String Object in Java
In Java, strings can be created in two ways:

Using String Literals
Using the new Keyword (String Object)

================================================================================
🔹 String Literal (Stored in String Pool)
When you create a string using double quotes, it is stored in the String Pool (inside the heap memory).
✔ Memory Efficient: Java reuses string literals from the String Pool instead of creating new objects.
✔ Faster: Since no new object is created if it already exists.
s1 and s2 refer to the same object in the String Pool.

========================================================================

🔹 String Object (Stored in Heap Memory)
When you create a string using new String(), it always creates a new object in the Heap Memory,
even if the same value exists in the String Pool.

✔ Avoids String Pool (Each new call creates a fresh object).
✔ Useful if modifications are expected, but inefficient in most cases
❌ s1 and s2 are different objects, even though their values are the same.

================================================================================

🔍 Comparing String Literal vs String Object
Feature	String Literal	String Object (new)
Memory Location	String Pool	Heap Memory
Memory Efficiency	✅ Reuses existing objects	❌ Creates a new object every time
Performance	✅ Faster	❌ Slower (More memory usage)
Object Creation	Only once if the string already exists	Always creates a new object
Usage	When immutability is needed	When modification is needed

 */

public class Main {

    // StringLiteralExample
    public static void stringLiteralExample() {
        String s1 = "Hello";
        String s2 = "Hello";

        System.out.println(s1 == s2); // true (Both refer to the same object)
    }


    // StringObjectExample
    public static void stringObjectExample() {
        String s1 = new String("Hello");
        String s2 = new String("Hello");

        System.out.println(s1 == s2); // false (Different objects)
    }
}
