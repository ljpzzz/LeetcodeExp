package Leetcode1147;

import java.util.Arrays;

public class Leetcode1147_1 {
    public int longestDecomposition(String text) {
        int n = text.length();
        int m = n / 2;
        int ans = 0;
        int ansIndex = -1;
        //dp[i]表示[0,i]和[n-i,n-1]凑成回文的最长个数
        int[] dp = new int[m + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 1; i <= m; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] == -1) continue;
                if(text.substring(j, i).equals(text.substring(n - i, n - j))){
                    dp[i] = Math.max(dp[i], dp[j] + 2);
                    if(dp[i] > ans){
                        ans = dp[i];
                        ansIndex = i;
                    }
                }
            }
        }
        if(ansIndex == m && m*2 == n) return ans;
        else return ans + 1;
    }
    public static void main(String[] args)
    {
        Leetcode1147_1 test = new Leetcode1147_1();
        System.out.println(test.longestDecomposition("abab"));
    }
}
