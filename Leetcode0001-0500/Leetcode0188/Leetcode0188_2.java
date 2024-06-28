package Leetcode0188;

public class Leetcode0188_2 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] d = new int[k][2];
        for(int i = 0; i < k; i++) d[i][0] = -prices[0];
        for(int i = 1; i < n; i++){
            for(int j = 0; j < k; j++){
                d[j][0] = Math.max(d[j][0], ((j-1 >= 0) ?d[j-1][1]:0)-prices[i]);
                d[j][1] = Math.max(d[j][1], d[j][0]+prices[i]);
            }
        }
        return d[k-1][1];
    }
}
