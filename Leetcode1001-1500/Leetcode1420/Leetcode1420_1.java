package Leetcode1420;

public class Leetcode1420_1 {
    public int numOfArrays(int n, int m, int k) {
        int MOD = (int) 1e9 + 7;
        if(k == 0) return 0;
        // dp[i][j][t] 表示长度为i，当前的最大值是j，cost为t的数组的个数
        long[][][] dp = new long[n + 1][m+1][k + 1];
        for(int j = 1; j <= m; j++) dp[1][j][1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                for(int t = 1; t <= k; t++) {
                    for(int j1 = 1; j1 <= j; j1++) {
                        //op1, 当前要取新的最大值, 则前i-1个数字的最大值是j-1
                        if(j > j1) dp[i][j][t] = (dp[i][j][t] + dp[i - 1][j1][t - 1]) % MOD;
                        //op2, 当前取相同的最大值, 则前i-1个数字的最大值是j, 当前有j种选择
                        else dp[i][j][t] = (dp[i][j][t] +  j*dp[i - 1][j1][t] % MOD) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for(int j = 1; j <= m; j++) ans = (ans + dp[n][j][k]) % MOD;
        return (int) ans;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode1420_1().numOfArrays(2, 4, 2));
    }
}
