package DSAlgo.dp;

import java.util.Arrays;

public class FrogJump {

    //https://bit.ly/3JPcoOx
    //https://atcoder.jp/contests/dp/tasks/dp_b

    static int rec(int ind , int heights[] , int dp[]){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int r = Integer.MAX_VALUE;
        int l = Math.abs(heights[ind] - heights[ind-1]) + rec(ind-1 , heights , dp);
        if(ind-2>=0) r = Math.abs(heights[ind] - heights[ind-2]) + rec(ind-2 , heights , dp);
        return dp[ind] = Math.min(l , r);
    }

    public static int frogJump_Memoization(int n, int heights[]) {

        // Write your code here..
        //Memoization
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        return rec(n-1 , heights , dp);

    }

    public static int frogJump_Tabulation(int n, int heights[]) {

        //Tabulation:
        int dp[] = new int[n];
        Arrays.fill(dp, 0);
        dp[0] = 0;
        for(int i = 1 ; i<n ; i++){
            int f = dp[i-1] + Math.abs(heights[i] - heights[i-1]);
            int s = Integer.MAX_VALUE;
            if(i-2>=0) s = dp[i-2] + Math.abs(heights[i] - heights[i-2]);
            dp[i] = Math.min(f, s);
        }
        return dp[n-1];

    }

    public static int frogJump_Tabulation_SpaceOptimized(int n, int heights[]) {
        int prev1 = 0;
        int prev2 = 0;
        for(int i = 1 ; i<n ; i++){
            int f = prev1 + Math.abs(heights[i] - heights[i-1]);
            int s = Integer.MAX_VALUE;
            if(i-2>=0) s = prev2 + Math.abs(heights[i] - heights[i-2]);
            int cur = Math.min(f, s);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }


    static int recWithKPossibleJumps(int ind , int heights[] , int dp[] , int k){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int minStep = Integer.MAX_VALUE;
        for(int i = 1 ; i<=k ; i++){
            if(ind-i>=0){
                int jmp = recWithKPossibleJumps(ind-i , heights , dp , k) + Math.abs(heights[ind] - heights[ind-i]);
                minStep = Math.min(minStep , jmp);
            }
        }
        return dp[ind] = minStep;

    }

    public static int frogJumpWithKPossibleJumps(int n, int heights[] , int k) {

        // Write your code here..
        //space optimization
        int dp[] = new int[n];
        Arrays.fill(dp, 0);
        for(int ind = 1 ; ind<n ; ind++){
            int minStep = Integer.MAX_VALUE;
            for(int i = 1 ; i<=k ; i++){
                if(ind-i>=0){
                    int jmp = dp[ind-i] + Math.abs(heights[ind] - heights[ind - i]);
                    minStep = Math.min(minStep, jmp);
                }
            }
            dp[ind] = minStep;
        }
        return dp[n-1];

    }


}
