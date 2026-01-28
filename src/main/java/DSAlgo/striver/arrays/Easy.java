package DSAlgo.striver.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Easy {

    static int largestEle(ArrayList<Integer> arr){

        int maxi = Integer.MIN_VALUE;
        for( int i = 0 ; i<arr.size() ; i++ ){
            maxi = Math.max(maxi, arr.get(i));
        }
        return maxi;
    }

    // Assumption: The array has non-negative numbers
    //https://www.geeksforgeeks.org/problems/second-largest3735/1

    static int secondLargestEle(ArrayList<Integer> arr){

        // 2,4,1,5,2,7,6
        int slargest = -1;
        int largest = -1;

        for(int i = 0 ; i <arr.size() ; i++){

            if(arr.get(i) > largest){
                slargest = largest;
                largest = arr.get(i);
            }
            else if (arr.get(i) > slargest && arr.get(i) < largest) {
                slargest = arr.get(i);
            }
        }
        return slargest;
    }

    static int thirdLargestEle(ArrayList<Integer> arr){

        // 2,4,1,5,2,7,6
        int tlargest = -1;
        int slargest = -1;
        int largest = -1;

        for(int i = 1 ; i <arr.size() ; i++){
            if(arr.get(i) > largest){
                tlargest = slargest;
                slargest = largest;
                largest = arr.get(i);
            }
            if(arr.get(i) > slargest && arr.get(i) < largest){
                tlargest = slargest;
                slargest = arr.get(i);
            }
            else if (arr.get(i) > tlargest && arr.get(i) < largest && arr.get(i)<slargest) {
                tlargest = arr.get(i);
            }
        }
        return tlargest;
    }

    // Assumption: The array has non-negative numbers

    static int secondSmallestEle(ArrayList<Integer> arr){

        int ssmallest = Integer.MAX_VALUE;
        int smallest = arr.get(0);

        for(int i = 1 ; i <arr.size() ; i++){

            if(arr.get(i)<smallest){
                ssmallest = smallest;
                smallest = arr.get(i);
            }
            else if (arr.get(i) != smallest && arr.get(i) < ssmallest) {
                ssmallest = arr.get(i);
            }
        }
        return ssmallest;
    }

    //https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/

    static boolean checkIfArrayIsSorted(ArrayList<Integer> arr){

        for(int i = 1 ; i<arr.size() ; i++){
            if(arr.get(i-1) <= arr.get(i)){

            }else{
                return false;
            }
        }
        return true;
    }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
    // 1 2 2 3 3 3 4 5 6
    static int removeDuplicateFromSortedArray(ArrayList<Integer> arr){
        int i = 0;
        for(int j = 1 ; j<arr.size() ; j++){
            if(arr.get(i) != arr.get(j)){
                arr.set(i+1, arr.get(j));
                i++;
            }
        }
        return i+1;
    }


    static void reverse(ArrayList<Integer> arr, int start, int end){

        while(start<=end){
            Collections.swap(arr, start, end);
            start++;
            end--;
        }

    }

    // RotateArrayByKElement
    // https://leetcode.com/problems/rotate-array/description/
    static void leftRotateByKEle(ArrayList<Integer> arr, int k){

        k = k % arr.size();
        reverse(arr, 0, k-1);
        reverse(arr, k, arr.size()-1);
        Collections.reverse(arr);

    }

    public static void RotateToLeft(int[] arr, int n, int k) {

        if (n == 0) return;
        k = k % n;
        if (k > n) return;

        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }
        for (int i = 0; i < n - k; i++) {
            arr[i] = arr[i + k];
        }
        for (int i = n - k; i < n; i++) {
            arr[i] = temp[i - n + k];
        }
    }

    // Move All zeroes to the end of the Array
    // https://leetcode.com/problems/move-zeroes/description/
    static void moveZerosToEnd(ArrayList<Integer> arr){

        int j = -1;
        for(int i = 0 ; i<arr.size() ; i++){
            if(arr.get(i)==0){
                j = i;
                break;
            }
        }

        for(int i = j+1 ; j<arr.size() ; i++){
            if(arr.get(i) != 0){
                Collections.swap(arr, i, j);
                j++;
            }
        }
    }

    // https://www.geeksforgeeks.org/problems/union-of-two-sorted-arrays-1587115621/1

    static LinkedList<Integer> unionOfTwoSortedArray(ArrayList<Integer> a, ArrayList<Integer> b){

        LinkedList<Integer> union = new LinkedList<>();
        int i = 0;
        int j = 0;

        while(i<a.size() && j<b.size()){
            if(a.get(i) <= b.get(i)){
                if(union.size()==0 || union.getLast() != a.get(i)){
                    union.add(a.get(i));
                }
                i++;
            }else{
                if(union.size()==0 || union.getLast() != b.get(j)){
                    union.add(b.get(j));
                }
                j++;
            }
        }

        while(i<a.size()){
            if(union.size()==0 || union.getLast() != a.get(i)){
                union.add(a.get(i));
            }
            i++;
        }
        while(j<b.size()){
            if(union.size()==0 || union.getLast() != b.get(j)){
                union.add(b.get(j));
            }
            j++;
        }
        return union;
    }

    // Intersection of Two Sorted Array
    static ArrayList<Integer> intersectionOfTwoSortedArray(ArrayList<Integer> a, ArrayList<Integer> b){

        int i = 0;
        int j = 0;
        ArrayList<Integer> intersection = new ArrayList<>();
        while(i<a.size() && j<b.size()){
            if(a.get(i)<b.get(j)){
                i++;
            } else if (b.get(j)<a.get(i)) {
                j++;
            }else{
                intersection.add(a.get(i));
                i++;
                j++;
            }
        }
        return intersection;
    }

    // Maximum consecutive 1's
    // https://leetcode.com/problems/max-consecutive-ones/description/

    static int findMaxConsecutiveOnes(ArrayList<Integer> arr) {

        int maxCounter = 0;
        int counter = 0;
        for(int i = 0 ; i<arr.size() ; i++){
            if(arr.get(i)==1){
                counter++;
                maxCounter = Math.max(counter, maxCounter);
            }else{
                counter = 0;
            }
        }
        return maxCounter;
    }

    // StockBuyAndSell - Single day buy and single day sale
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    static int profit(ArrayList<Integer> arr){

        int mini = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i = 0 ; i < arr.size() ; i++){
            maxProfit = Math.max(maxProfit, arr.get(i) - mini);
            mini = Math.min(mini, arr.get(i));
        }
        return maxProfit;
    }






    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2,3,4,1,5,-4,6));
        System.out.println(largestEle(arr));

    }
}
