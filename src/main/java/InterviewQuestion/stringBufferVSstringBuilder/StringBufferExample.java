package InterviewQuestion.stringBufferVSstringBuilder;

/*

✔ Thread-safe: Synchronization ensures consistency in multi-threaded environments.
 ❌ Slower: Unnecessary synchronization overhead when used in single-threaded applications.

 */

public class StringBufferExample {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World");
        System.out.println(sb); // Output: Hello World
    }

}
