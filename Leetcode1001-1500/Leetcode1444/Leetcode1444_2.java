package Leetcode1444;

import java.util.Arrays;

public class Leetcode1444_2 {
    int MOD = (int)1e9 + 7;
    int m;
    int n;
    long[][][] dp;
    int[][] pSum;
    int k;
    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        this.k = k;
        pSum = new int[m + 1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pSum[i+1][j+1] = pSum[i+1][j] + pSum[i][j+1] - pSum[i][j] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }
        //dp[i][j][t]是从(i,j)到(m-1, n-1)切t刀有多少种方案
        dp = new long[m][n][k];
        for(int i = 0; i < m;i++){
            for(int j = 0; j < n; j++) Arrays.fill(dp[i][j], -1);
        }
        return (int)dfs(0, 0, 0);
    }
    public long dfs(int i, int j, int t){
        if(t == k-1){
            int cnt = pSum[m][n] - pSum[m][j] - pSum[i][n] + pSum[i][j];
            return cnt > 0 ? 1 : 0;
        }
        if(dp[i][j][t] != -1) return dp[i][j][t];
        long res = 0;
        for(int x = i + 1; x < m; x++){
            int cnt = pSum[x][n] - pSum[x][j] - pSum[i][n] + pSum[i][j];
            if(cnt > 0) res = (res + dfs(x, j, t+1)) % MOD;
        }
        for(int x = j + 1; x < n; x++){
            int cnt = pSum[m][x] - pSum[m][j] - pSum[i][x] + pSum[i][j];
            if(cnt > 0) res = (res + dfs(i, x, t+1))%MOD;
        }
        dp[i][j][t] = res;
        return res;
    }
}
