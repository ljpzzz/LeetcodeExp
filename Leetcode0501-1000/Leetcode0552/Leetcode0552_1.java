package Leetcode0552;

public class Leetcode0552_1 {
    public int checkRecord(int n) {
        int MOD = (int) 1e9 + 7;
        // dp[i][j][k] 表示第i天，j表示A的数量，k表示连续L数量
        long[][][] dp = new long[n + 1][2][3];
        dp[0][0][0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 2; j++){
                //先计算dp[i][j][0], 此时当前一定是P或者A
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1] + dp[i - 1][j][2])%MOD; //当前是P
                if(j > 0) dp[i][j][0] = (dp[i][j][0] + dp[i-1][j-1][0]+dp[i-1][j-1][1] +dp[i-1][j-1][2])%MOD;//当前是A
                //计算dp[i][j][1]和dp[i][j][2],此时当前一定是L
                for(int k = 1; k < 3; k++){
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k-1])%MOD;
                }
           }
        }
        long ans = 0;
        for(int j = 0; j < 2; j++){
            for(int k = 0; k < 3; k++){
                ans += dp[n][j][k];
                ans %= MOD;
            }
        }
        return (int) ans;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0552_1().checkRecord(2));
        System.out.println(new Leetcode0552_1().checkRecord(1));
        System.out.println(new Leetcode0552_1().checkRecord(10101));
    }
}
