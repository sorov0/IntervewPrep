package DSAlgo.practice.arrays;

public class TrappingRainWater {

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

}
