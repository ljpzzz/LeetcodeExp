package Leetcode1289;

public class Leetcode1289_1 {
    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //dp[i][j]表示第i行以位置j结束时的最小值
        int[][] dp = new int[m][n];
        for(int j = 0; j < n; j++) dp[0][j] = grid[0][j];
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = 0x3f3f3f3f;
                for(int k = 0; k < n; k++){
                    if(j == k) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]);
                }
            }
        }
        int ans = 0x3f3f3f3f;
        for(int j = 0; j < n; j++) ans = Math.min(ans, dp[m - 1][j]);
        return ans;
    }
}
