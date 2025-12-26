package DSAlgo.practice.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

//
public class ChocolateDistribution {

    //https://www.geeksforgeeks.org/problems/chocolate-distribution-problem3825/1

    //Input: arr = [3, 4, 1, 9, 56, 7, 9, 12], m = 5
    //Output: 6

    public long findMinDiff1 (ArrayList<Integer> a, int n, int m)
    {
        // your code here
        long ans = Integer.MAX_VALUE;
        Collections.sort(a);
        for(int i = 0 ; i<n-m + 1 ; i++){
            ans = Math.min(ans, a.get(i+m-1) - a.get(i));
        }
        return ans;
    }

    public long findMinDiff (ArrayList<Integer> a, int n, int m)
    {
        // your code here
        Collections.sort(a);
        long ans = Integer.MAX_VALUE;
        for(int i = 0 ; i<n-m+1 ; i++){
            ans = Math.min(ans , a.get(i+m-1) - a.get(i) );
        }
        return ans;
    }


}
