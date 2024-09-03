package DSAlgo.strings;

import java.util.HashMap;
import java.util.Map;

public class RomanToDecimal {

    //https://www.geeksforgeeks.org/problems/roman-number-to-integer3201/1

    public int romanToDecimal(String s) {

        //XIX
        Map<Character , Integer> mp = new HashMap<>();
        mp.put('I' , 1);
        mp.put('V', 5 );
        mp.put('X', 10);
        mp.put('L', 50);
        mp.put('C', 100);
        mp.put('D', 500);
        mp.put('M', 1000);
        int sum = 0;
        int n = s.length();
        for(int i = 0 ; i<n ; i++){
            if (i!=n-1 && mp.get(s.charAt(i)) < mp.get(s.charAt(i + 1))){
                sum = sum + mp.get(s.charAt(i + 1)) - mp.get(s.charAt(i));
                i++;
            }else{
                sum = sum + mp.get(s.charAt(i));
            }
        }
        return sum;
    }
}
