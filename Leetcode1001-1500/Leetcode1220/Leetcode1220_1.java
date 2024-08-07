package Leetcode1220;

import java.util.*;

public class Leetcode1220_1 {
    public int countVowelPermutation(int n) {
        List<Integer>[] g = new List[5];
        for(int i = 0; i < 5; i++) g[i] = new ArrayList<>();
        g[0].add(1);
        g[1].add(0); g[1].add(2);
        g[2].add(0); g[2].add(1); g[2].add(3); g[2].add(4);
        g[3].add(2); g[3].add(4);
        g[4].add(0);

        long[][] dp = new long[n][5];
        int MOD = 1000000007;
        Arrays.fill(dp[0], 1);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < 5; j++){
                for(int next : g[j]){
                    dp[i][next] += dp[i - 1][j];
                    dp[i][next] %= MOD;
                }
            }
        }
        long ans = 0;
        for(int i = 0; i < 5; i++) ans = (ans + dp[n - 1][i])%MOD;
        return (int)ans;
    }
}
