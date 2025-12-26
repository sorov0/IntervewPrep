package InterviewQuestion.immutability;


/*
What is immutability? What are the other types of immutable classes in Java?
Can you create a custom immutable class in coderpad?


What is Immutability?
Immutability means that an object’s state cannot be modified after it is created.
In Java, immutable objects are instances whose fields do not change after the object is constructed.

Immutable Classes in Java
Some common immutable classes in Java include:

String
Wrapper classes (Integer, Double, Float, etc.)
BigInteger and BigDecimal
LocalDate, LocalTime, and LocalDateTime (Java 8 Time API)
UUID

Creating a Custom Immutable Class in Java
To create an immutable class, follow these rules:

Declare the class as final (to prevent subclassing).
Make all fields private and final.
Do not provide setters.
Initialize fields through the constructor.
Return defensive copies of mutable fields.
Here’s an example implementation of an immutable class in Java:

==========================================================================================

What does it mean:
Return defensive copies of mutable fields.

If a class has a field that holds a reference to a mutable object (like a List, Map, Date, or another custom object),
returning the field directly in a getter would expose the internal state of the object, breaking immutability.

Defensive copying means returning a new copy of the mutable object instead of the original reference,
preventing unintended modifications.

==========================================================================================

should you have getters and setters in your immutable class? And what about constructors?

Getters, Setters, and Constructors in an Immutable Class
1️⃣ Should an Immutable Class Have Getters?
✅ Yes, immutable classes should have getters to provide read access to their fields.
However, the getters must not return mutable references unless they return a defensive copy (if needed).

2️⃣ Should an Immutable Class Have Setters?
❌ No, an immutable class must not have setters, because setters allow modification of the object state.

Removing setters ensures that the object’s state cannot be changed after it is created.

3️⃣ What About Constructors?
✅ Yes, immutable classes need constructors to initialize their fields.
The constructor must:

Initialize all fields when the object is created.
Use defensive copies if receiving mutable objects (like List, Date).

✅ Key Takeaways:
✔ Getters → Allowed, but be cautious with mutable fields.
✔ Setters → ❌ Not allowed (no modification after creation).
✔ Constructors → Used to initialize fields, with defensive copies when needed.

https://chatgpt.com/share/67add674-d970-8012-a5ae-5b344e4cb283

*/

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ImmutablePerson person = new ImmutablePerson("Alice", 30);
        System.out.println(person);

        List<String> list = new ArrayList<>();
        list.add("Apple");

        MutableListContainer mutableListContainer = new MutableListContainer(list);
        mutableListContainer.getItems().add("Banana"); // Modifies the original list

        System.out.println(mutableListContainer.getItems()); // Output: [Apple, Banana] (Changed!)



        ImmutableListContainer immutableListContainer = new ImmutableListContainer(list);
        immutableListContainer.getItems().add("Banana"); // This modifies a copy, not the original list

        System.out.println(immutableListContainer.getItems()); // Output: [Apple] (Unchanged ✅)
    }

}
