package DSAlgo.binarysearch;

public class BinarySearch {

    //https://bit.ly/41Tk8ql
    //Simple BinarySearch

    public static int findRec(int []nums ,int l ,int h ,int target){

        if(l>h) return -1;
        int mid = (l+h)/2;

        if(nums[mid]==target) return mid;

        else if(nums[mid]<target){
            return findRec(nums , mid+1 , h , target);
        }
        else{
            return findRec(nums , l , mid-1 , target);
        }
    }

    public static int binarySearch(int[] nums, int low, int high, int target) {

        if (low > high) return -1; //Base case.

        // Perform the steps:
        int mid = (low + high) / 2;

        if (nums[mid] == target) return mid;

        else if (target > nums[mid])
            return binarySearch(nums, mid + 1, high, target);

        return binarySearch(nums, low, mid - 1, target);
    }


    public static int search(int []nums, int target) {
        return findRec(nums , 0 , nums.length - 1 , target);
    }
}
