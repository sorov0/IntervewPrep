package DSAlgo.dp;

public class MaximumPathSumGrid {

    //https://bit.ly/3q5sqfu

    static int rec(int i , int j , int dp[][] , int[][] grid){
        if(i==0 && j==0) return grid[i][j];
        if(i<0 || j<0) return (int) Math.pow(10, 9);

        if(dp[i][j] != -1) return dp[i][j];
        int up = grid[i][j] + rec(i-1 , j , dp , grid);
        int left = grid[i][j] +  rec(i , j-1 , dp , grid);
        return dp[i][j] = Math.min(up , left);
    }

    public static int minSumPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int dp[][] = new int[n][m];
        for (int i = 0 ; i<n ; i++){
            //Arrays.fill(dp[i], -1);
        }
        //return rec(n-1 , m-1 , dp ,grid);

        //Tabulation
        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(i==0 && j==0){
                    dp[i][j] = grid[i][j];
                }
                else{
                    int up = (int) Math.pow(10, 9);
                    int left = (int) Math.pow(10, 9);
                    if(i-1>=0) up = grid[i][j] + dp[i-1][j];
                    if(j-1>=0) left = grid[i][j] + dp[i][j-1];
                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[n-1][m-1];
    }
}
