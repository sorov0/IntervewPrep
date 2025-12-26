package DSAlgo.practice.arrays;

import java.util.ArrayList;

public class StockBuySell_SingleDay {

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

    //Function to find the days of buying and selling stock for max profit.
    public static int bestTimeToBuyAndSellStock(int []prices) {
        // Write your code here.

        int minPrice = Integer.MAX_VALUE;
        int profit = 0;

        for(int i = 0 ; i <prices.length ; i++){

            minPrice = Math.min(minPrice, prices[i]);
            profit = Math.max(profit, prices[i] - minPrice);

        }
        return profit;
    }
    public static void main(String[] args){

        int arr[] = new int[]{100,180,260,310,40,535,695};

    }
}
