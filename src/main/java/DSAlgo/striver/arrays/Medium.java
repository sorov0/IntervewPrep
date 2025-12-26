package DSAlgo.striver.arrays;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Medium {

    // Longest SubArray Sum (Array contains positive numbers and zeros)
    // https://www.naukri.com/code360/problems/longest-subarray-with-sum-k_6682399

    public static int longestSubarrayWithSumK(int []a, long k) {

        int n = a.length; // size of the array.
        int left = 0, right = 0; // 2 pointers
        long sum = a[0];
        int maxLen = 0;

        while (right < n) {
            // if sum > k, reduce the subarray from left until sum becomes less or equal to k:
            while (left < right && sum > k) {
                sum -= a[left];
                left++;
            }

            // if sum = k, update the maxLen i.e. answer:
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Move forward thw right pointer:
            right++;
            if (right < n) sum += a[right];
        }

        return maxLen;
    }

    // Longest SubArray Sum (Array contains positive numbers and zeros and negative numbers)
    public static int getLongestSubarrayWithNegativeNumbers(int []a, long k) {

        int n = a.length; // size of the array.

        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i:
            sum += a[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            long rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }

    // 2 sum problem, return yes or no
    // https://leetcode.com/problems/two-sum/

    static boolean twoSum(ArrayList<Integer> arr, int target){

        Collections.sort(arr);
        int left = 0;
        int right = arr.size()-1;

        while(left < right){
            int sum = arr.get(left) + arr.get(right);
            if(sum == target){
                return true;
            }
            if(sum>target){
                right--;
            }else{
                left++;
            }
        }
        return false;
    }

    // 2 sum problem, return the left and right index
    // https://leetcode.com/problems/two-sum/

    public static int[] twoSum2(int[] nums, int target) {

        Map<Integer , Integer> mp = new HashMap<>();
        for(int i = 0 ; i<nums.length ; i++){
            mp.put(nums[i] , i);
        }
        for(int i = 0 ; i<nums.length ; i++){
            if (mp.containsKey(target - nums[i]) && mp.get(target - nums[i]) != i) {
                return new int[] { i, mp.get(target - nums[i]) };
            }
        }
        return null;
    }

    // Sort Arrays of 0, 1 and 2
    // //https://www.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s4231/1
    public static void sort012(int a[], int n)
    {
        int left = 0;
        int  mid = 0;
        int right = a.length;

        while(mid <= right){
            if(a[mid] == 0){
                a[mid] = a[left];
                a[left] = 0;
                left++;
                right++;
            }
            else if (a[mid] == 1) {
                mid++;
            }
            else{
                a[mid] = a[right];
                a[right] = 2;
                right--;
            }
        }
    }

    // Majority Element - Moores Voting algorithm
    // https://leetcode.com/problems/majority-element/

    int majorityEle(ArrayList<Integer> arr){

        int count = 0;
        int ele = 0;
        for(int i = 0 ; i<arr.size() ; i++){
            if(count == 0){
                count = 1;
                ele = arr.get(i);
            }
            else if(ele == arr.get(i)) count++;
            else count--;
        }
        int finalCount = 0;
        for(int i = 0 ; i<arr.size() ; i++){
            if(ele == arr.get(i)){
                finalCount++;
            }
        }
        if(finalCount > arr.size()/2) return ele;
        else return -1;

    }

    // Maximum SubArraySum - kadanes Algorithm
    // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4};
    // https://leetcode.com/problems/maximum-subarray/
    int maxSubArraySum(ArrayList<Integer> arr){

        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0 ; i<arr.size() ; i++){
            sum = sum + arr.get(i);
            if(sum > maxi){
                maxi = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return maxi;
    }

    // Maximum SubArraySum - kadanes Algorithm, print the subarray
    // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4};
    // https://leetcode.com/problems/maximum-subarray/
    Pair<Integer, Integer> maxSubArraySumPrint(ArrayList<Integer> arr){

        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        int start = -1;
        int ansStart = -1;
        int ansEnd = -1;
        for(int i = 0 ; i<arr.size() ; i++){
            if(sum == 0) start = i;
            sum = sum + arr.get(i);
            if(sum > maxi){
                maxi = sum;
                ansStart = start;
                ansEnd = i;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return new Pair<>(ansStart, ansEnd);
    }






    public static void main(String[] args) {

    }
}
