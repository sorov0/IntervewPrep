package DSAlgo.practice.arrays;

public class StockBuySell_MultipleDay {

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
}
