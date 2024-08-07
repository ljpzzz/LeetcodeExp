package Leetcode1263;

import java.util.*;

public class Leetcode1263_1 {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minPushBox(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int sx = -1, sy = -1, bx = -1, by = -1, tx = -1, ty = -1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }
                else if(grid[i][j] == 'B'){
                    bx = i;
                    by = j;
                }
                else if(grid[i][j] == 'T'){
                    tx = i;
                    ty = j;
                }
            }
        }
        int[][][][] dp = new int[m][n][m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < m; k++) Arrays.fill(dp[i][j][k], -1);
            }
        }
        dp[sx][sy][bx][by] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, bx, by, 0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int px = cur[0], py = cur[1], boxX = cur[2], boxY = cur[3]; int dis = cur[4];
            if(boxX == tx && boxY == ty) return dis;
            for(int[] dir : dirs){
                int nx = px + dir[0], ny = py + dir[1];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == '#') continue;
                if(nx == boxX && ny == boxY){
                    int boxXX = boxX + dir[0], boxYY = boxY + dir[1];
                    if(boxXX < 0 || boxXX >= m || boxYY < 0 || boxYY >= n || grid[boxXX][boxYY] == '#') continue;
                    if(dp[nx][ny][boxXX][boxYY] == -1 || dp[nx][ny][boxXX][boxYY] > dis + 1){
                        dp[nx][ny][boxXX][boxYY] = dis + 1;
                        q.addLast(new int[]{nx, ny, boxXX, boxYY, dis + 1});
                    }
                }
                else if(dp[nx][ny][boxX][boxY] == -1 || dp[nx][ny][boxX][boxY] > dis){
                    dp[nx][ny][boxX][boxY] = dis;
                    q.addFirst(new int[]{nx, ny, boxX, boxY, dis});
                }
            }
        }
        return -1;
    }
}
