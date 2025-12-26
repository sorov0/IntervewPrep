package DSAlgo.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class SubarraySum {

    //https://www.geeksforgeeks.org/problems/subarray-with-given-sum-1587115621/1
    static ArrayList<Integer> subarraySum(int[] arr, int n, int s)
    {
        // Your code here
        ArrayList<Integer> indArr = new ArrayList();
        int start = 0 , end = 0 , sum = 0;
        boolean flag = false;
        for(int i = 0 ; i<arr.length ; i++){
            sum = sum + arr[i];
            while(sum>s && start<i){
                sum = sum - arr[start];
                start++;
            }
            if(sum==s){
                flag = true;
                end = i;
                break;
            }
        }
        if(flag){
            indArr.add(start+1);
            indArr.add(end+1);
        }else{
            indArr.add(-1);
        }
        return indArr;
    }


    public static void main(String[] args){
        ArrayList newArr = new ArrayList(Arrays.asList(1 ,2, 3));
        int[] newArr1 = new int[]{1 , 2, 3};
        subarraySum(newArr1 , newArr.size() , newArr.size());
    }
}
