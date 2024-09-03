package DSAlgo.arrays;

import java.util.ArrayList;
import java.util.Collections;

public class LeadersInArray {

    //https://www.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1

    static ArrayList<Integer> leaders(int arr[], int n){
        // Your code here
        ArrayList<Integer> ans = new ArrayList<Integer>();

        ans.add(arr[n-1]);
        int c_max = arr[n-1];
        for(int i = n-2 ; i>=0 ; i--){
            if(arr[i]>=c_max){
                c_max = arr[i];
                ans.add(arr[i]);
            }
        }
        Collections.reverse(ans);
        return ans;
    }

}
