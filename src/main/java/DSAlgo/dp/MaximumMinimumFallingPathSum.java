package DSAlgo.dp;

public class MaximumMinimumFallingPathSum {

    //https://bit.ly/3F436dK

    static int rec(int i , int j , int m , int matrix[][] , int dp[][]){


        if(j<0 || j>=m) return (int) Math.pow(-10, 9);
        if(i==0) return matrix[0][j];

        if(dp[i][j] != -1) return dp[i][j];

        int u = matrix[i][j] + rec(i-1 , j , m , matrix , dp);
        int ld = matrix[i][j] + rec(i-1 , j-1 , m , matrix , dp);
        int rd = matrix[i][j] + rec(i-1 , j+1 , m , matrix , dp);

        return dp[i][j] = Math.max(u , Math.max(ld , rd));
    }

    static int tabulation(int n , int m, int[][] matrix , int dp[][]){

        //Tabulation
        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(i==0){
                    dp[i][j] = matrix[i][j];
                }else{
                    int u = matrix[i][j] + dp[i-1][j];

                    int ld = matrix[i][j];
                    if(j-1>=0) ld = ld + dp[i-1][j-1];
                    else ld = (int) Math.pow(-10, 9);

                    int rd = matrix[i][j];
                    if(j+1<m) rd = rd + dp[i-1][j+1];
                    else rd = (int) Math.pow(-10, 9);

                    dp[i][j] = Math.max(u, Math.max(ld, rd));
                }
            }
        }
        int maxi = Integer.MIN_VALUE;
        for (int j = 0; j < m; j++) {
            maxi = Math.max(maxi, dp[n - 1][j]);
        }
        return maxi;
    }

    public static int getMaxPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int dp[][] = new int[n][m];
        int ans = 0;
        for(int j = 0 ; j<m ; j++){
            ans = Math.max(ans , rec(n-1 , j , m , dp , matrix));
        }
        return ans;
    }
}
