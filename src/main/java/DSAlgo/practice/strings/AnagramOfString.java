package DSAlgo.practice.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramOfString {

    //https://www.geeksforgeeks.org/problems/anagram-1587115620/1

    public static boolean isAnagram(String a,String b)
    {

        if(a.length() != b.length()){
            return false;
        }
        // Your code here
        Map<Character , Integer> mp = new HashMap<>();

        for(int i = 0 ; i<a.length() ; i++){
            if( mp.containsKey(a.charAt(i))){
                mp.put(a.charAt(i) , mp.get(a.charAt(i)) + 1);
            }else{
                mp.put(a.charAt(i) , 1);
            }
        }
        for(int i = 0 ; i<b.length() ; i++){
            if( mp.containsKey(b.charAt(i))){
                mp.put(b.charAt(i) , mp.get(b.charAt(i)) - 1);
            }else{
                return  false;
            }
        }
        for(Map.Entry<Character,Integer> entry: mp.entrySet()){
            if(entry.getValue() > 0){
                return false;
            }
        }
        return true;

        //----------------------

        /*
        char[] c = a.toCharArray();
        char[] d = b.toCharArray();
        Arrays.sort(c);
        Arrays.sort(d);
        String x = new String(c);
        String y = new String(d);
        if(x.equals(y)) return true;
        else return false;
        */


        //---------------------
    }

    public static void main(String[] args) {
        isAnagram("aabaa" , "baaaaa");
    }
}
