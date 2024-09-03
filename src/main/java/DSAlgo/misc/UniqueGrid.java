package DSAlgo.misc;

import java.util.ArrayList;
import java.util.Arrays;

public class UniqueGrid {

    //Problem Link: https://bit.ly/3JMHc2l
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

        int dp[][] = new int[n][m];
        for(int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i], -1);
        }
        return rec(n-1 , m-1 , dp , mat);
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
