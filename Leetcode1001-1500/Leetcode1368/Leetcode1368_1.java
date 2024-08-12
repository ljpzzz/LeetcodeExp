package Leetcode1368;

import java.util.*;

public class Leetcode1368_1 {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        dp[0][0] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0}); // x, y, cost
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], cost = cur[2];
            if(x == m - 1 && y == n - 1) return cost;
            for(int[] dir : dirs){
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(ny == y + 1){
                    if(grid[x][y] == 1 && dp[nx][ny] > cost){
                        dp[nx][ny] = cost;
                        q.addFirst(new int[]{nx, ny, cost});
                    }
                    else if(grid[x][y] != 1 && dp[nx][ny] > cost + 1){
                        dp[nx][ny] = cost + 1;
                        q.addLast(new int[]{nx, ny, cost + 1});
                    }
                }
                if(ny == y - 1){
                    if(grid[x][y] == 2 && dp[nx][ny] > cost){
                        dp[nx][ny] = cost;
                        q.addFirst(new int[]{nx, ny, cost});
                    }
                    else if(grid[x][y] != 2 && dp[nx][ny] > cost + 1){
                        dp[nx][ny] = cost + 1;
                        q.addLast(new int[]{nx, ny, cost + 1});
                    }
                }
                if(nx == x + 1){
                    if(grid[x][y] == 3 && dp[nx][ny] > cost){
                        dp[nx][ny] = cost;
                        q.addFirst(new int[]{nx, ny, cost});
                    }
                    else if(grid[x][y] != 3 && dp[nx][ny] > cost + 1){
                        dp[nx][ny] = cost + 1;
                        q.addLast(new int[]{nx, ny, cost +1});
                    }
                }
                if(nx == x - 1){
                    if(grid[x][y] == 4 && dp[nx][ny] > cost){
                        dp[nx][ny] = cost;
                        q.addFirst(new int[]{nx, ny, cost});
                    }
                    else if(grid[x][y] != 4 && dp[nx][ny] > cost + 1){
                        dp[nx][ny] = cost + 1;
                        q.addLast(new int[]{nx, ny, cost + 1});
                    }
                }
            }
        }
        return -1;
    }
}
