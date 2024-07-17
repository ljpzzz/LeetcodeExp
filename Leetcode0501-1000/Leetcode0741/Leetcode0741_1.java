package Leetcode0741;

public class Leetcode0741_1 {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        //dp[s][i][j]表示走了s步, 第一个行程横坐标在位置i，第二个行程在位置j，摘到的樱桃数量
        int[][][] dp = new int[2*n-1][n][n];
        dp[0][0][0] = grid[0][0];
        for(int s = 1; s < 2*n-1; s++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(!isValid(grid, s, i, j, n)) continue;
                    int max1 = Math.max(getDp(grid, dp, s-1, i, j-1, n), getDp(grid, dp, s-1, i, j, n));
                    int max2 = Math.max(getDp(grid, dp, s-1, i-1, j, n), getDp(grid, dp, s-1, i-1, j-1, n));
                    dp[s][i][j] = Math.max(max1, max2);
                    if(dp[s][i][j] == -1) continue;
                    if(i == j) dp[s][i][j] += grid[i][s-i];
                    else dp[s][i][j] += grid[i][s-i] + grid[j][s-j];
                }
            }
        }
        return dp[2*n-2][n-1][n-1] == -1 ? 0 : dp[2*n-2][n-1][n-1];
    }
    public boolean isValid(int[][] grid, int s, int i ,int j, int n) {
        int x1 = s - i, x2 = s - j;
        if(x1 >= 0 && x1 < n && x2 >= 0 && x2 < n && i >= 0 && j >= 0 && i < n && j < n && grid[i][x1] != -1 && grid[j][x2] != -1)
            return true;
        return false;
    }
    public int getDp(int[][] grid, int[][][] dp, int s, int i , int j, int n) {
        if(isValid(grid,s, i, j, n))
            return dp[s][i][j];
        return -1;
    }
}
