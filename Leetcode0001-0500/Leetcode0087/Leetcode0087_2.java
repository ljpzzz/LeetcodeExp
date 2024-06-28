package Leetcode0087;

public class Leetcode0087_2 {
    //DP
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        // dp[i][j][k]表示s1[i,i+k-1]和s2[j,j+k-1]是否可以构成等价子串
        boolean[][][] dp = new boolean[n][n][n + 1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for(int k = 2; k <= n; k++){
            for(int i = 0; i <= n - k; i++){
                for(int j = 0; j <= n - k; j++){
                    for(int t = 1; t < k; t++){
                        if((dp[i][j][t] && dp[i + t][j + t][k - t]) || (dp[i][j + k - t][t] && dp[i + t][j][k - t])){
                            dp[i][j][k] = true;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
