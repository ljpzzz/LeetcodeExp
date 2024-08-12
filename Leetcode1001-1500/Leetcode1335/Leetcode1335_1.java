package Leetcode1335;

import java.util.Arrays;

public class Leetcode1335_1 {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) return -1;
        //dp[i][j]表示前i天，完成前j个任务，对应的最小难度
        int[][] dp = new int[d + 1][n + 1];
        for(int i = 0; i <= d; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        dp[0][0] = 0;
        for(int i = 1; i <= d; i++){
            for(int j = i; j <= n; j++){
                //i天完成j个任务，拆分为i-1天完成j-k个任务,最后一天完成k个任务
                int max = jobDifficulty[j-1];
                for(int k = 1; k <= j; k++){
                    max = Math.max(max, jobDifficulty[j-k]);
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-k] + max);
                }
            }
        }
        return dp[d][n];
    }
}
