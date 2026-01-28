package DSAlgo.striver.dynamicPrograming;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DPOnSequence {


    // printAllSubSeq(arr, 0, subSeq)
    static void printAllSubSeq(ArrayList<Integer> arr, int ind, ArrayList<Integer> subSeq) {
        if (ind == arr.size()) {
            System.out.println(subSeq);
            return;
        } else {
            subSeq.add(arr.get(ind));
            printAllSubSeq(arr, ind + 1, subSeq);
            subSeq.remove(subSeq.size() - 1);
            printAllSubSeq(arr, ind + 1, subSeq);
        }
    }

    static int cntSubSeq(ArrayList<Integer> arr, int ind) {
        if (ind == arr.size()) return 0;
        return 1 + cntSubSeq(arr, ind + 1) + cntSubSeq(arr, ind + 1);
    }

    // it prints all subsequence with given sum
    static void sumSubSeq(ArrayList<Integer> arr , ArrayList<Integer> sub , int ind, int gsum , int sum){

        if(ind == arr.size()){
            if(gsum == sum){
                System.out.println(sub);
            }
            return;
        }else{
            sum = sum + arr.get(ind);
            sub.add(arr.get(ind));
            sumSubSeq(arr, sub, ind+1, gsum, sum);

            sum = sum - arr.get(ind);
            sub.remove(sub.size()-1);
            sumSubSeq(arr , sub , ind+1 , gsum , sum);
        }
    }

    //it prints only one subsequence with given sum
    static boolean sumSubSeq2(ArrayList<Integer> arr , ArrayList<Integer> sub , int ind, int gsum , int sum){
        if(ind==arr.size()){
            if(gsum==sum){
                System.out.println(sub);
                return true;
            }
            return false;
        }
        sub.add(arr.get(ind));
        sum+=arr.get(ind);
        if(sumSubSeq2(arr , sub , ind+1 , gsum , sum)) return true;
        sum-=arr.get(ind);
        sub.remove(sub.size()-1);
        if(sumSubSeq2(arr , sub , ind+1 , gsum , sum)) return true;
        return false;
    }

    // It prints number of different subsequence with given sum
    static int sumSubSeq3(ArrayList<Integer> arr , ArrayList<Integer> sub , int ind, int gsum , int sum){
        if(ind==arr.size()){
            if(gsum==sum){
                return 1;
            }
            return 0;
        }
        sub.add(arr.get(ind));
        sum+=arr.get(ind);
        int l = sumSubSeq3(arr , sub , ind+1 , gsum , sum);
        sum-=arr.get(ind);
        sub.remove(sub.size()-1);
        int r = sumSubSeq3(arr , sub , ind+1 , gsum , sum);
        return l+r;
    }

    // https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
    //isSubsetSum(arr, n-1, target, dp);
    static boolean isSubsetSum(int arr[], int ind, int target, int dp[][]) {

        if( target == 0) return true;
        if(ind == 0) return (arr[0] == target);

        if(dp[ind][target] != -1) return dp[ind][target] == 0 ? false : true;
        boolean notTake = isSubsetSum(arr, ind-1, target, dp);

        // Try taking the current element if it doesn't exceed the target
        boolean take = false;
        if(arr[ind] <= target) take = isSubsetSum(arr, ind-1, target - arr[ind], dp);

        dp[ind][target] = notTake || take ? 1 : 0;
        return notTake || take;

    }

    // https://leetcode.com/problems/partition-equal-subset-sum/description/
    public static boolean canPartition(int[] arr, int n) {
        // Write your code here.

        int sum = 0;
        for(int i = 0 ; i<n ; i++){
            sum = sum + arr[i];
        }
        if(sum%2==1) return false;
        int k = sum/2;

        int dp[][] = new int[n][k+1];
        for (int i = 0 ; i<n ; i++){
            Arrays.fill(dp[i], -1);
        }
        return isSubsetSum(arr, 0, k , dp);

    }

    // https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1
    // Count Subsets with Sum K (DP - 17)
    static int rec(int i , int k , int arr[] , int dp[][]){

        if(k==0) return 1;
        if(i==0) {
            return arr[0] == k ? 1 : 0;
        }
        if(dp[i][k] != -1) return dp[i][k];
        int notPick = rec(i-1 , k , arr , dp);
        int pick = 0;
        if(k-arr[i]>=0) pick = rec(i-1 , k-arr[i] , arr , dp);
        return dp[i][k] = notPick + pick;
    }

    // printSubsetWithSumK(0, k, arr, dp, sub)
    // Print all valid subsets (no memoization here required to avoid skipping paths)
    static void printSubsets(int i, int k, int[] arr, ArrayList<Integer> current) {
        if (k == 0) {
            System.out.println(current);
            return;
        }
        if (i < 0 || k < 0) return;

        // Not pick current element
        printSubsets(i - 1, k, arr, current);

        // Pick current element
        current.add(arr[i]);
        printSubsets(i - 1, k - arr[i], arr, current);
    }

    // https://leetcode.com/problems/coin-change-ii/description/
    // coinChange(n-1, T, nums, dp);
    static long countWaysToMakeChange(int ind , int T , int nums[] , long dp[][]){
        if(ind == 0){
            if(T % nums[ind] == 0) return 1;
            else return 0;
        }
        if(dp[ind][T] != -1) return dp[ind][T];
        long notPick = countWaysToMakeChange(ind-1 , T , nums , dp);
        long pick = 0;
        if(nums[ind] <= T) pick = countWaysToMakeChange(ind , T - nums[ind] , nums , dp);
        return dp[ind][T] = notPick + pick;
    }

    // https://leetcode.com/problems/coin-change/description/
    static int minimumElementsUtil(int[] arr, int ind, int T, int[][] dp) {

        if(ind == 0){
            if(T % arr[0] == 0){
                return T / arr[0];
            }else{
                return Integer.MAX_VALUE;
            }
        }
        if (dp[ind][T] != -1)
            return dp[ind][T];

        int notTaken = 0 + minimumElementsUtil(arr, ind-1, T, dp);
        int taken = Integer.MAX_VALUE;
        if(T>=arr[ind]) taken = 1 + minimumElementsUtil(arr, ind, T - arr[ind], dp);

        return dp[ind][T] = Math.min(notTaken, taken);
    }

    //  https://www.naukri.com/code360/problems/1072980 (0/1 Knapsack)
    // one item can be picked only once
    static int knapSack1(int ind, int val[], int wt[], int capacity, int dp[][]) {
        if(ind == 0){
            if(wt[ind]<=capacity) return val[ind];
            return 0;
        }
        if(dp[ind][capacity] !=1 ) return dp[ind][capacity];
        int notTake = knapSack1(ind-1 , val, wt, capacity, dp);
        int take = 0;
        if(wt[ind]<=capacity) take = knapSack1(ind-1, val, wt, capacity - wt[ind], dp);
        return dp[ind][capacity] = Math.max(notTake, take);
    }

    // https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
    // ine item can be picked multiple times

    static int knapSack(int ind, int val[], int wt[], int capacity, int dp[][]) {

        if(ind == 0){
           return (capacity/wt[ind]) * val[ind];
        }
        if(dp[ind][capacity] != -1) return dp[ind][capacity];
        int notTake = knapSack(ind-1, val, wt, capacity, dp);

        int take = 0;
        if(capacity>=wt[ind]) take = val[ind] + knapSack(ind, val, wt, capacity - wt[ind], dp);
        return dp[ind][capacity] = Math.max(take, notTake);

    }



    // https://www.geeksforgeeks.org/problems/rod-cutting0840/1
    public static int cutRod(int ind, int N, int[] price, int dp[][]) {

        if(ind == 0){
            return N * price[0];
        }

        if(dp[ind][N] != -1) return dp[ind][N];
        int notTake = 0 + cutRod(ind-1, N, price, dp);
        int take = 0;
        int rodLength = ind+1;
        if(rodLength <= N){
            take = price[ind] + cutRod(ind, N - rodLength, price, dp);
        }

        return dp[ind][N] = Math.max(take, notTake);
    }


}
