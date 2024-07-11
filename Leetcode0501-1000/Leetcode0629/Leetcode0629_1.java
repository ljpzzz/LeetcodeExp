package Leetcode0629;

import java.util.Arrays;

public class Leetcode0629_1 {
    public int kInversePairs(int n, int k) {
        if(k == 0) return 1;
        int MOD = (int)1e9+7;
        long[][] c = new long[n][n];
        c[0][0] = 1;
        //预处理组合数
        for(int i = 1; i < n; i++){
            c[i][0] = 1;
            for(int j = 1; j < n; j++){
                c[i][j] = c[i-1][j] + c[i-1][j-1];
                c[i][j] %= MOD;
            }
        }
        //dp[i][j]表示前i个数字有j个逆序对的方案数
        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            //前i个数字有j个逆序对, 分解为：
            // 前i-1个数字有x个逆序对, 第i个数字偶j-x个逆序对
            for(int j = 1; j <= k; j++) {
                if(j >= i) dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-i];
                else if(j >= 1) dp[i][j] = dp[i][j-1] + dp[i-1][j];
                else dp[i][j] = dp[i-1][j];
                dp[i][j] = (dp[i][j] + MOD)%MOD;
            }
        }
        return (int)dp[n][k];
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0629_1().kInversePairs(3, 1));
    }
}
