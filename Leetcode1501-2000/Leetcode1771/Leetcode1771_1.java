package Leetcode1771;

import java.util.Arrays;

public class Leetcode1771_1 {
    public int longestPalindrome(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        String s = word1 + word2;
        int[][] dp = new int[m + n][m + n];
        for(int i = 0; i < m+n; i++) dp[i][i] = 1;
        for(int i = m+n-1; i >= 0; i--){
            for(int j = i+1; j < m+n; j++){
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i+1][j-1]+2;
                else dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = m; j < m+n; j++){
                if(word1.charAt(i) != word2.charAt(j-m)) continue;
                ans = Math.max(ans, dp[i+1][j-1]+2);
            }
        }
        return ans;
    }
}
