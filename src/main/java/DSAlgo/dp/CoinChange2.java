package DSAlgo.dp;

import java.util.Arrays;

public class CoinChange2 {

    static long rec(int ind , int T , int nums[] , long dp[][]){
        if(ind == 0){
            if(T % nums[ind] == 0) return 1;
            else return 0;
        }
        if(dp[ind][T] != -1) return dp[ind][T];
        long notPick = rec(ind-1 , T , nums , dp);
        long pick = 0;
        if(nums[ind] <= T) pick = rec(ind , T - nums[ind] , nums , dp);
        return dp[ind][T] = notPick + pick;
    }

    public static long countWaysToMakeChange(int denominations[], int value){
        //write your code here
        int n = denominations.length;
        long dp[][] = new long[n][value + 1];

        for (long row[] : dp)
            Arrays.fill(row, -1);
        return rec(n-1 , value , denominations , dp);
    }

}
