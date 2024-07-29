package Leetcode0980;

import java.util.*;

public class Leetcode0980_2 {
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int BASE = Math.max(m,n)+1;
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        int initMask = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
                else if(grid[i][j] == 2){
                    endX = i;
                    endY = j;
                }
                else if(grid[i][j] == -1) initMask |= (1<<(n*i+j));
            }
        }
        //int[]包含3个值，BFS的横坐标，纵坐标，不能再访问的点的mask
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startX, startY, initMask});
        int total = 0;
        while(queue.size() > 0){
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int mask = tmp[2];
            mask |= (1<<(x*n+y));
            if(x == endX && y == endY){
                if(mask == ((1<<(m*n))-1)) total++;
                continue;
            }
            if(x-1 >= 0 && (mask&(1<<((x-1)*n+y))) == 0) queue.add(new int[]{x-1, y, mask});
            if(x+1 < m && (mask&(1<<((x+1)*n+y))) == 0) queue.add(new int[]{x+1, y, mask});
            if(y-1 >= 0 && (mask & (1<<(x*n+y-1))) == 0) queue.add(new int[]{x, y-1, mask});
            if(y+1 < n && (mask & (1<<(x*n+y+1))) == 0) queue.add(new int[]{x, y+1, mask});
        }
        return total;
    }
}
