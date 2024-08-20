package Leetcode1531;

import java.util.*;

public class Leetcode1531_1 {
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        //dp[i][j]表示前i个字符删除任意j个字符后，编码的最小长度
        int[][] dp = new int[n+1][k+1];
        for(int i = 0; i <= n; i++) Arrays.fill(dp[i], n*100);
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= Math.min(i,k); j++){
                //第i个字符被删除，等价于前i-1个字符删除j-1个字符
                if(j > 0) dp[i][j] = dp[i-1][j-1];
                int same = 0; //[t,i]区间和s[i]相同的字符，不需要删除
                int diff = 0; //[t,i]区间和s[i]不同的字符，需要删除
                for(int t = i; t >= 1 && diff <= j; t--){
                    if(s.charAt(i-1) == s.charAt(t-1)){
                        same++;
                        dp[i][j] = Math.min(dp[i][j], dp[t-1][j-diff]+encodeLen(same));
                    }
                    else diff++;
                }
            }
        }
        return dp[n][k];

    }
    public int encodeLen(int len){
        if(len == 1) return 1;
        else if(len < 10) return 2;
        else if(len < 100) return 3;
        else return 4;
    }
}
