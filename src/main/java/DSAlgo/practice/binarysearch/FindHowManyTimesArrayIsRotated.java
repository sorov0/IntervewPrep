package DSAlgo.practice.binarysearch;

public class FindHowManyTimesArrayIsRotated {

    //https://bit.ly/3pMzTCh

    public static int findKRotation(int []arr){
        // Write your code here.


        int l = 0 , h = arr.length-1;
        int mini = Integer.MAX_VALUE;
        int ind = -1;
        while(l<=h){
            int mid = (l+h)/2;
            if(arr[l]<=arr[mid]){
                if(arr[l]<mini){
                    ind = l;
                    mini = arr[l];
                }
                l = mid+1;
            }else{
                if(arr[mid]<mini){
                    ind = mid;
                    mini = arr[mid];
                }
                h = mid-1;
            }

        }
        return ind;
    }
}
