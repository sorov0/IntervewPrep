package collections.mapInterface;

import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapDemo {

    public static void main(String[] args) {

        SortedMap<Integer, String> stringSortedMap = new TreeMap<>((a,b) -> b-a );

        stringSortedMap.put(1,"saurav");
        stringSortedMap.put(6,"Mohit");
        stringSortedMap.put(4,"RAj");
        stringSortedMap.put(2,"Sina");

        System.out.println(stringSortedMap.firstKey());
        System.out.println(stringSortedMap.lastKey());
        System.out.println(stringSortedMap.headMap(1));
        System.out.println(stringSortedMap.tailMap(6 ));

    }
}
