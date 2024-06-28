package Leetcode0132;

import java.util.*;

public class Leetcode0132_1 {
    public int minCut(String s) {
        int n = s.length();
        //isHuiwen[i][j]表示[i,j]之间是否是回文，特别的 i>=j时 isHuiwen[i][j] = true;
        boolean[][] isHuiwen = new boolean[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(isHuiwen[i], true);
        for(int i = n-1; i >= 0; i--){
            for(int j = i+1; j < n; j++){
                if(s.charAt(i) == s.charAt(j)) isHuiwen[i][j] = isHuiwen[i+1][j-1];
                else isHuiwen[i][j] = false;
            }
        }
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            if(isHuiwen[0][i-1]){
                dp[i] = 0;
                continue;
            }
            dp[i] = i-1;
            for(int j = 2; j <= i; j++){
                if(isHuiwen[j-1][i-1]) {
                    dp[i] = Math.min(dp[i], dp[j-1]+1);
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args)
    {
        Leetcode0132_1 leetcode = new Leetcode0132_1();
        String s = "aab";
        System.out.println(leetcode.minCut(s));
    }
}
