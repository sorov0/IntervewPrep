package InterviewQuestion.stringBufferVSstringBuilder;


/*✔ Faster: No synchronization, making it more efficient for single-threaded applications.
        ❌ Not Thread-Safe: If used in multi-threaded applications without synchronization, it can cause issues.*/


public class StringBuilderExample {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");
        System.out.println(sb); // Output: Hello World
    }
}
