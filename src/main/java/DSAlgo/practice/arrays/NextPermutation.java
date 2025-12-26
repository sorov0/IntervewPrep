package DSAlgo.practice.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class NextPermutation {

    //https://leetcode.com/problems/next-permutation/description/

    //  {2,1,5,4,3,0,0} -> 2,3,5,4,1,0,0 -> 2,3,0,0,1,4,5
    //  {2,3,0,0,1,4,5}
    //  2,5,6,4,3,0,0 -> 2,6,5,4,3,0,0 -> 2,6,0,0,3,4,5

    public void nextPermutation(int[] nums) {

        /*
        //This part is for learning purpose

        Integer[] arr = {1, 2, 3, 4, 5};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(arr));
        Integer[] convertedArray = (Integer[]) list.toArray();

        //End of the part
         */
        List<Integer> number = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int ind = -1;
        int n = number.size();
        for(int i = n-2 ; i>=0 ; i--){
            if(number.get(i)<number.get(i+1)){
                ind = i;
                break;
            }
        }
        if(ind == -1){
            Collections.reverse(number);
        }else{
            for(int i = n-1 ; i>ind ; i--){
                if(number.get(i)>number.get(ind)){
                    Collections.swap(number , i , ind);
                    break;
                }
            }
            Collections.reverse(number.subList(ind+1 , n));
        }
        int ans[] = number.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        for(int i = 0 ; i<n ; i++){
            nums[i] = ans[i];
        }
    }


    public static void main(String args[]) {

    }
}
