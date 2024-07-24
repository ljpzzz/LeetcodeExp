package Leetcode0879;

public class Leetcode0879_2 {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][minProfit + 1];
        for(int i = 0; i <= n; i++) dp[i][0] = 1;

        for(int i = 0; i < group.length; i++) {
            int person = group[i], cost = profit[i];
            for(int j = n; j >= person; j--) {
                for(int k = minProfit; k >= 0; k--) {
                    dp[j][k] += dp[j - person][Math.max(0, k - cost)];
                    dp[j][k] %= mod;
                }
            }
        }
        return dp[n][minProfit];
    }
}
