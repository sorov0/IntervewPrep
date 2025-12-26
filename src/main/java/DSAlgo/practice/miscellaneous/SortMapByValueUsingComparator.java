package DSAlgo.practice.miscellaneous;

import java.util.*;

public class SortMapByValueUsingComparator {

    public static void main(String[] args) {


        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 3);
        map.put("Banana", 2);
        map.put("Orange", 5);
        map.put("Grape", 1);

        // Sort map by values using a custom comparator

        List<Map.Entry<String, Integer>> li = new ArrayList<>(map.entrySet());

        // Comparator to sort by values

        li.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });


        // Print sorted map entries
        for (Map.Entry<String, Integer> entry : li) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
