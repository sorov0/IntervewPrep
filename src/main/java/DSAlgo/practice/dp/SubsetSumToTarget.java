package DSAlgo.practice.dp;

import java.util.Arrays;

public class SubsetSumToTarget {

    //https://bit.ly/3ukNmRZ

    static boolean rec(int i , int k , int arr[] , int dp[][]){

        if(k==0) return true;
        if(i==0) return (k==arr[i]);

        if(dp[i][k] != -1) return dp[i][k]==0 ? false : true;

        boolean notPick = rec(i-1 , k , arr , dp);
        boolean pick = false;
        if(k-arr[i]>=0) pick = rec(i-1 , k-arr[i] , arr , dp);
        dp[i][k] = pick || notPick ? 1:0;
        return pick || notPick;
    }

    static boolean tabulation(int n , int k , int arr[]){
        //Tabulation
        boolean dp[][] = new boolean[n][k + 1];
        for (int i = 0; i < n; i++) {
            //Target is zero
            dp[i][0] = true;
        }

        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {
                boolean notTaken = dp[ind - 1][target];

                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = dp[ind - 1][target - arr[ind]];
                }

                dp[ind][target] = notTaken || taken;
            }
        }
        return dp[n - 1][k];

    }

    public static boolean subsetSumToK(int n, int k, int arr[]) {
        // Write your code here.


		int dp[][] = new int[n][k+1];
        for (int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i], -1);
        }
        return rec(n-1 , k , arr , dp);

    }
}
