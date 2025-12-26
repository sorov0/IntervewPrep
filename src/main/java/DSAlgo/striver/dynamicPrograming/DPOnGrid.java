package DSAlgo.striver.dynamicPrograming;

import java.util.ArrayList;
import java.util.List;

public class DPOnGrid {

    //https://leetcode.com/problems/unique-paths/description/
    // find number of unique path
    //uniquePaths(n-1,m-1,dp)
    static int uniquePaths(int i, int j, int dp[][]) {
        if(i==0 && j==0) return 1;
        if(i<0 || j<0) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        int up = uniquePaths(i-1 , j, dp);
        int left = uniquePaths(i,j-1, dp);
        return dp[i][j] = up + left;
    }

    // find and print all unique unique path
    //uniquePathsPrint(n-1,m-1,paths, path)
    static void uniquePathsPrint(int i, int j, List<String> paths, String path) {
        if(i==0 && j==0){
            paths.add(path) ;
            return;
        }
        if(i<0 || j<0) return;

        uniquePathsPrint(i-1 , j, paths, path + "R");
        uniquePathsPrint(i,j-1, paths, path + "D");
    }

    ///https://leetcode.com/problems/unique-paths-ii/description/
    // find unique path with hurdles
    // uniquePathsWithHurdle(n-1, m-1, hurdle)
    static int uniquePathsWithHurdle(int i, int j, boolean hurdle[][]){
        if(i>=0 && j>=0 && hurdle[i][j]==false) return 0;
        if(i==0 && j==0) return 1;
        if(i<0 || j<0) return 0;
        int up = uniquePathsWithHurdle(i-1 , j, hurdle);
        int left = uniquePathsWithHurdle(i,j-1,hurdle);
        return up+left;
    }

    // https://leetcode.com/problems/minimum-path-sum/description/
    // find minimum path sum
    static int findMinimumPathSum(int i, int j, int grid[][], int dp[][]){
        if(i==0 && j==0) return grid[i][j];
        if(i<0 || j<0) return Integer.MAX_VALUE;

        if(dp[i][j] != -1) return dp[i][j];
        int up = grid[i][j] + findMinimumPathSum(i-1, j, grid, dp);
        int left = grid[i][j] + findMinimumPathSum(i, j-1, grid, dp);
        return dp[i][j] = Math.min(up, left);
    }

    //https://www.naukri.com/code360/problems/triangle_1229398

    static int rec(int i , int j , int dp[][] , int[][] triangle , int n){

        if(i==n-1) return triangle[i][j];

        if(dp[i][j] != -1) return dp[i][j];

        int down = triangle[i][j] + rec(i+1 , j , dp , triangle , n);
        int diagonal = triangle[i][j] + rec(i+1 , j+1 , dp , triangle , n);

        return dp[i][j] = Math.min(down, diagonal);


    }

    //https://www.geeksforgeeks.org/problems/geeks-training/1?
    // return solve(n - 1, 3, points, dp);
    static int solve(int day , int lastAct , int points[][] , int dp[][]){

        if(day==0){
            int maxi = 0;
            for(int i = 0 ; i<3 ; i++){
                if(i!=lastAct){
                    maxi = Math.max(maxi, points[0][i]);
                }
            }
            return dp[day][lastAct] = maxi;
        }

        if(dp[day][lastAct] != -1) return dp[day][lastAct];

        int maxi = 0;
        for(int i = 0 ; i<3 ; i++){
            if(i!=lastAct){
                int actPoint = points[day][i] + solve(day-1 , i , points , dp);
                maxi = Math.max(maxi, actPoint);
            }
        }
        return dp[day][lastAct] = maxi;
    }

   // maximal Square
    public int maximalSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int maxSide = 0;

        // Fill DP table
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;  // First row or first column
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide; // Return area
    }

    // https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/
    public int countSquares(int[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int maxSide = 0;

        // Fill DP table
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;  // First row or first column
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                }
            }
        }
        int cnt = 0;
        for(int i = 0 ; i<rows ; i++){
            for(int j = 0 ; j<cols ; j++){
                cnt += dp[i][j];
            }
        }
        return cnt;

    }


    public static void main(String[] args) {
        int n = 3, m = 3;
        List<String> paths = new ArrayList<>();

        uniquePathsPrint(n-1, m-1, paths, "");

        System.out.println("All Unique Paths:");
        for (String path : paths) {
            System.out.println(path);
        }
    }

}
