package DSAlgo.dp;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumSumOfNonAdjascentElement {

    // https://bit.ly/3q5rlUY

    public static int recTest(int ind , ArrayList<Integer> nums , int dp[]){

        if(ind==0) return nums.get(ind);
        if(ind<0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int notPick = 0 + rec(ind-1 , nums , dp);
        int pick = nums.get(ind) + rec(ind-2 , nums , dp);
        return dp[ind] = Math.max(notPick , pick);
    }

    //Keep the base case implementation same way in every problem
    public static int rec(int ind , ArrayList<Integer> nums , int dp[]){

        if(ind==0 || ind==1) return nums.get(ind);
        if(dp[ind]!=-1) return dp[ind];
        int pick = 0;
        int notPick = 0 + rec(ind-1 , nums , dp);
        if(ind>1) pick = nums.get(ind) + rec(ind-2 , nums , dp);
        return dp[ind] = Math.max(notPick , pick);
    }

    public static int tabulation(ArrayList<Integer> nums , int dp[]){

        Arrays.fill(dp, 0);
        dp[0] = nums.get(0);
        for(int i = 1 ; i<nums.size() ; i++){
            int l = 0 + dp[i-1];
            int r = nums.get(i);
            if(i-1>0) r = nums.get(i) + dp[i-2];
            dp[i] = Math.max(l , r);
        }
        return dp[nums.size()-1];
    }
    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        // Write your code here.
        int dp[] = new int[nums.size()];
        Arrays.fill(dp , -1);
        //return rec(nums.size()-1 , nums , dp);
        return tabulation(nums , dp);
    }

}
