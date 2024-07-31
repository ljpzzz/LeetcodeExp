package Leetcode1092;

import java.util.Arrays;

public class Leetcode1092_1 {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1]; //最短公共子序列的长度
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else if(dp[i-1][j] >= dp[i][j-1]){
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int p1 = m;
        int p2 = n;
        while(p1 >= 1 || p2 >= 1){
            if(p1 == 0){
                sb.append(str2.charAt(p2-1));
                p2--;
            }
            else if(p2 == 0){
                sb.append(str1.charAt(p1-1));
                p1--;
            }
            else if(str1.charAt(p1-1) == str2.charAt(p2-1)){
                sb.append(str1.charAt(p1-1));
                p1--;
                p2--;
            }
            else if(dp[p1-1][p2] >= dp[p1][p2-1]){
                sb.append(str1.charAt(p1-1));
                p1--;
            }
            else {
                sb.append(str2.charAt(p2-1));
                p2--;
            }
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        Leetcode1092_1 test = new Leetcode1092_1();
        System.out.println(test.shortestCommonSupersequence("abac", "cab"));
    }
}
