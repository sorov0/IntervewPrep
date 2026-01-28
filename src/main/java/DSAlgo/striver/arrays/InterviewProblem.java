package DSAlgo.striver.arrays;


import java.util.*;
import java.util.stream.Collectors;

public class InterviewProblem {


    // https://leetcode.com/problems/next-permutation/description/
    // Input: nums = [1,2,3]
    // Output: [1,3,2]
    //  {2,1,5,4,3,0,0} -> 2,3,5,4,1,0,0 -> 2,3,0,0,1,4,5
    //  {2,3,0,0,1,4,5}
    //  2,5,6,4,3,0,0 -> 2,6,5,4,3,0,0 -> 2,6,0,0,3,4,5
    static void nextPermutation(int[] nums) {

        //  {2,1,5,4,3,0,0}
        List<Integer> number = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        int ind = -1;
        int size = number.size();
        for(int i = size - 2 ; i>=0 ; i--){
            if(number.get(i) < number.get(i+1)){
                ind = i;
                break;
            }
        }
        if(ind == -1){
            Collections.reverse(number);
        }else{
            for(int i = size - 1 ; i>ind ; i--){
                if(number.get(i) > number.get(ind)){
                    Collections.swap(number, i, ind);
                    break;
                }
            }
            Collections.reverse(number.subList(ind+1 , size));
        }
        int ans[] = number.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        nums = Arrays.copyOf(ans , size);

    }

    // 3Sum problem
    // https://leetcode.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int size = nums.length;

        for(int i = 0 ; i<size ; i++){
            if(i > 0 && nums[i] == nums[i+1]) continue;
            int j = i+1;
            int k = size - 1;

            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];

                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    //skip the duplicates:
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
                else if(sum > 0){
                    k--;
                }else{
                    j++;
                }
            }
        }

        return res;

    }
    // Closest Sum to Target
    static int closestSumToTarget(int arr[], int target){

        int n = arr.length;
        Arrays.sort(arr);
        int closestSum = arr[0] + arr[1] + arr[2]; // Initial best guess
        for(int i = 0 ; i < n-1 ; i++){
            int j = 1+1;
            int k = n-1;
            while(j<k){

                int currSum = arr[i] + arr[j] + arr[k];

                if(Math.abs(currSum - target) < Math.abs(closestSum - target)){
                    closestSum = currSum;
                }
                if(currSum < target){
                    j++;
                }else if(currSum > target){
                    k--;
                }else{
                    return currSum;
                }
            }
        }
        return closestSum;
    }

    // Maximum SubArraySum - kadanes Algorithm
    // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4};
    // https://leetcode.com/problems/maximum-subarray/
    int maxSubArraySum(ArrayList<Integer> arr){

        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0 ; i<arr.size() ; i++){
            sum = sum + arr.get(i);
            if(sum > maxi){
                maxi = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return maxi;
    }

    // Maximum SubArraySum - kadanes Algorithm, print the subarray
    // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4};
    // https://leetcode.com/problems/maximum-subarray/
/*    Pair<Integer, Integer> maxSubArraySumPrint(ArrayList<Integer> arr){

        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        int start = -1;
        int ansStart = -1;
        int ansEnd = -1;
        for(int i = 0 ; i<arr.size() ; i++){
            if(sum == 0) start = i;
            sum = sum + arr.get(i);
            if(sum > maxi){
                maxi = sum;
                ansStart = start;
                ansEnd = i;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return new Pair<>(ansStart, ansEnd);
    }*/

    // Majority Element - Moores Voting algorithm
    // https://leetcode.com/problems/majority-element/
    int majorityEle(ArrayList<Integer> arr){

        int count = 0;
        int ele = 0;
        for(int i = 0 ; i<arr.size() ; i++){
            if(count == 0){
                count = 1;
                ele = arr.get(i);
            }
            else if(ele == arr.get(i)) count++;
            else count--;
        }
        int finalCount = 0;
        for(int i = 0 ; i<arr.size() ; i++){
            if(ele == arr.get(i)){
                finalCount++;
            }
        }
        if(finalCount > arr.size()/2) return ele;
        else return -1;

    }

    // Maximum Product Subarray
    // https://leetcode.com/problems/maximum-product-subarray/
    static int maxProduct(int[] nums) {

        int pre = 1;
        int suff = 1;
        int ans = Integer.MIN_VALUE;
        int size = nums.length;

        for(int i = 0 ; i < size ; i++){
            if(pre == 0){
                pre = 1;
            }
            if(suff == 0){
                suff = 1;
            }
            pre = pre * nums[i];
            suff = suff * nums[size-i-1];
            ans = Math.max(ans, Math.max(pre, suff));
        }
        return ans;
    }

    // Count Inversion










    // Find missing and repeating number
    // https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1
    static int[] findTwoElement(int[] a) {

        long n = a.length; // size of the array
        // Find Sn and S2n:
        long SN = (n * (n + 1)) / 2;
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6;

        // Calculate S and S2:
        long S = 0, S2 = 0;
        for (int i = 0; i < n; i++) {
            S += a[i];
            S2 += (long)a[i] * (long)a[i];
        }

        //S-Sn = X-Y:
        long val1 = S - SN;

        // S2-S2n = X^2-Y^2:
        long val2 = S2 - S2N;

        //Find X+Y = (X^2-Y^2)/(X-Y):
        val2 = val2 / val1;

        //Find X and Y: X = ((X+Y)+(X-Y))/2 and Y = X-(X-Y),
        // Here, X-Y = val1 and X+Y = val2:
        long x = (val1 + val2) / 2;
        long y = x - val1;

        int[] ans = {(int)x, (int)y};
        return ans;
    }

    // StockBuyAndSell - Single day buy and single day sale
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    static int profit(ArrayList<Integer> arr){

        int mini = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i = 0 ; i < arr.size() ; i++){
            maxProfit = Math.max(maxProfit, arr.get(i) - mini);
            mini = Math.min(mini, arr.get(i));
        }
        return maxProfit;
    }

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

    public int maxProfit(int[] prices) {

        int price_buy;
        int price_sell;
        int pf = 0;
        int ind = 0;
        int size = prices.length;

        while(ind < size-1){
            while(ind < size-1 && prices[ind] > prices[ind+1]){
                ind++;
            }

            price_buy = prices[ind];

            while(ind<size-1 && prices[ind]<=prices[ind+1]){
                ind++;
            }

            price_sell = prices[ind];

            pf += price_sell - price_buy;

        }
        return pf;

    }

    //https://leetcode.com/problems/merge-intervals/description/

    public int[][] merge(int[][] intervals) {

        //Arrays.sort(intervals , (a,b) ->  a[0] - b[0] );

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        LinkedList<int[]> ans = new LinkedList<int[]>();

        for(int[] interval : intervals){

            if(ans.isEmpty() || ans.getLast()[1] < interval[0]){
                ans.add(interval);
            }else{
                ans.getLast()[1] = Math.max(ans.getLast()[1] , interval[1]);
            }
        }

        return ans.toArray(new int[ans.size()][]);

    }

    //https://www.geeksforgeeks.org/problems/trapping-rain-water-1587115621/1

    static long trappingWater(int arr[], int n) {

        int left[] = new int[n];
        int right[] = new int[n];

        left[0] = arr[0];
        right[n-1] = arr[n-1];

        for(int i = 1 ; i<n ; i++){
            left[i] = Math.max(arr[i] , left[i-1]);
        }
        for(int i = n-2 ; i>=0 ; i--){
            right[i] = Math.max(arr[i] , right[i+1]);
        }
        long ans = 0;
        for(int i = 1 ; i<n-1 ; i++){
            ans = ans + Math.min(left[i] , right[i]) - arr[i];
        }
        return ans;
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, width * minHeight);

            // Move the pointer with the smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

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



    public static void main(String[] args) {

    }
}
