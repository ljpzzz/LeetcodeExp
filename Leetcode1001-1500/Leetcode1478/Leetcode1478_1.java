package Leetcode1478;

import java.util.Arrays;

public class Leetcode1478_1 {
    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        if(k >= n) return 0;
        int[][] cost = new int[n][n]; // cost[i][j]表示将i到j放一个邮筒的最小代价
        for(int len = 2; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;
                cost[i][j] = cost[i+1][j-1] + houses[j] - houses[i];
            }
        }
        //dp[i][j]表示前i个房子放j个邮筒的最小代价
        int[][] dp = new int[n][k+1];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        for(int i = 0; i < n; i++) dp[i][1] = cost[0][i];
        for(int i = 1; i < n; i++){
            for(int j = 2; j <= Math.min(k, i+1); j++){
                //[0,i]放j个邮筒，等价于[0,t]放j-1个邮筒，[t+1,i]放1个邮筒
                for(int t = j-2; t < i; t++){
                    dp[i][j] = Math.min(dp[i][j], dp[t][j-1] + cost[t+1][i]);
                }
            }
        }
        return dp[n-1][k];
    }
}
