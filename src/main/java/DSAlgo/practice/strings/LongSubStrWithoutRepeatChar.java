package DSAlgo.practice.strings;

import java.util.HashSet;
import java.util.Set;

public class LongSubStrWithoutRepeatChar {

    public int lengthOfLongestSubstring(String s) {

        if(s.length()==0) return 0;
        int l = 0;
        int ans = Integer.MIN_VALUE;
        Set<Character> st = new HashSet<>();
        for(int r = 0 ; r < s.length() ; r++){
            if(st.contains(s.charAt(r))){
                while(l<r && st.contains(s.charAt(r))){
                    st.remove(s.charAt(l));
                    l++;
                }
            }
            st.add(s.charAt(r));
            ans = Math.max(ans , r-l+1);
        }
        return ans;
    }

}
