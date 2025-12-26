package DSAlgo.practice.strings;

import java.util.HashMap;
import java.util.Map;

public class LongestDistinctCharInString {

    //https://www.geeksforgeeks.org/problems/longest-distinct-characters-in-string5848/1

    static int longestSubstrDistinctChars(String S){

        // code here
        // geeksforgeeks

        Map<Character , Integer> mp = new HashMap<>();
        int s = 0;
        int e = 0;
        int len = 0;
        while(s<S.length() && e<S.length()){
            if(!mp.containsKey(S.charAt(e)) || mp.get(S.charAt(e))==0){
                mp.put(S.charAt(e) , 1);
                e++;
            }
            else if(mp.get(S.charAt(e)) > 0){
                len = Math.max(len , e-s);
                mp.put(S.charAt(s) , mp.get(S.charAt(s)) - 1);
                s++;
            }
            len = Math.max(len , e-s);
        }
        return len;
    }

    public static void main(String[] args) {
        int num = 10;

        System.out.println("Case 1:");
        if(num%2==0){
            System.out.println("First Statement executed");
        }else{
            System.out.println("Second Statement executed");
        }

        System.out.println("Case 2:");
        if(num%2==0){
            System.out.println("First Statement executed");
        }else if(num%5==0){
            System.out.println("Second Statement executed");
        }

        System.out.println("Case 3:");
        if(num%2==0){
            System.out.println("First Statement executed");
        }if(num%5==0){
            System.out.println("Second Statement executed");
        }

    }
}
