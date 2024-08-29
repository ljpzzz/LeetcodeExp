package Leetcode0994;

import java.util.*;

public class Leetcode0994_1 {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int cnt1 = 0;
        boolean[][] vis = new boolean[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1) cnt1++;
                else if(grid[i][j] == 2){
                    q.offer(new int[]{i,j,0});
                    vis[i][j] = true;
                }
            }
        }
        int maxDay = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];
            maxDay = Math.max(maxDay, t);
            for(int[] dir : dirs){
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n || vis[nx][ny] || grid[nx][ny] == 0) continue;
                vis[nx][ny] = true;
                cnt1--;
                q.offer(new int[]{nx,ny,t+1});
            }
        }
        if(cnt1 == 0) return maxDay;
        return -1;
    }
}
