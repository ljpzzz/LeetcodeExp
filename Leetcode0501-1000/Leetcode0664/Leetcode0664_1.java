package Leetcode0664;

import java.util.*;

public class Leetcode0664_1 {
    public int strangePrinter(String s) {
        StringBuilder sb = new StringBuilder();
        char pre = ' ';
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != pre){
                sb.append(s.charAt(i));
                pre = s.charAt(i);
            }
        }
        s = sb.toString();
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
            dp[i][i] = 1;
        }
        for(int len = 2; len <= n; len++){
            for(int i = 0; i + len - 1 < n; i++){
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i][j-1];
                else{
                    for(int len2 = 1; len2 < len; len2++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][i+len2-1] + dp[i+len2][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0664_1().strangePrinter("ccdcadbddbaddcbccdcdabcbcddbccdcbad"));
    }
}
