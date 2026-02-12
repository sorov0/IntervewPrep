package company.epam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // ✔️ PATTERN 1 — Fixed-Size Sliding Window
    // Window size = k (constant).
    // Used when window size is known (e.g., “size K subarray”, “first / maximum / count in every window of size K”).

    // Problem 1.1 — Maximum Sum Subarray of Size K
    //Input: arr = [2, 1, 5, 1, 3, 2], k = 3
    //Output: 9 (from subarray [5, 1, 3])

    public int maxSumSubarray(int[] arr, int k) {

        int windowSum = 0;
        int maxSum = 0;

        for(int i = 0 ; i<arr.length ; i++){

            windowSum += arr[i];

            if(i >= k-1){
                maxSum = Math.max(windowSum, maxSum);
                windowSum = windowSum - arr[i - k + 1];
            }
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

    // ✔️ PATTERN 2 — Variable-Size Sliding Window (Longest Substring/Subarray Meeting a Condition)
    // Very common for interview questions like:
    //longest substring with X condition
    //longest subarray with sum ≤ target
    //longest substring with <= k distinct chars
    //Window grows until invalid → shrink until valid again.

    // 2.1. Longest Substring Without Repeating Characters
    //str = "bacda"
    public int lengthOfLongestSubstring(String s) {

        int len = s.length();
        int left = 0;
        int maxLen = 0;
        int freq[] = new int[256];

        for(int right = 0 ; right < len ; right++){
            char c = s.charAt(right);
            freq[c]++;

            while(freq[c] > 1){
                freq[s.charAt(left)]--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);

        }
        return maxLen;



    }

    // Problem 2.2 — Longest Subarray With Sum ≤ K
    //
    //Input: arr = [2, 5, 1, 1, 1, 7], k = 8
    //Output: 4 (subarray [5,1,1,1])

    public int longestSubarraySumAtMostK(int[] arr, int k) {
        int start = 0, sum = 0, maxLen = 0;

        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];

            while (sum > k) {
                sum -= arr[start];
                start++;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }


    // Maximum Consecutive Ones After Flipping Zeroes
    // MaximumSubArray length with all consecutive 1's after flipping at most k 0s
    // Input: arr[] = [1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1], k = 2
    // Output: 8
    int getMaxSubArrayLength(int arr[], int k){

        int left = 0;
        int cntZero = 0;
        int ans = 0;

        for(int right = 0 ; right < arr.length ; right++){

            if(arr[right] == 0){
                cntZero++;
            }

            while(cntZero > k){
                if(arr[left] == 0) cntZero--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);

        }
        return ans;
    }

    // MaximumSubArray length with all consecutive 1's after flipping at most 2 0s
    // Input: arr[] = [1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1]
    // Output: 8
    int getMaxSubArrayLengthWithKConstant(int arr[]){

        int left = 0;
        int cntZero = 0;
        int ans = 0;

        for(int right = 0 ; right < arr.length ; right++){

            if(arr[right] == 0){
                cntZero++;
            }

            while(cntZero > 2){
                if(arr[left] == 0) cntZero--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);

        }
        return ans;
    }


    // Maximum subarray sum or Kadane Algo
    public int maximumSubArraySum(int[] arr){

        int currSum = arr[0];
        int maxSum = arr[0];

        for(int i = 1 ; i<arr.length ; i++){
            if(arr[i] > currSum + arr[i]){
                currSum = arr[i];
            }else{
                currSum = currSum + arr[i];
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    // Maximum Sum Subarray of Size K
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
