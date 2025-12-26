package DSAlgo.practice.binarysearch;

public class CountOccurenceInArray {

    //https://bit.ly/42ZDqM4
    // Number of occurrence

    static int lowerBound(int arr[], int n, int x){
        int l = 0;
        int h = n-1;
        int ans = 0;
        while(l<h){
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
    public static int count(int arr[], int n, int x) {
        //Your code goes here
        return lowerBound(arr , n , x);

    }
}
