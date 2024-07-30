package Leetcode1000;

import java.util.Arrays;

public class Leetcode1000_1 {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if(n == 1) return 0;
        if((n-1)%(k-1) != 0) return -1;
        int[] pSum = new int[n+1];
        for(int i = 1; i <= n; i++) pSum[i] = pSum[i-1] + stones[i-1];
        if(k == n) return pSum[n];

        //dp[i][j]表示把[i,j]的石头合成一堆需要的最小成本
        int[][][] dp = new int[n][n][k+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) Arrays.fill(dp[i][j], 0x3f3f3f3f);
            dp[i][i][1] = 0;
        }
        for(int len = 2; len <= n; len++){
            for(int i = 0; i + len - 1 < n; i++){
                int j = i + len - 1;
                for(int len2 = 2; len2 <= k; len2++) {
                    //[i,r]一堆，[r+1,j] len2-1堆
                    for (int r = i; r < j; r+=k-1) {
                        dp[i][j][len2] = Math.min(dp[i][j][len2], dp[i][r][1] + dp[r+1][j][len2-1]);
                    }
                }
                dp[i][j][1] = dp[i][j][k] + pSum[j+1] - pSum[i];
            }
        }
        return dp[0][n-1][1];
    }
}
