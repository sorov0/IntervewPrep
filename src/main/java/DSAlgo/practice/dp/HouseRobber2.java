package DSAlgo.practice.dp;

import java.util.ArrayList;
import java.util.Arrays;

public class HouseRobber2 {

    //https://bit.ly/3F6q83P

    public static long solveSpaceOptimized(ArrayList<Integer> arr){
        int n = arr.size();
        long prev1 = arr.get(0);
        long prev2 = 0;
        for(int i = 0 ; i<n ; i++){
            long notPick = prev1;
            long pick = arr.get(i);
            if(i-2>=0) pick = arr.get(i) + prev2;
            long cur = Math.max(notPick, pick);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
    public static long solve(ArrayList<Integer> arr){
        int n = arr.size();
        long dp[] = new long[n];
        Arrays.fill(dp, 0);
        dp[0] = arr.get(0);
        for(int i = 1 ; i<n ; i++){
            long notPick = dp[i-1];
            long pick = arr.get(i);
            if(i-2>=0) pick = arr.get(i) + dp[i-2];
            dp[i] = Math.max(notPick, pick);
        }
        return dp[n-1];
    }


    public static long houseRobber(int[] valueInHouse) {
        // Write your code here.

        int n = valueInHouse.length;
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        if(n==1) return valueInHouse[0];
        for(int i = 0 ; i<valueInHouse.length ; i++){
            if(i!=0) arr1.add(valueInHouse[i]);
            if(i!=n-1) arr2.add(valueInHouse[i]);
        }
        long ans1 = solve(arr1);
        long ans2 = solve(arr2);

        return Math.max(ans1,ans2);

    }
}
