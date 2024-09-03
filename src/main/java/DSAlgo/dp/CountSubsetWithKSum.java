package DSAlgo.dp;

import java.util.Arrays;

public class CountSubsetWithKSum {

    //https://bit.ly/3B5JBkU

    //When arr[i]>=1
    static int rec(int i , int k , int arr[] , int dp[][]){

        if(k==0) return 1;
        if(i==0) {
            return arr[0] == k ? 1 : 0;
        }
        if(dp[i][k] != -1) return dp[i][k];
        int notPick = rec(i-1 , k , arr , dp);
        int pick = 0;
        if(k-arr[i]>=0) pick = rec(i-1 , k-arr[i] , arr , dp);
        return dp[i][k] = notPick + pick;
    }

    //When arr[i]>=0
    static int rec2(int i , int k , int arr[] , int dp[][]){

        if(k==0) return 1;
        if(i==0) {
            return arr[0] == k ? 1 : 0;
        }
        if(dp[i][k] != -1) return dp[i][k];
        int notPick = rec(i-1 , k , arr , dp);
        int pick = 0;
        if(k-arr[i]>=0) pick = rec(i-1 , k-arr[i] , arr , dp);
        return dp[i][k] = notPick + pick;
    }


    public static int findWays(int num[], int tar) {
        // Write your code here.

        int n = num.length;
        int dp[][] = new int[n][tar+1];
        for (int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i], -1);
        }

        return rec(n-1 , tar , num , dp);
    }
}
