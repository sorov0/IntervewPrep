package InterviewQuestion.immutability;

import java.util.ArrayList;
import java.util.List;

// Fixing It with Defensive Copying

// Solution:
//
//The constructor creates a new copy of the list instead of storing the reference.
//The getItems() method returns a new copy of the list instead of the original.

final class ImmutableListContainer {
    private final List<String> items;

    public ImmutableListContainer(List<String> items) {
        this.items = new ArrayList<>(items); // Defensive copy in constructor
    }

    public List<String> getItems() {
        return new ArrayList<>(items); // Defensive copy in getter
    }
}
