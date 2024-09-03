package DSAlgo.dp;

import java.util.ArrayList;
import java.util.Arrays;

public class UniquePath2 {

    //https://bit.ly/3JMHc2l

    static int rec(int i , int j , int dp[][] , ArrayList<ArrayList<Integer>> mat){
        if(i==0 && j==0) return 1;
        if(i<0 || j<0 || mat.get(i).get(j) == -1) return 0;

        if(dp[i][j] != -1) return dp[i][j];
        int up = rec(i-1 , j , dp , mat);
        int left = rec(i , j-1 , dp , mat);
        return dp[i][j] = up + left;
    }

    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        // Write your code here.
        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        rec(n-1 , m-1 , dp , mat);
        return 0;

    }

}
