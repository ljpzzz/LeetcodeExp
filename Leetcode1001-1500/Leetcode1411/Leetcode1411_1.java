package Leetcode1411;

import java.util.*;

public class Leetcode1411_1 {
    public int numOfWays(int n) {
        int MOD = (int) 1e9 + 7;
        List<Integer> validMasks = new ArrayList<>();
        for(int i = 1; i <= 26; i++){
            int[] d = getVal(i);
            if(d[0] != d[1] && d[1] != d[2]) validMasks.add(i);
        }
        int m = validMasks.size();
        long[][] dp = new long[n][m]; //dp[i][j]表示第i行mask为j的方案数
        Arrays.fill(dp[0], 1);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < m; j++){
                for(int k = 0; k < m; k++){
                    int[] preVal = getVal(validMasks.get(k));
                    int[] curVal = getVal(validMasks.get(j));
                    if(preVal[0] != curVal[0] && preVal[1] != curVal[1] && preVal[2] != curVal[2]){
                        dp[i][j] += dp[i-1][k];
                        dp[i][j] %= MOD;
                    }
                }
            }
        }
        long ans = 0;
        for(int i = 0; i < m; i++) ans = (ans + dp[n-1][i]) % MOD;
        return (int)ans;
    }
    public int[] getVal(int mask){
        int x = mask%3;
        mask /= 3;
        int y = mask%3;
        mask /= 3;
        int z = mask%3;
        return new int[]{x, y, z};
    }
}
