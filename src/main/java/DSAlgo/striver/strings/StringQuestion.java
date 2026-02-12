package DSAlgo.striver.strings;

import java.util.*;

public class StringQuestion {

    /*

    Variation 1: indexOf(String searchText) - return index of first char of searchText
    Variation 2: indexOf(String searchText, int fromIndex)
    Variation 3: indexOf(int character) - Search for a single character (ASCII/Unicode)
    Variation 4: indexOf(int character, int fromIndex)

    Variation 1: substring(int startIndex) - Extract till the end from the startIndex
    Variation 2: substring(int startIndex, int endIndex) - Extract from startIndex (inclusive) to endIndex (exclusive).
     */

    // https://leetcode.com/problems/valid-anagram/submissions/1915672213/
    public boolean isAnagram(String s, String t) {

        int cnt[] = new int[26];

        for(char c : s.toCharArray()){
            cnt[c - 'a']++;
        }

        for(char c : t.toCharArray()){
            cnt[c - 'a']--;
        }

        for(int i : cnt){
            if(i != 0) return false;
        }
        return true;

    }

    // https://leetcode.com/problems/group-anagrams/description/

    static String sortString(String str){
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> mp = new HashMap<>();

        for(int i = 0 ; i<strs.length ; i++){

            String sortedStr = sortString(strs[i]);
            if(!mp.containsKey(sortedStr)){
                mp.put(sortedStr, new ArrayList<>(Arrays.asList(strs[i])));
            }else{
                mp.get(sortedStr).add(strs[i]);
            }
        }
        return new ArrayList<>(mp.values());
    }

    // https://leetcode.com/problems/rotate-string/
    public boolean rotateString(String s, String goal) {

        if(s.length() != goal.length()) return false;

        return (s+s).contains(goal);
    }

    // https://www.geeksforgeeks.org/problems/check-if-string-is-rotated-by-two-places-1587115620/1

    // amazon, azonam

    // on + amaz
    // azon + am
    public static boolean isRotated(String s1, String s2) {
        // code here

        int n = s1.length();
        int m = s2.length();
        if(n!=m || n<=1 || m<=1) return false;

        String a = s1.substring(n-2) + s1.substring(0, n-2);
        String b = s1.substring(2) + s1.substring(0, 2);

        if(a.equals(s2) || b.equals(s2)) return true;
        return false;

    }

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1915719079/
    public int lengthOfLongestSubstringWithoutRepeatingChar(String s) {

        Set<Character> set = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        for(int right = 0 ; right<s.length() ; right++){

            while(set.contains(s.charAt(right)) && left<right){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;

        /*
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
         */
    }

    // https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1

    public int longestKSubstr(String s, int k) {

        int left = 0;
        int maxLength = -1;

        HashMap<Character, Integer> map = new HashMap<>();

        for(int right = 0 ; right<s.length() ; right++){

            char ch = s.charAt(right);

            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while(map.size() > k && left<right){
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }


            // update only if distinct == k
            if (map.size() == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;

    }

    //https://www.geeksforgeeks.org/problems/remove-duplicates3034/1

    String removeDups(String S) {
        // code here
        HashSet<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++){
            char curr = S.charAt(i);
            if (!set.contains(curr)){
                set.add(curr);
                sb.append(curr);
            }
        }
        return sb.toString();
    }

    public void reverseString(char[] s) {

        int length = s.length;

        int l = 0;
        int r = length-1;

        while(l<r){
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }

    public int firstUniqChar(String s) {

        Map<Character, Integer> mp = new LinkedHashMap<>();
        for(int i = 0 ; i<s.length() ; i++){
            mp.put(s.charAt(i), mp.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : mp.entrySet()){
            if(entry.getValue()==1){
                return s.indexOf(entry.getKey());  // return index ✔
            }
        }
        return -1;

    }

    // Compress string ("AAAAABBCCAA" → "5A2B2C2A")
    String compress(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        return sb.toString();
    }

    // Smallest window substring containing all characters - karna hai

    public static void main(String[] args) {

    }


}


