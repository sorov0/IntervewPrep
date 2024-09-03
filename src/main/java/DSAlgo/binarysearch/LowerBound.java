package DSAlgo.binarysearch;

public class LowerBound {

    //https://bit.ly/43967G5
    //Implement LowerBound
    // The lower bound is the smallest index, ind, where arr[ind] >= x.
    // But if any such index is not found, the lower bound algorithm returns n i.e. size of the given array.

    public static int lowerBound(int []arr, int n, int x) {
        // Write your code here

        int ans = n;
        int l = 0 , h = n-1;
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
