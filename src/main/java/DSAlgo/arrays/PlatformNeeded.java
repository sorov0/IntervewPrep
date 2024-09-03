package DSAlgo.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class PlatformNeeded {

    //https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1

    static int findPlatform(int arr[], int dep[], int n)
    {
        // add your code here
        Arrays.sort(arr);
        Arrays.sort(dep);
        int platform = 1;
        int ans = 0;

        int arrivalInd = 1;
        int departureInd = 0;
        while(arrivalInd<n && departureInd<n){
            if(arr[arrivalInd]<=dep[departureInd]){
                platform++;
                arrivalInd++;
            }
            else{
                platform--;
                departureInd--;
            }
            ans = Math.max(platform , ans);
        }
        return ans;

    }
}
