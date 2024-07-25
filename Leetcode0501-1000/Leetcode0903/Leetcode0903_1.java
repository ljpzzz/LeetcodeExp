package Leetcode0903;

import java.util.*;

public class Leetcode0903_1 {
    //DP优化版本
    public int numPermsDISequence(String s) {
        int m = s.length();
        int n = m+1;
        int MOD = (int)1e9+7;
        //dp[i][j]表示前i+1个数字的perm[0...i]，未选择的元素中，有j个元素比nums[i]小
        long[][] dp = new long[n][n];
        Arrays.fill(dp[0], 1);

        for(int i = 1; i < n; i++){
            if(s.charAt(i-1) == 'D'){
                dp[i][i] = 0;
                for(int j = i-1; j >= 0; j--){
                    dp[i][j] = (dp[i-1][j] + dp[i][j+1]) % MOD;
                }
            }else{
                dp[i][0] = 0;
                for(int j = 1; j <= i; j++){
                    dp[i][j] = (dp[i-1][j-1] + dp[i][j-1]) % MOD;
                }
            }
        }
        long ans = 0;
        for(int i = 0; i < n; i++) ans = (ans + dp[n-1][i]) % MOD;
        return (int)ans;
    }
}
