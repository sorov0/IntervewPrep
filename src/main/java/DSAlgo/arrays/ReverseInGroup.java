package DSAlgo.arrays;

import java.util.ArrayList;
import java.util.Collections;

public class ReverseInGroup {

    //https://www.geeksforgeeks.org/problems/reverse-array-in-groups0255/1

    void reverseInGroups(ArrayList<Integer> arr, int n, int k) {
        // code here

        for(int i = 0 ; i<n ; i+=k){
            int s = i;
            int e = Math.min(n-1 , i+k-1);
            while(s<e){
                Collections.swap(arr , s , e);
                s++;e--;
            }
        }


    }
}
