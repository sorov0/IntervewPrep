package DSAlgo.arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    //https://leetcode.com/problems/two-sum/description/

    public static int[] twoSum(int[] nums, int target) {
        int l = nums.length;
        int ans[] = new int[2];
        for(int i = 0 ; i<l-2 ; i++){
            for(int j = i+1 ; j<l-1 ; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i , j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {

        Map<Integer , Integer> mp = new HashMap<>();
        for(int i = 0 ; i<nums.length ; i++){
            mp.put(nums[i] , i);
        }
        for(int i = 0 ; i<nums.length ; i++){
            if (mp.containsKey(target - nums[i]) && mp.get(target - nums[i]) != i) {
                return new int[] { i, mp.get(target - nums[i]) };
            }
        }
        return null;
    }

    public static void main(String[] args){
        int nums[] = new int[]{1 ,2, 4 ,5};
        twoSum(nums , 4);
    }
}
