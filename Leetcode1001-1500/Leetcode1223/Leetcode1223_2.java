package Leetcode1223;

import java.util.Arrays;

public class Leetcode1223_2 {
    int MOD = (int)1e9+7;
    public int dieSimulator(int n, int[] rollMax) {
        long[][] dp = new long[n][6];
        Arrays.fill(dp[0], 1);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < 6; j++){
                for(int k = 0; k < 6; k++){
                    dp[i][j] += dp[i-1][k];
                    dp[i][j] %= MOD;
                }
                if(i > rollMax[j]){
                    for(int k = 0; k < 6; k++) {
                        if(j == k) continue;
                        dp[i][j] -= dp[i - rollMax[j]-1][k];
                        dp[i][j] = (dp[i][j] + MOD) % MOD;
                    }
                }
                else if(i == rollMax[j]){
                    dp[i][j] = (dp[i][j]-1+MOD)%MOD;
                }
            }
        }
        return (int)((dp[n-1][0]+dp[n-1][1]+dp[n-1][2]+dp[n-1][3]+dp[n-1][4]+dp[n-1][5])%MOD);
    }
}
