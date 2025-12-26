package DSAlgo.practice.arrays;

import java.util.Arrays;

public class PythagoreanTriplet {

    //https://www.geeksforgeeks.org/problems/pythagorean-triplet3018/1

    boolean checkTriplet(int[] arr, int n) {
        // code here
        boolean flag = false;
        for(int i = 0 ; i<n ; i++){
            arr[i] = arr[i]* arr[i];
        }
        Arrays.sort(arr);
        for(int i = n-1 ; i>=2 ; i--){
            int sum = arr[i];

            int s = 0;
            int e = i-1;
            while(s<e){
                if(arr[s] + arr[e] == sum ){
                    flag = true;
                    break;
                }
                if(arr[s] + arr[e]>sum ){
                    e--;
                }else{
                    s++;
                }
            }
            if(flag==true) break;
        }
        return flag;
    }
}
