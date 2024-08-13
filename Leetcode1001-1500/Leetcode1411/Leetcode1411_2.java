package Leetcode1411;

public class Leetcode1411_2 {
    int MOD = 1000000007;
    public int numOfWays(int n) {
        // dp[i][0]表示第i行，第一类6种排列每种出现的概率。
        // dp[i][1]表示第i行，第二类6种排列每种出现的概率。
        // 第一类6种包含RYR, RGR, YGY, YRY, GRG, GYG, 即有2个一样的颜色出现
        // 第二类6种包含GYR, GRY, YRG, YGR, RGY, RYG，即三个颜色都不一样
        long[][] dp = new long[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for(int i = 1; i < n; i++){
            //第一类的某个排列，可以由上一行3个第一类排列，和2个第二类排列转移得到
            dp[i][0] = dp[i-1][0]*3 + dp[i-1][1]*2;
            dp[i][0] %= MOD;
            //第二类的某个排列，可以由上一行2个第一类排列，和2个第二类排列转移得到
            dp[i][1] = dp[i-1][0]*2 + dp[i-1][1]*2;
            dp[i][1] %= MOD;
        }
        long res = dp[n-1][0]*6 + dp[n-1][1]*6;
        res %= MOD;
        return (int)res;
    }
}
