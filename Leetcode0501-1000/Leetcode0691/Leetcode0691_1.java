package Leetcode0691;

import java.util.*;

public class Leetcode0691_1 {
    int m;
    int n;
    int[][] cnt;
    Integer[] dp;
    public int minStickers(String[] stickers, String target) {
        m = target.length();
        n = stickers.length;
        cnt = new int[n][26];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < stickers[i].length(); j++) cnt[i][stickers[i].charAt(j)-'a']++;
        }
        //检查是否有不存在的字符
        for(int i = 0; i < m; i++){
            boolean ok = false;
            int pos = target.charAt(i)-'a';
            for(int j = 0; j < n; j++){
                if(cnt[j][pos] > 0){
                    ok = true;
                    break;
                }
            }
            if(!ok) return -1;
        }
        dp = new Integer[1<<m];
        dp[0] = 0;
        return dfs(stickers, target, (1<<m)-1);
    }
    public int dfs(String[] stickers, String target, int mask){
        if(dp[mask] != null) return dp[mask];
        int ans = m+1;
        for(int i = 0; i < n; i++){
            int[] cntCur = cnt[i].clone();
            int nextMask = mask;
            for(int j = 0; j < m; j++){
                if((mask&(1<<j)) == 0 || cntCur[target.charAt(j)-'a'] == 0) continue;
                nextMask ^= (1<<j);
                cntCur[target.charAt(j)-'a']--;
            }
            if(nextMask == mask) continue;
            ans = Math.min(ans, dfs(stickers, target, nextMask)+1);
        }
        dp[mask] = ans;
        return ans;
    }
}
