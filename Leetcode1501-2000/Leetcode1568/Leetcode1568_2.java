package Leetcode1568;

import java.util.*;

public class Leetcode1568_2 {
    int m;
    int n;
    int total = 0;
    int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
    int[][] grid;
    public int minDays(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        List<int[]> list1 = new ArrayList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    total++;
                    list1.add(new int[]{i,j});
                }
            }
        }
        if(total == 0) return 0;
        if(total == 1) return 1;
        //先看看是不是已经多个陆地了
        int x1 = list1.get(0)[0];
        int y1 = list1.get(0)[1];
        int cnt0 = dfsCnt(x1, y1, new boolean[m][n]);
        if(cnt0 < total) return 0;
        for(int i = 0; i < total; i++){
            int xCheck = list1.get(i)[0];
            int yCheck = list1.get(i)[1];
            grid[xCheck][yCheck] = 0;
            int pos = ((i+1 == total)? 0:i+1);
            int x = list1.get(pos)[0];
            int y = list1.get(pos)[1];
            int cnt = dfsCnt(x, y, new boolean[m][n]);
            if(cnt < total-1) return 1;
            grid[xCheck][yCheck] = 1;
        }
        return 2;
    }
    public int dfsCnt(int x, int y, boolean[][] vis){
        if(vis[x][y]) return 0;
        vis[x][y] = true;
        int res = 1;
        for(int[] dir : dirs){
            int x2 = x + dir[0];
            int y2 = y + dir[1];
            if(x2 < 0 || x2 >= m || y2 < 0 || y2 >= n) continue;
            if(vis[x2][y2] || grid[x2][y2] == 0) continue;
            res += dfsCnt(x2, y2, vis);
        }
        return res;
    }
}
