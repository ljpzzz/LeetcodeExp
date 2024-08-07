package Leetcode1223;

public class Leetcode1223_1 {
    public int dieSimulator(int n, int[] rollMax) {
        int MOD = 1000000007;
        long[][][] dp = new long[n][6][16];
        for (int i = 0; i < 6; i++) dp[0][i][1] = 1;
        //当前位置i,填入数字l, 基于位置i-1的数字j, 连续数字数量k来递推
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int l = 0; l < 6; l++) {
                        if (l != j) dp[i][l][1] = (dp[i][l][1] + dp[i - 1][j][k]) % MOD;
                        else if(k+1 <= rollMax[j]) dp[i][l][k + 1] = (dp[i][l][k + 1] + dp[i - 1][j][k]) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 6; i++) {
            for(int j = 1; j <= rollMax[i]; j++) ans = (ans + dp[n - 1][i][j])%MOD;
        }
        return (int)ans;
    }
}
