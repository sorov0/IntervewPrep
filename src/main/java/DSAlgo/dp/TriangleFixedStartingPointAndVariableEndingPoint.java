package DSAlgo.dp;

public class TriangleFixedStartingPointAndVariableEndingPoint {

    //https://bit.ly/3K1cvqv

    static int rec(int i , int j , int dp[][] , int[][] triangle , int n){

        if(i==n-1) return triangle[i][j];

        if(dp[i][j] != -1) return dp[i][j];

        int down = triangle[i][j] + rec(i+1 , j , dp , triangle , n);
        int diagonal = triangle[i][j] + rec(i+1 , j+1 , dp , triangle , n);

        return dp[i][j] = Math.min(down, diagonal);


    }

    public static int minimumPathSum(int[][] triangle, int n) {
        // Write your code here.

        int dp[][] = new int[n][n];
        for (int i = 0 ; i<n ; i++){
            //Arrays.fill(dp[i], -1);
        }
        //return rec(0 , 0 , dp , triangle , n);

        //Tabulation
        for(int i = n-1 ; i>=0 ; i--){
            for(int j = i ; j>=0 ; j--){
                if(i==n-1){
                    dp[i][j] = triangle[i][j];
                }
                else{
                    int down = triangle[i][j] + dp[i+1][j];
                    int diagonal = triangle[i][j] + dp[i+1][j+1];
                    dp[i][j] = Math.min(down, diagonal);
                }
            }
        }
        return dp[0][0];
    }
}
