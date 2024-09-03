package DSAlgo.dp;

import java.util.Arrays;

public class NinjaTraining {

    //

    static int solve(int day , int lastAct , int points[][] , int dp[][]){

        if(dp[day][lastAct] != -1) return dp[day][lastAct];

        if(day==0){
            int maxi = 0;
            for(int i = 0 ; i<=2 ; i++){
                if(i!=lastAct)
                    maxi = Math.max(maxi , points[0][i]);
            }
            return dp[day][lastAct] = maxi;
        }

        int maxi = 0;
        for(int i = 0 ; i<=2 ; i++){
            if(i!=lastAct){
                int actPoint = points[day][i] + solve(day-1 , i , points , dp);
                maxi = Math.max(maxi, actPoint);
            }
        }
        return dp[day][lastAct] = maxi;
    }

    static int tabulation(int n , int points[][] , int dp[][]){
        dp[0][0] = Math.max(points[0][1] , points[0][2]);
        dp[0][1] = Math.max(points[0][0] , points[0][2]);
        dp[0][2] = Math.max(points[0][0] , points[0][1]);

        dp[0][3] = Math.max(Math.max(points[0][1] , points[0][2]) , points[0][0]);
        for(int day = 1 ; day<n ; day++){
            for(int lastAct = 0 ; lastAct<3 ; lastAct++){
                dp[day][lastAct] = 0;
                for(int task = 0 ; task<=2 ; task++){
                    if(task!=lastAct){
                        int maxPoint = points[day][task] + dp[day-1][task];
                        dp[day][lastAct] = Math.max(dp[day][lastAct], maxPoint);
                    }
                }
            }
        }
        return dp[n-1][3];
    }

    public static int ninjaTraining(int n, int points[][]) {

        int dp[][] = new int[n][4];
        for (int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i], -1);
        }
        return solve(n - 1, 3, points, dp);
    }
}
