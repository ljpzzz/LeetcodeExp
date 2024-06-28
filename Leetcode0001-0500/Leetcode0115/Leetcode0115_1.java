package Leetcode0115;

public class Leetcode0115_1 {
    int MOD = 1000000007;
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(m < n) return 0;
        long[][] dp = new long[m+1][n+1];
        dp[0][0] = 1;
        for(int i = 1; i <= m; i++){
            for(int j = 0; j <= n; j++){
                dp[i][j] = dp[i-1][j]; //不选
                if(j > 0 && s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] += dp[i-1][j-1];
                    dp[i][j] %= MOD;
                }
            }
        }
        return (int)dp[m][n];
    }
}
