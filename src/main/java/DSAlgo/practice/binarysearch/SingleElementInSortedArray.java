package DSAlgo.practice.binarysearch;

import java.util.ArrayList;

public class SingleElementInSortedArray {

    //https://bit.ly/42KKHj5

    //

    public static int singleNonDuplicate(ArrayList<Integer> arr)
    {
        //    Write your code here.
        int n = arr.size();
        if(n==1) return arr.get(0);
        if(arr.get(0)!=arr.get(1)) return arr.get(0);
        if(arr.get(n-2)!=arr.get(n-1)) return arr.get(n-1);

        int l = 1 , h = n-2;
        while(l<=h){
            int mid = (l+h)/2;
            if(arr.get(mid-1)!=arr.get(mid) && arr.get(mid)!=arr.get(mid+1)){
                return arr.get(mid);
            }
            if((mid%2==1 && arr.get(mid-1)==arr.get(mid))
                    || (mid%2==0 && arr.get(mid)==arr.get(mid+1))){

                l = mid+1;
            }else{
                h = mid-1;
            }
        }
        return -1;
    }
}
