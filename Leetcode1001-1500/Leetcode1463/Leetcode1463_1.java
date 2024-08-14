package Leetcode1463;

import java.util.*;

public class Leetcode1463_1 {
    int[][] dirs = new int[][]{{-1, -1},{-1, 0}, {-1, 1}, {0, -1}, {0, 0},{0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) Arrays.fill(dp[i][j], -1);
        }
        dp[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    //(j,k)可以从(j-1,k-1)、(j-1,k)、(j-1,k+1)、(j,k-1)、(j,k)、(j,k+1)、(j+1,k-1)、(j+1,k)、(j+1,k+1)过来
                    for(int[] dir : dirs){
                        int j1 = j + dir[0];
                        int k1 = k + dir[1];
                        if(j1 < 0 || j1 >= n || k1 < 0 || k1 >= n || dp[i - 1][j1][k1] == -1) continue;
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j1][k1] + grid[i][j] + (j == k ? 0 : grid[i][k]));
                    }
                }
            }
        }
        int ans = 0;
        for(int j = 0; j < n; j++){
            for(int k = 0; k < n; k++){
                ans = Math.max(ans, dp[m - 1][j][k]);
            }
        }
        return ans;
    }
}
