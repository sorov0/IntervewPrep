package DSAlgo.arrays;

public class KadaneAlgorithm {

    //https://www.geeksforgeeks.org/problems/kadanes-algorithm-1587115620/1

    long maxSubarraySum(int arr[], int n){

        // Your code here
        long csum = arr[0] , tsum = arr[0];

        for(int i = 1 ; i<n ; i++){
            if(arr[i]>csum+arr[i]){
                csum = arr[i];
            }else{
                csum = csum + arr[i];
            }
            tsum = Math.max(tsum , csum);
        }
        return tsum;
    }
}
