package Leetcode1639;

import java.util.*;

public class Leetcode1639_1 {
    public int numWays(String[] words, String target) {
        int m = words[0].length();
        int n = target.length();
        int MOD = (int) 1e9 + 7;
        int[][] posCnt = new int[m][26];
        for(String w : words){
            for(int i = 0; i < m; i++) posCnt[i][w.charAt(i) - 'a']++;
        }
        //dp[i][j表示target的前i个字母用words的前j个字母表示的方案数，
        long[][] dp = new long[n + 1][m + 1];
        Arrays.fill(dp[0], 1);
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = dp[i][j - 1]; //不选第j列
                int cnt = posCnt[j - 1][target.charAt(i - 1) - 'a'];
                dp[i][j] += cnt*dp[i - 1][j - 1]%MOD; //选择第j列
                dp[i][j] %= MOD;

            }
        }
        return (int)dp[n][m];
    }
    public static void main(String[] args) {
        String[] words = {"acca","bbbb","caca"};
        String target = "aba";
        Leetcode1639_1 leetcode1639_1 = new Leetcode1639_1();
        System.out.println(leetcode1639_1.numWays(words, target));
    }
}
