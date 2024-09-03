package DSAlgo.dp;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    //https://bit.ly/34iIIsH

    static boolean rec(int i , int k , int arr[] , int dp[][]){

        if(k==0) return true;
        if(i==0) return (k==arr[i]);

        if(dp[i][k] != -1) return dp[i][k]==0 ? false : true;

        boolean notPick = rec(i-1 , k , arr , dp);

        boolean pick = false;
        if(k-arr[i]>=0) pick = rec(i-1 , k-arr[i] , arr , dp);

        dp[i][k] = notPick || pick ? 1 : 0;
        return notPick || pick;
    }
    public static boolean canPartition(int[] arr, int n) {
        // Write your code here.

        int sum = 0;
        for(int i = 0 ; i<n ; i++){
            sum = sum + arr[i];
        }
        if(sum%2==1) return false;
        int k = sum/2;

        int dp[][] = new int[n][k+1];
        for (int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i], -1);
        }
        return rec(n-1 , k , arr , dp);

    }
}
