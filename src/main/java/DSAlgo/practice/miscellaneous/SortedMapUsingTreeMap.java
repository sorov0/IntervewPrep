package DSAlgo.practice.miscellaneous;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapUsingTreeMap {

    public static void main(String[] args) {
        // Create a SortedMap (TreeMap)
        SortedMap<Integer, String> sortedMap = new TreeMap<>();

        // Put entries into the map
        sortedMap.put(3, "Apple");
        sortedMap.put(1, "Banana");
        sortedMap.put(4, "Grapes");
        sortedMap.put(2, "Orange");

        // Print the sorted map (sorted by keys)
        System.out.println("SortedMap (by keys):");
        for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
