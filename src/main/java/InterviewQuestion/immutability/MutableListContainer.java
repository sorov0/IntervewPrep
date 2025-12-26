package InterviewQuestion.immutability;

import java.util.ArrayList;
import java.util.List;

// Example Without Defensive Copying (Breaks Immutability)

// Problem: Even though MutableListContainer is supposed to be immutable, the internal items list can be modified externally.

final class MutableListContainer {
    private final List<String> items;

    public MutableListContainer(List<String> items) {
        this.items = items; // Direct assignment (Not defensive)
    }

    public List<String> getItems() {
        return items; // Returning the original list (Breaks immutability)
    }
}
