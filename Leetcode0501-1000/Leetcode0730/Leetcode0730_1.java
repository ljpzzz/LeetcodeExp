package Leetcode0730;

import java.util.*;

public class Leetcode0730_1 {
    int MOD = (int)1e9+7;
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        long[][][] dp = new long[4][n][n];
        for(int i = 0;i < n;i++) dp[s.charAt(i)-'a'][i][i] = 1;
        for(int len = 2;len <= n;len++){
            for(int i = 0;i+len-1 < n;i++){
                int j = i+len-1;
                int left = s.charAt(i)-'a';
                int right = s.charAt(j)-'a';
                for(int k = 0;k < 4; k++){
                    if(left == right){
                        if(left == k) dp[k][i][j] = 2 + dp[0][i+1][j-1]+ dp[1][i+1][j-1]+ dp[2][i+1][j-1]+ dp[3][i+1][j-1];
                        else dp[k][i][j] = dp[k][i+1][j-1];
                        dp[k][i][j] %= MOD;
                    }
                    else if(left == k) dp[k][i][j] = dp[k][i][j-1];
                    else if(right == k) dp[k][i][j] = dp[k][i+1][j];
                    else dp[k][i][j] = dp[k][i+1][j-1];

                }
            }
        }
        long ans = (dp[0][0][n-1] + dp[1][0][n-1] + dp[2][0][n-1] + dp[3][0][n-1])%MOD;
        return (int)ans;
    }
}
