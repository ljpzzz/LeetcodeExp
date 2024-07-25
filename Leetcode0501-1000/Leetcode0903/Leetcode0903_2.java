package Leetcode0903;

import java.util.Arrays;

public class Leetcode0903_2 {
    //DP未优化版本
    public int numPermsDISequence(String s) {
        int m = s.length();
        int n = m + 1;
        int MOD = (int) 1e9 + 7;
        //dp[i][j]表示前i+1个数字的perm[0...i]，未选择的元素中，有j个元素比nums[i]小
        long[][] dp = new long[n][n];
        Arrays.fill(dp[0], 1);

        for(int i = 1; i < n; i++){
            for(int j = 0; j <= i; j++){
                if(s.charAt(i - 1) == 'D'){
                    for(int k = j; k < i; k++){
                        dp[i][j] += dp[i - 1][k];
                        dp[i][j] %= MOD;
                    }
                }
                else{
                    for(int k = 0; k < j; k++){
                        dp[i][j] += dp[i - 1][k];
                        dp[i][j] %= MOD;
                    }
                }
            }
        }
        long ans = 0;
        for(int i = 0; i < n; i++) ans = (ans + dp[n-1][i]) % MOD;
        return (int)ans;
    }
}
