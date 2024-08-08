package Leetcode1269;

public class Leetcode1269_1 {
    public int numWays(int steps, int arrLen) {
        int n = Math.min(steps, arrLen);
        int MOD = 1000000007;
        long[][] dp = new long[steps + 1][n];
        dp[0][0] = 1;
        for(int i = 1; i <= steps; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = dp[i - 1][j]; //当前选择不动
                if(j - 1 >= 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD; //选择向右
                if(j + 1 < n) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD; //选择向左
            }
        }
        return (int)dp[steps][0];
    }
}
