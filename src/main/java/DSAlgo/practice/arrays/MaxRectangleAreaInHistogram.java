package DSAlgo.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class MaxRectangleAreaInHistogram {

    //https://leetcode.com/problems/largest-rectangle-in-histogram/description/

    public int largestRectangleArea(int[] heights) {

        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> leftSmall = new ArrayList<>();
        ArrayList<Integer> rightSmall = new ArrayList<>();
        int n = heights.length;

        for(int i = 0 ; i<n ; i++){
            while(!st.empty() && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            if(!st.empty()){
                leftSmall.add(st.peek() + 1);
            }else{
                leftSmall.add(0);
            }
            st.push(i);
        }

        while(!st.empty()) st.pop();

        for(int i = n-1 ; i>=0 ; i--){
            while(!st.empty() && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            if(!st.empty()){
                rightSmall.add(st.peek() - 1);
            }else{
                rightSmall.add(n-1);
            }
            st.push(i);
        }

        Collections.reverse(rightSmall);
        int ans = 0;
        for(int i = 0 ; i<n ; i++){
            ans = Math.max(ans , (heights[i] * (rightSmall.get(i) - leftSmall.get(i) + 1)));
        }
        return ans;
    }

    public static void main(String[] args){
        ArrayList newArr = new ArrayList(Arrays.asList(1 ,2, 3));
        int[] newArr1 = new int[]{2,1,5,6,2,3,1};
    }

}
