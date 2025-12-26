package DSAlgo.practice.strings;

public class RecursivelyRemovedAdjascentDuplicates {

    //https://www.geeksforgeeks.org/problems/recursively-remove-all-adjacent-duplicates0744/1

    String rremove(String s) {

        StringBuilder sb = new StringBuilder(s);
        boolean hasDuplicate = false;

        int i = 0;
        while(i < sb.length()-1){
            if(sb.charAt(i)==sb.charAt(i+1)){
                hasDuplicate = true;
                int j = i+1;
                while(j < sb.length() - 1 && sb.charAt(j) == sb.charAt(i)){
                    j++;
                }
                sb.delete(i , j);
            }else {
                i++;
            }
        }

        if(hasDuplicate){
            return rremove(sb.toString());
        }else{
            return sb.toString();
        }
    }
}
