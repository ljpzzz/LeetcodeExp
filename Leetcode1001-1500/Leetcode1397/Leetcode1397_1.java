package Leetcode1397;

import java.util.*;

public class Leetcode1397_1 {
    int MOD = (int) 1e9 + 7;
    int n;
    int m;
    int[][] trans;
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.n = n;
        m = evil.length();
        //trans[i][j]表示当前匹配长度为i，遇到字母j时，新的匹配长度
        trans = new int[m][26];
        trans[0][evil.charAt(0) - 'a'] = 1;
        for(int i = 1; i < m; i++){
            for(int j = 0; j < 26; j++){
                if(j == evil.charAt(i) - 'a') trans[i][j] = i+1;
                else{
                    String tmp = evil + evil.substring(0, i) + (char)('a' + j);
                    int[] next = getNext(tmp);
                    trans[i][j] = next[tmp.length()-1];
                }
            }
        }
        long ans1 = getCnt(s1, 0, 0, true, new Long[n][m]);
        long ans2 = getCnt(s2, 0, 0, true, new Long[n][m]);
        long ans = (ans2-ans1+MOD)%MOD;
        if(!s1.contains(evil)) ans = (ans + 1 + MOD)%MOD;
        return (int)ans;
    }
    //dp[index][preMatch]
    public long getCnt(String s, int index, int preMatch, boolean limit, Long[][] dp){
        if(index == n) return 1;
        if(!limit && dp[index][preMatch] != null) return dp[index][preMatch];
        long ans = 0;
        int up = limit ? s.charAt(index) - 'a' : 25;
        for(int i = 0; i <= up; i++){
            int nextTrans = trans[preMatch][i];
            if(nextTrans == m) continue;
            boolean newLimit = limit && i == up;
            ans += getCnt(s, index+1, nextTrans, newLimit, dp);
            ans %= MOD;
        }
        if(!limit) dp[index][preMatch] = ans;
        return ans;
    }
    public int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); ++i) {
            int j = next[i - 1];
            while (j != 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = next[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                next[i] = j + 1;
            }
        }
        return next;
    }
    public static void main(String[] args) {
        Leetcode1397_1 test = new Leetcode1397_1();
        System.out.println(test.findGoodStrings(2, "aa", "da", "b"));
    }
}
