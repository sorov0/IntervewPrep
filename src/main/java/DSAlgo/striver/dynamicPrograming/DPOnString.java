package DSAlgo.striver.dynamicPrograming;

import java.util.List;

public class DPOnString {

    // https://leetcode.com/problems/longest-common-subsequence/description/
    public static int lcs(int i, int j, String text1, String text2, int dp[][]) {

        if(i<0 || j<0) return 0;
        if(text1.charAt(i) == text2.charAt(j)){
            return dp[i][j] = 1 + lcs(i-1, j-1, text1, text2, dp);
        }
        return dp[i][j] = Math.max(lcs(i-1, j, text1, text2, dp), lcs(i, j-1, text1, text2, dp));

    }

    // https://www.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1
    static void findLcs(String s1, String s2) {

        int n=s1.length();
        int m=s2.length();

        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            dp[i][0] = 0;
        }
        for(int i=0;i<=m;i++){
            dp[0][i] = 0;
        }

        for(int ind1=1;ind1<=n;ind1++){
            for(int ind2=1;ind2<=m;ind2++){
                if(s1.charAt(ind1-1)==s2.charAt(ind2-1))
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                else
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2],dp[ind1][ind2-1]);
            }
        }

        int len=dp[n][m];
        int i=n;
        int j=m;

        int index = len-1;
        String str="";
        for(int k=1; k<=len;k++){
            str +="$"; // dummy string
        }
        StringBuilder ss= new StringBuilder(s1);
        StringBuilder str2=new StringBuilder(str);
        while(i>0 && j>0){
            if(ss.charAt(i-1) == s2.charAt(j-1)){
                str2.setCharAt(index,ss.charAt(i-1) );
                index--;
                i--;
                j--;
            }
            else if(ss.charAt(i-1)>s2.charAt(j-1)){
                i--;
            }
            else j--;
        }
        System.out.println(str2);
    }

    // https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
    static int findLongCommonSubstring(String s1, String s2) {

        int n=s1.length();
        int m=s2.length();

        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            dp[i][0] = 0;
        }
        for(int i=0;i<=m;i++){
            dp[0][i] = 0;
        }
        int ans = 0;
        for(int ind1=1;ind1<=n;ind1++){
            for(int ind2=1;ind2<=m;ind2++){
                if(s1.charAt(ind1-1)==s2.charAt(ind2-1)){
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                    ans = Math.max(ans, dp[ind1][ind2]);
                }
                else dp[ind1][ind2] = 0;
            }
        }
        return ans;
    }

    // https://leetcode.com/problems/longest-palindromic-subsequence/
    static int longestPalindromeSubseq(String str) {

        return findLongCommonSubstring(str, new StringBuilder(str).reverse().toString());

    }

    // https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
    static int minInsertions(String s) {

        int length = s.length();
        int lonPalidSub = longestPalindromeSubseq(s);
        int ans  = length = lonPalidSub;
        return ans;
    }

    // https://leetcode.com/problems/delete-operation-for-two-strings/description/
    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int lcs = lcs(n-1, m-1, word1, word2, new int[n][m]);

        int ans = n + m - (2*lcs);
        return ans;
    }

    // https://leetcode.com/problems/edit-distance/
    static int minDistance(int i, int j, String word1, String word2, int dp[][]) {

        if(i<0) return j+1;
        if(j<0) return i+1;

        if(word1.charAt(i) == word2.charAt(i)){
            return 0 + minDistance(i-1, j-1, word1, word2, dp);
        }

        return 0;


    }

}
