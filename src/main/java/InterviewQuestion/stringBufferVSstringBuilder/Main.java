package InterviewQuestion.stringBufferVSstringBuilder;


/*

can you tell me the difference between string buffer and string builder?

Yes! StringBuffer and StringBuilder are both used for mutable (modifiable) strings in Java, but they have key differences.

📌 Difference Between StringBuffer and StringBuilder

Feature	                  StringBuffer	                                StringBuilder
Thread Safety	       ✅ Thread-safe (Synchronized)	                ❌ Not thread-safe (No synchronization)
Performance	           ⏳ Slower due to synchronization overhead	         ⚡ Faster because it doesn’t use synchronization
Use Case	           When multiple threads access the same string	     When only one thread modifies the string
Introduced In	       Java 1.0	                                         Java 1.5


 */


public class Main {

    public static void main(String[] args) {
        long startTime, endTime;

        StringBuffer stringBuffer = new StringBuffer("Hello");
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            stringBuffer.append(" World");
        }
        endTime = System.nanoTime();
        System.out.println("StringBuffer Time: " + (endTime - startTime) + " ns");

        StringBuilder stringBuilder = new StringBuilder("Hello");
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            stringBuilder.append(" World");
        }
        endTime = System.nanoTime();
        System.out.println("StringBuilder Time: " + (endTime - startTime) + " ns");
    }
}
