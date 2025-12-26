package DSAlgo.practice.dp;

public class CountWaysToReachNthStairs {

    //https://bit.ly/3t1Sjyx

    //Tabulation
    public static int countDistinctWayToClimbStair(long nStairs) {

        if (nStairs <= 1) {
            return 1;
        }

        long[] dp= new long[(int)(nStairs+1)];
        int mod=1000000007;
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=nStairs;i++){
            dp[i]=((dp[i-1]%mod)+(dp[i-2]%mod))%mod;
        }
        return (int)dp[(int)nStairs];
    }

    //Memoization
    static int rec(int n , long[] dp , int mod){

        if(n==0 || n==1) return 1;
        if(dp[n]!=-1) return (int) dp[n];
        else{
            int l = rec(n-1,dp , mod);
            int r = rec(n-2,dp , mod);
            dp[n] = (l%mod+r%mod)%mod;
            return (int) dp[n];
        }
    }
    public static int countDistinctWayToClimbStairM(long nStairs) {
        // Write your code here.

        long[] dp= new long[(int)(nStairs+1)];
        int mod=1000000007;
        for(int i = 0 ; i<=nStairs;i++) dp[i] = -1;
        return rec((int) nStairs, dp , mod);

    }





}
