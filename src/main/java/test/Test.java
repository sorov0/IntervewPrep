package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {


    //

    static int firstRepeatingChar(String str){

        HashMap<Character, Integer> mp = new HashMap<>();
        for(int i = 0 ; i<str.length() ; i++){
            if(!mp.containsKey(str.charAt(i))){
                mp.put(str.charAt(i), 1);
            }else{
                mp.put(str.charAt(i), mp.get(str.charAt(i) + 1));
            }
        }


        for(int i = 0 ; i<str.length() ;i++){
            if(mp.get(str.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        String str = "loveleetcode";
        System.out.println(firstRepeatingChar(str));
    }
}
