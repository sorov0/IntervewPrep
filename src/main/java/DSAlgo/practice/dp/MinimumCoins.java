package DSAlgo.practice.dp;

import java.util.Arrays;

public class MinimumCoins {

    //https://bit.ly/3HJTeIl

    static int rec(int ind , int T , int nums[] , int dp[][]){
        if(ind == 0){
            if(T % nums[ind]==0) return T / nums[ind];
            else return (int) Math.pow(10 , 9);
        }
        if(dp[ind][T] != -1) return dp[ind][T];
        int notPick = 0 + rec(ind-1 , T , nums , dp);
        int pick = Integer.MAX_VALUE;
        if(nums[ind] <= T){
            pick = 1 + rec(ind , T - nums[ind] , nums , dp);
        }
        return dp[ind][T] = Math.min(pick , notPick);
    }

    static int tabulation(int nums[] , int T){
        int n = nums.length;
        int dp[][] = new int[n][T + 1];

        // Initialize the dp array for the first element of the array
        for (int i = 0; i <= T; i++) {
            if (i % nums[0] == 0) dp[0][i] = i / nums[0];
            else dp[0][i] = (int) Math.pow(10, 9);
        }
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= T; target++) {

                int notTake = 0 + dp[ind - 1][target];
                int take = (int) Math.pow(10, 9);
                if (nums[ind] <= target) {
                    take = 1 + dp[ind][target - nums[ind]];
                }

                dp[ind][target] = Math.min(notTake, take);
            }
        }
        int ans = dp[n - 1][T];
        if (ans >= (int) Math.pow(10, 9)) return -1;
        return ans;

    }

    public static int minimumElements(int num[], int x) {
        // Write your code here..

        int n = num.length;
        int dp[][] = new int[n][x+1];
        for(int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i] , -1);
        }

        int ans = rec(n-1 , x , num , dp);
        if(ans >= (int) Math.pow(10 , 9)){
            return -1;
        }else{
            return ans;
        }
    }
}
