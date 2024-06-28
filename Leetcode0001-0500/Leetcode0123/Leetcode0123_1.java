package Leetcode0123;

import java.util.Arrays;

public class Leetcode0123_1 {
    //通用DP
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int k = 2;
        int[][][] dp = new int[n][k+1][2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= k; j++) Arrays.fill(dp[i][j], Integer.MIN_VALUE/3);
        }
        Arrays.fill(dp[0][0], 0);
        for(int j = 1; j <= k; j++){
            dp[0][j][0] = -prices[0];
            dp[0][j][1] = 0;
        }
        for(int i = 1; i < n; i++){
            Arrays.fill(dp[i][0], 0);
            for(int j = 1; j <= k; j++){
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j-1][1]-prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0]+prices[i]);
            }
        }
        int ans = 0;
        for(int j = 0; j <= k; j++){
            ans = Math.max(ans, dp[n - 1][j][1]);
        }
        return ans;
    }
    public static void main(String[] args){
        System.out.println(new Leetcode0123_1().maxProfit(new int[]{14,9,10,12,4,8,1,16}));
    }
}
