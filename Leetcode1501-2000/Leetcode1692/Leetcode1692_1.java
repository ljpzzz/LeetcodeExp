package Leetcode1692;

public class Leetcode1692_1 {
    int MOD = (int)1e9+7;
    public int waysToDistribute(int n, int k) {
        if (n == k) return 1;
        long[][] dp = new long[n + 1][k + 1]; //dp[i][j]表示前i个糖果放进j个口袋的方案数量
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j] * j % MOD + dp[i - 1][j - 1];
                dp[i][j] %= MOD;
            }
        }
        return (int) dp[n][k];
    }
}
