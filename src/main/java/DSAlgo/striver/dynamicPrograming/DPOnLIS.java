package DSAlgo.striver.dynamicPrograming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class DPOnLIS {


    // https://leetcode.com/problems/longest-increasing-subsequence/
    // lengthOfLIS(0, -1, nums, dp);
    // initialize the dp wit n+1 due to co-ordinate shift (starting prevInd with -1)
    public int lengthOfLIS(int ind, int prevInd, int[] nums, int dp[][]) {

        if(ind == nums.length) return 0;

        if(dp[ind][prevInd + 1] != -1) return dp[ind][prevInd];
        int len = 0 + lengthOfLIS(ind + 1, prevInd, nums, dp);

        if(prevInd == -1 || nums[ind] > nums[prevInd]){
            len = Math.max(len, 1 + lengthOfLIS(ind+1, ind, nums, dp));
        }

        return dp[ind][prevInd + 1] = len;

    }

    public int lengthOfLIS2ndMethod(int[] nums) {

        int maxi = 1;
        int dp[] = new int[nums.length];
        Arrays.fill(dp, 1);

        for(int i = 0 ; i<nums.length ; i++){
            for(int prev = 0 ; prev<i ; prev++){
                if(nums[prev] < nums[i]){
                    dp[i] = 1 + dp[prev];
                }
            }
            maxi = Math.max(maxi, dp[i]);
        }
        return maxi;

    }

    // https://www.geeksforgeeks.org/problems/printing-longest-increasing-subsequence/1
    static ArrayList<Integer> longestIncreasingSubsequence(int nums[]) {

        int maxi = 1;
        int lastIndex = 0;
        int dp[] = new int[nums.length];
        Arrays.fill(dp, 1);

        int hash[] = new int[nums.length];
        for(int i = 0 ; i<nums.length ; i++){
            hash[i] = i;
            for(int prev = 0 ; prev<i ; prev++){
                if(nums[prev] < nums[i] && 1 + dp[prev] > dp[i]){
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            if(dp[i] > maxi){
                maxi = dp[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(nums[lastIndex]);

        while(hash[lastIndex] != lastIndex){ // till not reach the initialization value
            lastIndex = hash[lastIndex];
            temp.add(nums[lastIndex]);
        }
        Collections.reverse(temp);
        return temp;
    }


    // https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
    static int findNumberOfLIS(int[] arr){

        int n = arr.length;
        int[] dp= new int[n];
        int[] ct= new int[n];

        Arrays.fill(dp,1);
        Arrays.fill(ct,1);

        int maxi = 1;

        for(int i=0; i<=n-1; i++){
            for(int prev_index = 0; prev_index <=i-1; prev_index ++){

                if(arr[prev_index]<arr[i] && dp[prev_index]+1>dp[i]){
                    dp[i] = dp[prev_index]+1;
                    //inherit
                    ct[i] = ct[prev_index];
                }
                else if(arr[prev_index]<arr[i] && dp[prev_index]+1==dp[i]){
                    //increase the count
                    ct[i] = ct[i] + ct[prev_index];
                }
            }
            maxi = Math.max(maxi,dp[i]);
        }

        int nos =0;

        for(int i=0; i<=n-1; i++){
            if(dp[i]==maxi) nos = nos + ct[i];
        }

        return nos;
    }


}
