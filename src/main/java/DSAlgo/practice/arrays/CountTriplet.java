package DSAlgo.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CountTriplet {

    //https://www.geeksforgeeks.org/problems/count-the-triplets4615/1

    int countTriplet1(int arr[], int n) {
        int count = 0;
        Arrays.sort(arr);
        for(int i = n-1 ; i>=2 ; i--){
            int j = 0;
            int k = i-1;
            while(j<k){
                if(arr[i]==arr[j]+arr[k]){
                    count++;
                    j++;
                    k--;
                }else if(arr[i]+arr[j]<arr[k]){
                    i++;
                }else{
                    j--;
                }
            }
        }
        return count;

    }

    int countTriplet(int arr[], int n) {
        Arrays.sort(arr);
        int cnt = 0;
        // code here
        for(int k = n-1 ; k>=2 ; k--){
            int i = 0;
            int j = k-1;
            while(i<j){
                if(arr[i]+arr[j]==arr[k]){
                    cnt++;
                    i++;
                    j--;
                }else if(arr[i]+arr[j]<arr[k]){
                    i++;
                }else{
                    j--;
                }
            }
        }
        return cnt;
    }
}
