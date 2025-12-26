package DSAlgo.practice.strings;

import java.util.*;

public class PermutationOfString {

    //https://www.geeksforgeeks.org/problems/permutations-of-a-given-string2041/1

    public static void swap(char arr[] , int i, int j){
        char tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void rec(char arr[] , int s , int e , SortedSet<String> ans){
        if(s==e){
            ans.add(new String(arr));
        }
        for(int i = s ; i<e ; i++){
            swap(arr , s , i);
            rec(arr , s+1 , e , ans);
            swap(arr , s , i);
        }
    }

    public List<String> find_permutation(String str) {
        SortedSet<String> ans = new TreeSet<>();
        int i = 0;
        int l = str.length();
        char arr[] = str.toCharArray();

        rec(arr , i , l , ans);
        return new ArrayList<>(ans);
    }
}
