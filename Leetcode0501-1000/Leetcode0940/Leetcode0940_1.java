package Leetcode0940;

import java.util.Arrays;

public class Leetcode0940_1 {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        int n = s.length();
        int[][] pos = new int[n][26];
        int[] tmp = new int[26];
        Arrays.fill(tmp, -1);
        for(int i = 0; i < n; i++){
            pos[i] = tmp.clone();
            int c = s.charAt(i) - 'a';
            tmp[c] = i;
        }
        //dp[i]是以i结束的子序列个数
        long[] dp = new long[26];
        for(int i = 0; i < n; i++){
            int c = s.charAt(i) - 'a';
            long tmpAns = 0;
            for(int j = 0; j < 26; j++){
                if(pos[i][j] != -1){
                    tmpAns += dp[j];
                    tmpAns %= MOD;
                }
            }
            dp[c] = (tmpAns + 1)%MOD;
        }
        long ans = 0;
        for(int i = 0; i < 26; i++){
            ans += dp[i];
            ans %= MOD;
        }
        return (int)ans;
    }
    public static void main(String[] args) {
        Leetcode0940_1 test = new Leetcode0940_1();
        System.out.println(test.distinctSubseqII("abc"));
        System.out.println(test.distinctSubseqII("aba"));
        System.out.println(test.distinctSubseqII("aaa"));
    }
}
