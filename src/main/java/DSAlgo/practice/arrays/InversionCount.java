package DSAlgo.practice.arrays;

import java.util.ArrayList;

public class InversionCount {

   // https://www.naukri.com/code360/problems/count-inversions_615

    static long cnt = 0;
    private static void mergeArray(ArrayList<Long> left, ArrayList<Long> right, ArrayList<Long> nums) {
        int i = 0;
        int j = 0;
        int k = 0;
        int n = left.size();
        int m = right.size();
        while (i < n && j < m) {
            if (left.get(i) < right.get(j)) {
                nums.set(k, left.get(i));
                i++;
            } else {
                nums.set(k, right.get(j));
                cnt = cnt + (n-i);
                j++;
            }
            k++;
        }
        while (i < n) {
            nums.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < m) {
            nums.set(k, right.get(j));
            j++;
            k++;
        }
    }

    public static void mergeSort(ArrayList<Long> nums){

        if(nums.size() <= 1){
            return;
        }

        // 1 2 3 4 -> mid = (4-1)/2 -> 3/2 -> mid = 1 thus left: 1 2 and right: 3 4
        // 1 2 3 4 5 -> mid = (5-1)/2 -> 4/2 -> mid = 2 thus left: 1 2 3 and right: 4 5
        // mid is always calculated by subtracting 1 from size, and thus we get mid as index which divides the array into two half

        int mid = (nums.size()-1)/2;

        ArrayList<Long> left = new ArrayList<>();
        ArrayList<Long> right = new ArrayList<>();

        for(int i = 0 ;i<=mid ; i++){
            left.add(nums.get(i));
        }
        for(int i = mid+1 ; i<=nums.size()-1 ; i++){
            right.add(nums.get(i));
        }

        mergeSort(left );
        mergeSort(right );

        mergeArray(left , right , nums );

    }

    public static long getInversions(long arr[], int n) {
        // Write your code here.
        ArrayList<Long> number = new ArrayList<>();

        for(int i = 0 ; i<n ; i++) number.add(arr[i]);
        mergeSort(number);
        return cnt;

    }

}
