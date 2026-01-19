package DSAlgo.striver.slidingWindow;

public class InterviewProblem {

    /*

You are given a string s and an integer k.
You can choose any character of the string and change it to any other uppercase English character.
You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.
Input: s = "ABAB", k = 2
Output: 4
Input: s = "AABABBA", k = 1
Output: 4
*/

    public int characterReplacement(String s, int k) {
        int[] count = new int[26];  // To store frequencies of characters
        int maxCount = 0;           // Max frequency in current window
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            int i = count[s.charAt(right) - 'A'];
            i++;
            maxCount = Math.max(maxCount, i);

            // If number of characters to change > k, shrink window
            if ((right - left + 1) - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

}
