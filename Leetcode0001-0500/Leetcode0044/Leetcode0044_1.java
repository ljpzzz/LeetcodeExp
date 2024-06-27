package Leetcode0044;

public class Leetcode0044_1 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*' && p.charAt(j - 1) != '?') {
                    if(i >= 1) dp[i][j] = dp[i-1][j-1] && s.charAt(i - 1) == p.charAt(j - 1);
                }
                else if(p.charAt(j - 1) == '?'){
                    if(i >= 1) dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = dp[i][j-1];
                    if(i >= 1) dp[i][j] |= dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0044_1().isMatch("aa", "*"));
    }
}
