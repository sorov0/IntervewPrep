package DSAlgo.practice.binarysearch;

import java.util.ArrayList;

public class SearchElementInRotatedSortedArray {

    //https://bit.ly/3OmIp5d

    //Search In Rotated Sorted Array

    public static int search(ArrayList<Integer> arr, int n, int k) {

        int l = 0 , h = arr.size()-1;
        while(l<=h){
            int mid = (l+h)/2;
            if(arr.get(mid)==k){
                return mid;
            }
            //if left half is sorted, Condition: [arr[l]<arr[mid]]
            else if(arr.get(l)<=arr.get(mid)){
                if(arr.get(l)<=k && k<=arr.get(mid)){
                    h = mid-1;
                }else{
                    l = mid+1;
                }
            }
            //if right half is sorted
            else{
                if(arr.get(mid)<=k && k<=arr.get(h)){
                    l = mid+1;
                }else{
                    h = mid-1;
                }

            }
        }
        return -1;


    }
}
