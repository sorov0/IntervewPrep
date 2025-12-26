package DSAlgo.practice.binarysearch;

public class SearchElementInRotatedSortedOrder2 {

    //https://bit.ly/3MCdLTY
    //Search In A Rotated Sorted Array II

    public static boolean searchInARotatedSortedArrayII(int []arr, int k) {
        // Write your code here.

        int l = 0 , h = arr.length-1;
        while(l<=h){
            int mid = (l+h)/2;
            if(arr[mid]==k){
                return true;
            }
            if(arr[l]==arr[mid] && arr[mid]==arr[h]){
                l++;
                h--;
                continue;
            }
            //if left half is sorted, Condition: [arr[l]<arr[mid]]
            if(arr[l]<=arr[mid]){
                if(arr[l]<=k && k<=arr[mid]){
                    h = mid-1;
                }else{
                    l = mid+1;
                }
            }
            //if right half is sorted
            else{
                if(arr[mid]<=k && k<=arr[h]){
                    l = mid+1;
                }else{
                    h = mid-1;
                }

            }
        }
        return false;
    }
}
