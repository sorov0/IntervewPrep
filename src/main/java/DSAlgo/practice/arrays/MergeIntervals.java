package DSAlgo.practice.arrays;

import java.util.*;

public class MergeIntervals {

    //https://leetcode.com/problems/merge-intervals/description/

    public int[][] merge(int[][] intervals) {

        //Arrays.sort(intervals , (a,b) ->  a[0] - b[0] );

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        LinkedList<int[]> ans = new LinkedList<int[]>();

        for(int[] interval : intervals){

            if(ans.isEmpty() || ans.getLast()[1] < interval[0]){
                ans.add(interval);
            }else{
                ans.getLast()[1] = Math.max(ans.getLast()[1] , interval[1]);
            }
        }

        return ans.toArray(new int[ans.size()][]);

    }
}
