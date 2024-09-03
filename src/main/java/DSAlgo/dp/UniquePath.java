package DSAlgo.dp;

import java.util.Arrays;

public class UniquePath {

    //https://bit.ly/34uoYCG

    static int rec(int i , int j , int dp[][]){
        if(i==0 && j==0) return 1;
        if(i<0 || j<0) return 0;
        if(dp[i][j]!=-1) return dp[i][j];

        int up = rec(i-1 , j , dp);
        int left = rec(i , j-1 , dp);
        return dp[i][j] = up+left;
    }

    public static int uniquePaths(int m, int n) {

        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        //return rec(n-1 , m-1 , dp);

        //Tabulation
        for(int i = 0 ; i<m ; i++){
            for(int j = 0 ; j<n ; j++){
                if(i==0 && j==0) dp[i][j] = 1;
                else{
                    int up = 0;
                    int left = 0;
                    if(i-1>=0) up = dp[i-1][j];
                    if(j-1>=0) left = dp[i][j-1];
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m-1][n-1];
    }

}
