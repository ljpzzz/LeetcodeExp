package Leetcode0123;

public class Leetcode0123_2 {
    //非通用DP
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0];
        int sell1 = 0;
        int buy2 = -prices[0];
        int sell2 = 0;
        for(int i = 1; i < n; i++){
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, prices[i]+buy1);
            buy2 = Math.max(buy2, sell1-prices[i]);
            sell2 = Math.max(sell2, prices[i]+buy2);
        }
        return Math.max(0,Math.max(sell1,sell2));
    }
}
