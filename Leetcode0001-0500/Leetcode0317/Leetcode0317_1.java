package Leetcode0317;

import java.util.*;

public class Leetcode0317_1 {
    class Solution {
        int m;
        int n;
        int[][] grid;
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        List<int[]> posList = new ArrayList<>();
        public int shortestDistance(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            this.grid = grid;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(grid[i][j] == 1) posList.add(new int[]{i,j});
                }
            }
            int ans = -1;
            for(int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(grid[i][j] != 0) continue;
                    int curAns = 0;
                    boolean isOK = true;
                    int[][] dis = getDis(i,j);
                    for(int[] pos : posList){
                        if(dis[pos[0]][pos[1]] == -1){
                            isOK = false;
                            break;
                        }
                        else curAns += dis[pos[0]][pos[1]];
                    }
                    if(isOK){
                        //System.out.println("pos (" + i + ", " + j + ") total distance:" + curAns);
                        if(ans == -1 || ans > curAns) ans = curAns;
                    }
                }
            }
            return ans;
        }
        public int[][] getDis(int x, int y){
            int[][] ans = new int[m][n];
            for(int i = 0; i < m; i++) Arrays.fill(ans[i], -1);
            ans[x][y] = 0;
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{x,y});
            while(q.size() > 0){
                int[] tmp = q.poll();
                int curX = tmp[0];
                int curY = tmp[1];
                for(int[] dir : dirs){
                    int nextX = curX + dir[0];
                    int nextY = curY + dir[1];
                    if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n ||
                            grid[nextX][nextY] == 2 || ans[nextX][nextY] >= 0) continue;
                    ans[nextX][nextY] = ans[curX][curY]+1;
                    if(grid[nextX][nextY] == 0) q.add(new int[]{nextX, nextY});
                }
            }
            return ans;
        }
    }
}
