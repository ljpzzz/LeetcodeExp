package Leetcode0265;

import java.util.*;

public class Leetcode0265_1 {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n+1][k];
        for(int i = 1; i <= n; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < k; j++) {
                int minDp = 0x3f3f3f3f;
                for(int t = 0; t < k; t++){
                    if(t == j) continue;
                    minDp = Math.min(minDp, dp[i][t]);
                }
                dp[i + 1][j] = minDp + costs[i][j];
            }
        }
        int ans = 0x3f3f3f3f;
        for(int j = 0; j < k; j++) ans = Math.min(ans, dp[n][j]);
        return ans;
    }
}
