package Leetcode1434;

import java.util.*;

public class Leetcode1434_1 {
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        int m = 40;
        int MOD = (int) 1e9 + 7;
        List<Integer> [] hats2People = new List[m]; //编号改为0-39
        for(int i = 0; i < m; i++) hats2People[i] = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int hat : hats.get(i)) hats2People[hat-1].add(i);
        }
        //dp[i][j]表示前i顶帽子，分配后mask为j时总的方案数
        long[][] dp = new long[m+1][1<<n];
        dp[0][0] = 1;

        for(int i = 1; i <= m; i++){
            for(int j = 0; j < (1<<n); j++){
                //默认不分配帽子i
                dp[i][j] = dp[i-1][j];
                //分配帽子i
                for(int people : hats2People[i-1]){
                    if((j & (1<<people)) == 0) continue;
                    dp[i][j] += dp[i-1][j ^ (1<<people)];
                    dp[i][j] %= MOD;
                }
            }
        }
        return (int) dp[m][(1<<n)-1];
    }
}
