package Leetcode0552;

public class Leetcode0552_2 {
    int MOD = (int)1e9+7;
    public int checkRecord(int n) {
        if(n == 1) return 3;
        // dp[i][j][k]表示[0,i]区间，位置i之前(包括i)有j个连续L, 有k个A的方案数
        long[][][] dp = new long[n][3][2];
        dp[0][0][1] = 1; //位置0放A
        dp[0][0][0] = 1; //位置0放P
        dp[0][1][0] = 1; //位置0放L
        for(int i = 1; i < n; i++){
            //基于dp[i-1][j][k]，递推dp[i][x][x]方案数量
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 2; k++){
                    //当前位置放P
                    dp[i][0][k] += dp[i-1][j][k];
                    dp[i][0][k] %= MOD;
                    //当前位置放A
                    if(k == 0){
                        dp[i][0][1] += dp[i-1][j][k];
                        dp[i][0][1] %= MOD;
                    }
                    //当前位置放L
                    if(j < 2){
                        dp[i][j+1][k] += dp[i-1][j][k];
                        dp[i][j+1][k] %= MOD;
                    }
                }
            }
        }
        long res = 0;
        for(int j = 0; j < 3; j++) {
            for (int k = 0; k < 2; k++) {
                res += dp[n-1][j][k];
                res %= MOD;
            }
        }
        return (int)res;
    }
}
