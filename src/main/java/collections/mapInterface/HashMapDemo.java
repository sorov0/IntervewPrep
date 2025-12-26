package collections.mapInterface;


// Order: Some implementation maintain insertion order(LinkedHashMap), NaturalOrder(TreeMap), No order(HashMap)
// HashMap: Unordered, aloows null keys and values, Not synchronised i.e not threadsafe
// Internal Working of Hashmap: Initial array of default 16 size created, With the help of Hashing function,
// Has code is generated for eah key and then index is found using modulo operator
// In case of collision each index can have multiple key and value intially in LinkedList DS after 8 node, it gets converted
// to Red Black tree(Self balancing binary search tree). The threshold value is 8 and load factor is 0.75
// While creating a custom class, We must need to override equals and hascode function of Custom class.
// Default hashing occurs on the basis if object referece and it can cause error.

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {

    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Saurav");
        map.put(2,"Muski");

        String value = map.get(1);

        System.out.println(map.containsKey(1));
        System.out.println(map.containsValue("Saurav"));

        Set<Integer> keySet = map.keySet();
        List<String> values = (List<String>) map.values();

        for(Integer key : keySet){
            System.out.println(map.get(key));
        }

        for(Map.Entry<Integer, String> mp : map.entrySet()){
            System.out.println(mp.getKey() + " " + mp.getValue());
        }

        map.remove(2);

        map.putIfAbsent(2,"Muski");
        String res = map.getOrDefault(2, "nothing");




    }
}
