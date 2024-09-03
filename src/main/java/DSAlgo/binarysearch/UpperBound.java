package DSAlgo.binarysearch;

public class UpperBound {

    //https://bit.ly/3IoKGce
    //Implement Upper bound

    //The upper bound is the smallest index, ind, where arr[ind] > x.
    //But if any such index is not found, the upper bound algorithm returns n i.e. size of the given array.
    // The main difference between the lower and upper bound is in the condition.
    // For the lower bound the condition was arr[ind] >= x and here, in the case of the upper bound, it is arr[ind] > x.

    public static int upperBound(int []arr, int x, int n){
        // Write your code here.

        int ans = n;
        int l = 0 , h = n-1;
        while(l<=h){
            int mid = (l+h)/2;
            if(arr[mid]>x){
                ans = mid;
                h = mid-1;
            }else{
                l = mid+1;
            }

        }
        return ans;
    }
}
