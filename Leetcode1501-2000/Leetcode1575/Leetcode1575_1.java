package Leetcode1575;

import java.util.*;

public class Leetcode1575_1 {
    int MOD = (int) 1e9 + 7;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        //dp[i][j]表示在位置i，还剩j的燃料时，到终点的方案数
        long[][] dp = new long[n][fuel + 1];
        dp[finish][0] = 1;
        for(int j = 1; j <= fuel; j++){
            for(int i = 0; i < n; i++){
                if(i == finish) dp[i][j] = 1;
                for(int k = 0; k < n; k++){
                    int distance = Math.abs(locations[k] - locations[i]);
                    if(k == i || distance > j) continue;
                    dp[i][j] = (dp[i][j] + dp[k][j - distance]) % MOD;
                }
            }
        }
        return (int)dp[start][fuel];
    }
}
