package DSAlgo.arrays;

public class EquillibriumPoint {

    //https://www.geeksforgeeks.org/problems/equilibrium-point-1587115620/1

    public static int equilibriumPoint(long arr[], int n) {

        // Your code here

        for(int i = 1 ; i<n ; i++){
            arr[i] = arr[i] + arr[i-1];
        }
        if(n==1) return 1;
        if(n==2) return -1;

        for(int i = 1 ; i<n-1 ; i++){
            long lsum = arr[i-1];
            long rsum = arr[n-1] - arr[i];
            if(lsum==rsum){
                return i+1;
            }
        }
        return -1;
    }
}
