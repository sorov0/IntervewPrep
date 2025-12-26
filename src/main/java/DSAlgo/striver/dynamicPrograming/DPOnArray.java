package DSAlgo.striver.dynamicPrograming;

import java.util.ArrayList;
import java.util.Arrays;

public class DPOnArray {

    //https://leetcode.com/problems/climbing-stairs/description/
    static int rec(int n , long[] dp , int mod){

        if(n==0 || n==1) return 1;
        if(dp[n]!=-1) return (int) dp[n];

        int l = rec(n-1, dp , mod);
        int r = rec(n-2, dp , mod);
        dp[n] = (l%mod+r%mod)%mod;

        return (int) dp[n];

    }

    public static int countDistinctWayToClimbStairM(long nStairs) {
        // Write your code here.

        long[] dp= new long[(int)(nStairs+1)];
        int mod=1000000007;
        for(int i = 0 ; i<=nStairs;i++) dp[i] = -1;
        return rec((int) nStairs, dp , mod);

    }

    //https://www.naukri.com/code360/problems/frog-jump_3621012
    // solve(n-1, h, dp)
    static int countMaxEnergy(int i, int h[], int dp[]){
        if(i == 0) return 0;

        if(dp[i] != -1) return dp[i];

        int l = countMaxEnergy(i-1, h, dp) + Math.abs(h[i] - h[i-1]);
        int r = Integer.MAX_VALUE;
        if(i-2 >= 0) r = countMaxEnergy(i-2, h, dp) + Math.abs(h[i] - h[i-2]);

        return dp[i] = Math.min(l,r);
    }

    // https://atcoder.jp/contests/dp/tasks/dp_b
    static int recWithKPossibleJumps(int ind , int h[] , int dp[] , int k){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int minStep = Integer.MAX_VALUE;
        for(int i = 1 ; i<=k ; i++){
            if(ind-i>=0){
                int jmp = recWithKPossibleJumps(ind-i , h , dp , k) + Math.abs(h[ind] - h[ind-i]);
                minStep = Math.min(minStep , jmp);
            }
        }
        return dp[ind] = minStep;
    }

    // https://leetcode.com/problems/house-robber/description/
    // Ye kaam ni karta hai, isko neeche wale ki tarah karna hoga
    public static int rec(int ind , ArrayList<Integer> nums , int dp[]){

        if(ind==0 || ind==1) return nums.get(ind);
        if(dp[ind]!=-1) return dp[ind];
        int pick = 0;
        int notPick = 0 + rec(ind-1 , nums , dp);
        if(ind>1) pick = nums.get(ind) + rec(ind-2 , nums , dp);
        return dp[ind] = Math.max(notPick , pick);
    }

    public static int maximizeProfit(int ind , ArrayList<Integer> nums , int dp[]){
        if(ind<0) return 0;
        if(ind==0) return nums.get(ind);
        if(dp[ind]!=-1) return dp[ind];
        int notPick = 0 + rec(ind-1 , nums , dp);
        int pick = nums.get(ind) + rec(ind-2 , nums , dp);
        return dp[ind] = Math.max(notPick , pick);
    }

    // https://leetcode.com/problems/house-robber-ii/description/
    // Circular house
    public static long houseRobber(int[] valueInHouse) {
        // Write your code here.
        int n = valueInHouse.length;
        int dp[] = new int[n];
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        if(n==1) return valueInHouse[0];
        for(int i = 0 ; i<valueInHouse.length ; i++){
            if(i!=0) arr1.add(valueInHouse[i]);
            if(i!=n-1) arr2.add(valueInHouse[i]);
        }
        long ans1 = maximizeProfit(n-2, arr1, dp);
        long ans2 = maximizeProfit(n-2, arr2, dp);

        return Math.max(ans1,ans2);

    }



}
