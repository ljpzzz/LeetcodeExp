package Leetcode1510;

import java.util.*;

public class Leetcode1510_1 {
    Boolean[] dp;
    List<Integer> d = new ArrayList<>();
    public boolean winnerSquareGame(int n) {
        for(int i = 1; i*i <= n; i++) d.add(i*i);
        dp = new Boolean[n+1];
        dp[0] = false;
        return dfs(n);
    }
    public boolean dfs(int n){
        if(dp[n] != null) return dp[n];
        boolean ans = false;
        for(int dd : d){
            if(dd > n) break;
            if(!dfs(n-dd)){
                ans = true;
                break;
            }
        }
        dp[n] = ans;
        return ans;
    }
}
