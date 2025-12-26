package DSAlgo.practice.dp;

import java.util.Arrays;

public class CherryPickUp2 {

    //https://bit.ly/3KQELfy

    static int rec(int i , int ja , int jb , int n , int m , int[][] grid , int dp[][][]){
        if(ja<0 || ja>=m || jb<0 || jb>=m) return (int) Math.pow(-10 , 9);
        if(i==n-1){
            if(ja == jb){
                return grid[i][ja];
            }else{
                return grid[i][ja] + grid[i][jb];
            }
        }
        else{
            if(dp[i][ja][jb] != -1) return dp[i][ja][jb];
            int maxi = Integer.MIN_VALUE;
            for(int dja = -1 ; dja<=+1 ; dja++){
                for(int djb = -1 ; djb<=+1 ; djb++){
                    int ans;
                    if(ja == jb){
                        ans = grid[i][ja] + rec(i+1 , ja + dja , jb + djb , n , m , grid , dp);
                    }else{
                        ans = grid[i][ja] + grid[i][jb] + rec(i+1 , ja + dja , jb + djb , n , m , grid , dp);
                    }
                    maxi = Math.max(maxi , ans);
                }
            }
            return dp[i][ja][jb] = maxi;
        }
    }

    static int tabulation(int r , int c , int[][] grid){
        //Tabulation
        int n = r;
        int m = c;
        int dp[][][] = new int[n][m][m];

        for(int i = n-1 ; i>=0 ; i--){
            for(int j1 = 0 ; j1<m ; j1++){
                for(int j2 = 0 ; j2<m ; j2++){

                    if(i==n-1){
                        if(j1==j2){
                            dp[i][j1][j2] = grid[i][j1];
                        }else{
                            dp[i][j1][j2] = grid[i][j1] + grid[i][j2];
                        }
                    }
                    else{
                        int maxi = Integer.MIN_VALUE;
                        for(int di = -1 ; di<=+1 ; di++){
                            for(int dj = -1 ; dj<=+1 ; dj++){
                                int ans;
                                if(j1==j2){
                                    ans = grid[i][j1];
                                }else{
                                    ans = grid[i][j1] + grid[i][j2];
                                }

                                if ((j1 + di < 0 || j1 + di >= m) || (j2 + dj < 0 || j2 + dj >= m)){
                                    ans = (int) Math.pow(-10, 9);
                                }else{
                                    ans = ans + dp[i + 1][j1 + di][j2 + dj];
                                }
                                maxi = Math.max(ans, maxi);
                            }
                        }
                        dp[i][j1][j2] = maxi;
                    }
                }
            }
        }
        return dp[0][0][m - 1];
    }

    public static int maximumChocolates(int r, int c, int[][] grid) {

        int dp[][][] = new int[r][c][c];
        for (int row1[][] : dp) {
            for (int row2[] : row1) {
                Arrays.fill(row2, -1);
            }
        }
        return rec(0 , 0 , c-1 , r , c , grid , dp);
    }
}
