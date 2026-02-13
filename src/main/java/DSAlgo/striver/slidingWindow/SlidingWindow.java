package DSAlgo.striver.slidingWindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindow {

    /*
    Pending: Note: These problems look like Two Pointer or Sliding window but it is not two pointer problem
    coz, Array has negative numbers as well.
    https://www.geeksforgeeks.org/dsa/longest-sub-array-sum-k/
    https://leetcode.com/problems/subarray-sum-equals-k/description/
     */


    // https://leetcode.com/problems/maximum-subarray/
    public int maxSumSubArray(int[] nums) {
        int curSum = nums[0];
        int totalSum = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i] > curSum + nums[i]){
                curSum = nums[i];
            }else{
                curSum = nums[i] + curSum;
            }
            totalSum = Math.max(curSum, totalSum);
        }
        return totalSum;
    }

    // Extension(Find the SubArray) to https://leetcode.com/problems/maximum-subarray/
    static int[] getSubArrayOfMaxSum(int arr[]){

        int curSum = arr[0];
        int totalSum = arr[0];

        int leftInd = 0;;
        int rightInd = 0;

        for(int right = 1 ; right<arr.length ; right++){

            if(arr[right] > curSum + arr[right]){
                leftInd = right;
                curSum = arr[right];
            }else{
                curSum = curSum + arr[right];
            }

            if(curSum > totalSum){
                rightInd = right;
                totalSum = curSum;
            }
        }
        return new int[]{leftInd, rightInd};
    }

    public int maxProductSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {

            // If current number is negative,
            // swap max and min because:
            // multiplying by a negative flips sign
            // so max could become min and min could become max
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);

            result = Math.max(result, max);
        }

        return result;
    }

    // https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/description/
    public int maxAbsoluteSum(int[] nums) {

        int curSum = nums[0];
        int totalMaxSum = nums[0];

        for(int i = 1 ; i<nums.length ; i++){
            if(nums[i]>curSum+nums[i]){
                curSum = nums[i];
            }else{
                curSum = curSum + nums[i];
            }
            totalMaxSum = Math.max(curSum, totalMaxSum);
        }

        curSum = nums[0];
        int totalMinSum = nums[0];

        for(int i = 1 ; i<nums.length ; i++){
            if(nums[i] < curSum + nums[i]){
                curSum = nums[i];
            }else{
                curSum = curSum + nums[i];
            }
            totalMinSum = Math.min(curSum, totalMinSum);
        }
        return Math.max(totalMaxSum, Math.abs(totalMinSum));
    }

    // https://www.geeksforgeeks.org/problems/subarray-with-given-sum-1587115621/1
    static ArrayList<Integer> subarraySum(int[] arr, int target) {
        int left = 0;
        int sum = 0;
        for(int right = 0 ; right < arr.length ; right++){
            sum = sum + arr[right];
            while(sum>target && left<right){
                sum = sum - arr[left];
                left++;
            }
            if(sum == target){
                return new ArrayList<>(Arrays.asList(left+1, right+1));
            }
        }
        return new ArrayList<>(Arrays.asList(-1));
    }

    // Problem 2.2 — Longest Subarray With Sum ≤ K
    //
    //Input: arr = [2, 5, 1, 1, 1, 7], k = 8
    //Output: 4 (subarray [5,1,1,1])
    // Applicable only when array has all positive numbers

    public int longestSubarraySumNotGreaterThanTarget(int[] arr, int target) {
        int left = 0;
        int sum = 0;
        int maxLength = 0;

        for(int right = 0 ; right< arr.length ; right++){

            sum = sum +arr[right];

            while(sum > target && left < right){
                sum = sum - arr[left];
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    // Maximum Consecutive Ones After Flipping Zeroes
    // MaximumSubArray length with all consecutive 1's after flipping at most k 0s
    // Input: arr[] = [1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1], k = 2
    // Output: 8
    int getMaxSubArrayLength(int arr[], int k){
        int left = 0;
        int cnt = 0;
        int maxLength = 0;

        for(int right = 0 ; right < arr.length ; right++){

            if(arr[right] == 0){
                cnt++;
            }
            while(cnt > 2){
                if(arr[left]==0) cnt--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    // Problem 1.1 — Maximum Sum Subarray of Size K
    //Input: arr = [2, 1, 5, 1, 3, 2], k = 3
    //Output: 9 (from subarray [5, 1, 3])
    public int maxSumSubarrayOfSizeK(int[] arr, int k) {
        int windowSum = 0;
        int maxSum = 0;

        // Build initial window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;

        // Slide the window
        for (int right = k; right < arr.length; right++) {
            windowSum += arr[right] - arr[right - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }


    // Problem 1.2 — First Negative Number in Every Window of Size K
    // Input: arr = [12,-1,-7,8,-15,30,16,28], k = 3
    // Output: [-1, -1, -7, -15, -15, 0]
    public List<Integer> firstNegativeInWindow(int[] arr, int k) {
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int right = 0 ; right < arr.length ; right++){
            if(arr[right] < 0){
                q.add(right);
            }
            if(right - left + 1 == k){
                if(!q.isEmpty()){
                    ans.add(q.peek());
                }else{
                    ans.add(0);
                }
            }
            if(!q.isEmpty() && q.peek() == left){
                q.poll();
            }
            left++;
        }
        return ans;
    }


    // 2. Minimum Window Substring (Substring containing all chars of another string)
    //    Given strings s and t, find the smallest substring of s that contains all characters of t.
    // Example:
    // S = "ADOBECODEBANC"
    // T = "ABC"
    // Answer = "BANC"

    public String minWindow(String s, String t) {

        // If s is smaller than t, no possible window
        if (s.length() < t.length()) return "";

        // This array will store how many times each character is needed
        int need[] = new int[128];

        // Fill the need array based on string t
        for (char c : t.toCharArray()) {
            need[c]++;  // e.g., for "ABC": need['A']=1, need['B']=1, need['C']=1
        }

        int left = 0;                       // left pointer of sliding window
        int minLength = Integer.MAX_VALUE;  // store minimum window size found
        int start = 0;                      // starting index of minimum window

        // Total characters from t that we still need to match
        int requiredLength = t.length();    // FIX: must be initialized

        // Expand the window using right pointer
        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);       // current character from s

            // If this character is still needed, we reduce the required count
            // (need[c] > 0 means this character is part of t and still needed)
            if (need[c] > 0) {
                requiredLength--;           // we matched one required character
            }

            // Decrease need[c] because this character is now in our window
            need[c]--;

            // If all required characters have been matched…
            while (requiredLength == 0) {

                // Check if this window is the smallest so far
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;           // save window start
                }

                // We will now shrink the window from the left
                char leftChar = s.charAt(left);

                // Before removing leftChar from the window, restore its need count
                need[leftChar]++;

                // If we now need this leftChar again, the window becomes invalid
                if (need[leftChar] > 0) {
                    requiredLength++;
                }

                left++;                     // shrink the window from the left
            }
        }

        // If no valid window was found, return empty string
        return minLength == Integer.MAX_VALUE
                ? ""
                : s.substring(start, start + minLength);
    }



    public static void main(String[] args) {

    }

}
