package Leetcode1293;

import java.util.*;

public class Leetcode1293_1 {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][k+1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++) Arrays.fill(dp[i][j], 0x3f3f3f3f);
        }
        dp[0][0][k] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, k});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], cnt = cur[2];
            if(x == m-1 && y == n-1) return dp[x][y][cnt];
            for(int[] dir : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}){
                int nx = x + dir[0], ny = y + dir[1];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(grid[nx][ny] == 1 && cnt > 0){
                    if(dp[nx][ny][cnt-1] > dp[x][y][cnt] + 1){
                        dp[nx][ny][cnt-1] = dp[x][y][cnt] + 1;
                        q.add(new int[]{nx, ny, cnt-1});
                    }
                }
                else if(grid[nx][ny] == 0){
                    if(dp[nx][ny][cnt] > dp[x][y][cnt] + 1){
                        dp[nx][ny][cnt] = dp[x][y][cnt] + 1;
                        q.add(new int[]{nx, ny, cnt});
                    }
                }

            }
        }
        return -1;
    }
}
