package Leetcode1563;

public class Leetcode1563_1 {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        if(n == 1) return 0;
        int[] pSum = new int[n + 1];
        for(int i = 0; i < n; i++) pSum[i+1] = pSum[i] + stoneValue[i];
        int[][] dp = new int[n][n];
        for(int len = 2; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;
                //将[i,j]拆分为[i,k],[k+1,j]两部分
                for(int k = i; k < j; k++){
                    int sum1 = pSum[k+1] - pSum[i];
                    int sum2 = pSum[j+1] - pSum[k+1];
                    if(sum1 < sum2) dp[i][j] = Math.max(dp[i][j], dp[i][k] + sum1);
                    else if(sum1 > sum2) dp[i][j] = Math.max(dp[i][j], dp[k+1][j] + sum2);
                    else dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][k] + sum1, dp[k+1][j] + sum2));
                }
            }
        }
        return dp[0][n-1];
    }
}
