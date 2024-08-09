package Leetcode1320;

import java.util.Arrays;

public class Leetcode1320_1 {
    public int minimumDistance(String word) {
        int n = word.length();
        int[][][] dp = new int[n][26][26];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 26; j++) Arrays.fill(dp[i][j], 0x3f3f3f3f);
        }
        int init = word.charAt(0) - 'A';
        Arrays.fill(dp[0][init], 0);
        for(int j = 0; j < 26; j++) dp[0][j][init] = 0;
        //当前处理位置i的cur
        for(int i = 1; i < n; i++){
            int cur = word.charAt(i) - 'A';
            for(int j = 0; j < 26; j++){
                for(int k = 0; k < 26; k++){
                    //op1:第一只手从位置j到cur
                    dp[i][cur][k] = Math.min(dp[i][cur][k], dp[i - 1][j][k] + getDistance(j, cur));
                    //op2:第二只手从位置k到cur
                    dp[i][j][cur] = Math.min(dp[i][j][cur], dp[i - 1][j][k] + getDistance(k, cur));
                }
            }
        }
        int ans = 0x3f3f3f3f;
        for(int i = 0; i < 26; i++){
            for(int j = 0; j < 26; j++) ans = Math.min(ans, dp[n - 1][i][j]);
        }
        return ans;
    }
    public int getDistance(int i, int j) {
        return Math.abs(i / 6 - j / 6) + Math.abs(i % 6 - j % 6);
    }
}
