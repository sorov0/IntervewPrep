package DSAlgo.practice.binarysearch;

public class SearchInsertionPosition {

    //https://bit.ly/3ocI0HW
    //Search Insertion Position

    public static int searchInsert(int [] arr, int x){
        // Write your code here.

        int ans = arr.length;
        int l = 0 , h = arr.length-1;
        while(l<=h){
            int mid = (l+h)/2;
            if(arr[mid]>=x){
                ans = mid;
                h = mid-1;
            }else{
                l = mid+1;
            }

        }
        return ans;
    }
}
