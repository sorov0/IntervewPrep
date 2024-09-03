package DSAlgo.dp;

import java.util.Arrays;

public class _0_1_Knapsack {

    //https://bit.ly/3KHpP3v

    static int rec(int ind , int W, int wt[] , int val[] , int dp[][]){

        if(ind == 0){
            if(wt[ind]<=W) return val[ind];
            return 0;
        }

        if(dp[ind][W] != -1) return dp[ind][W];

        int notPick = 0 + rec(ind-1 , W , wt , val , dp);

        int pick = Integer.MIN_VALUE;
        if(wt[ind] <= W){
            pick = val[ind] + rec(ind-1 , W - wt[ind] , wt , val , dp);
        }
        return dp[ind][W] = Math.max(notPick , pick);
    }

    static int tabulation(int n, int W, int wt[] , int val[]){

        int dp[][] = new int[n][W + 1];

        //Setting base case:
        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = val[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int cap = 0; cap <= W; cap++) {

                int notTaken = dp[ind - 1][cap];

                int taken = Integer.MIN_VALUE;
                if (wt[ind] <= cap) {
                    taken = val[ind] + dp[ind - 1][cap - wt[ind]];
                }
                dp[ind][cap] = Math.max(notTaken, taken);

            }
        }
        return dp[n - 1][W];

    }

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int dp[][] = new int[n][maxWeight+1];
        for(int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i] , -1);
        }
        return rec(n-1 , maxWeight , weight , value , dp);

    }
}
