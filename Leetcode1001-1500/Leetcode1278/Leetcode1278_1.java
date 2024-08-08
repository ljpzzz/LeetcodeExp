package Leetcode1278;

import java.util.Arrays;

public class Leetcode1278_1 {
    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] dpHuiwen = new int[n][n];
        for(int len = 2; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len-1;
                if(s.charAt(i) == s.charAt(j)) dpHuiwen[i][j] = dpHuiwen[i+1][j-1];
                else dpHuiwen[i][j] = dpHuiwen[i+1][j-1] + 1;
            }
        }
        // dp[i][t]表示把前i个数字分成j个回文串的最小修改次数
        int[][] dp = new int[n+1][k+1];
        for(int i = 0; i <= n; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= Math.min(k, i); j++){
                //把前i个数字分成j个回文串， 可以拆解为前l个数字分成j-1个回文串和第l+1个数字到第i个数字组成一个回文串
                for(int l = 0; l < i; l++){
                    dp[i][j] = Math.min(dp[i][j], dp[l][j-1] + dpHuiwen[l][i-1]);
                }
            }
        }
        return dp[n][k];
    }
    public static void main(String[] args) {
        Leetcode1278_1 leetcode1278_1 = new Leetcode1278_1();
        System.out.println(leetcode1278_1.palindromePartition("tcym", 2));
    }
}
