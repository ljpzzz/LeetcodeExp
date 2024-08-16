package Leetcode1510;

import java.util.*;

public class Leetcode1510_2 {
    public boolean winnerSquareGame(int n) {
        List<Integer> items = new ArrayList<>();
        boolean[] dp = new boolean[n+1];
        dp[0] = false;
        for(int i = 1; i*i <= n; i++) {
            items.add(i*i);
            dp[i*i] = true;
        }
        for(int i = 1; i <= n; i++){
            if(items.contains(i)) continue;
            for(int j : items){
                if(j > i) continue;
                if(!dp[i-j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
