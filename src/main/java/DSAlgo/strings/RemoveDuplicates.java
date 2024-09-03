package DSAlgo.strings;

import java.util.HashSet;

public class RemoveDuplicates {

    //https://www.geeksforgeeks.org/problems/remove-duplicates3034/1

    String removeDups(String S) {
        // code here
        HashSet<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++){
            char curr = S.charAt(i);
            if (!set.contains(curr)){
                set.add(curr);
                sb.append(curr);
            }
        }
        return sb.toString();
    }
}
