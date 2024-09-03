package DSAlgo.binarysearch;

public class MinimumRotatedSortedArray {

    //https://bit.ly/41My2dR

    public static int findMin(int []arr) {
        // Write your code here.

        int l = 0 , h = arr.length-1;
        int mini = Integer.MAX_VALUE;
        while(l<=h){
            int mid = (l+h)/2;

            if(arr[l]<=arr[mid]){
                mini = Math.min(mini , arr[l]);
                l = mid+1;
            }else{
                mini = Math.min( mini , arr[mid]);
                h = mid-1;
            }
        }
        return mini;


    }
}
