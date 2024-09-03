package DSAlgo.arrays;


import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort_ArrayList {

    private static void mergeArray(ArrayList<Integer> left, ArrayList<Integer> right, ArrayList<Integer> nums) {
        int i = 0;
        int j = 0;
        int k = 0;
        int n = left.size();
        int m = right.size();
        while(i<n && j<m){
            if(left.get(i)<right.get(j)){
                nums.set(k , left.get(i));
                i++;
            }else{
                nums.set(k , right.get(j));
                j++;
            }
            k++;
        }
        while(i<n){
            nums.set(k , left.get(i));
            i++;
            k++;
        }
        while(j<m){
            nums.set(k , right.get(j));
            j++;
            k++;
        }

    }

    public static void mergeSort(ArrayList<Integer> nums){

        if(nums.size() <= 1){
            return;
        }

        // 1 2 3 4 -> mid = (4-1)/2 -> 3/2 -> mid = 1 thus left: 1 2 and right: 3 4
        // 1 2 3 4 5 -> mid = (5-1)/2 -> 4/2 -> mid = 2 thus left: 1 2 3 and right: 4 5
        // mid is always calculated by subtracting 1 from size, and thus we get mid as index which divides the array into two half

        int mid = (nums.size()-1)/2;

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for(int i = 0 ;i<=mid ; i++){
            left.add(nums.get(i));
        }
        for(int i = mid+1 ; i<=nums.size()-1 ; i++){
            right.add(nums.get(i));
        }

        mergeSort(left);
        mergeSort(right);

        mergeArray(left , right , nums);

    }

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2,3,5,1,3));
        mergeSort(arr);
        System.out.print(arr);

    }

}
