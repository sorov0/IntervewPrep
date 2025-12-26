package DSAlgo.practice.miscellaneous;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapCustom {

    public static void main(String[] args) {

        // Create a TreeMap with a custom comparator (descending order of keys)

        SortedMap<Integer, String> sortedMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer key1, Integer key2) {
                return key2.compareTo(key1); // Descending order
            }
        });

        // Add entries to the map
        sortedMap.put(3, "Apple");
        sortedMap.put(1, "Banana");
        sortedMap.put(4, "Grapes");
        sortedMap.put(2, "Orange");

        // Print the sorted map (by keys in descending order)
        System.out.println("SortedMap (descending by keys):");
        for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
