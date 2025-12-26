package DSAlgo.practice.miscellaneous;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {

        Map<Integer, String> mp = new HashMap<>();

        mp.put(1,"Saurav");
        mp.put(2,"Gaurav");
        mp.put(3,"Abhishek");

        for(Map.Entry<Integer,String> map : mp.entrySet()){

            System.out.println(map.getKey());
            System.out.println(map.getValue());
        }

        for(Integer key : mp.keySet()){
            System.out.println(key);
        }

        for(String value : mp.values()){
            System.out.println(value);
        }

    }
}
