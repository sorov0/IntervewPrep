package DSAlgo.practice.arrays;

import java.util.ArrayList;

public class MergeTwoSortedArray {

    //https://www.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1

    public static void merge(long arr1[], long arr2[], int n, int m)
    {
        // code here
        ArrayList<Long> arr = new ArrayList<>();

        int i = 0;
        int j = 0;
        while(i<n && j<m){
            if(arr1[i]<=arr2[j]){
                arr.add(arr1[i]);
                i++;
            }
            if(arr1[i]>arr2[j]){
                arr.add(arr2[j]);
                j++;
            }
        }
        while(i<n){
            arr.add(arr1[i]);
            i++;
        }
        while(j<m){
            arr.add(arr2[j]);
            j++;
        }
        for(i = 0 ; i<n ; i++){
            arr1[i] = arr.get(i);
        }
        for(i = 0 , j = n ; i<m ; i++ , j++){
            arr2[i] = arr.get(j);
        }
    }
}
