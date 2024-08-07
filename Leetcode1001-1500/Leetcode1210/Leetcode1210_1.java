package Leetcode1210;

import java.util.*;

public class Leetcode1210_1 {
    int RIGHT = 0; //蛇头向右
    int DOWN = 1; //蛇头向下
    public int minimumMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //int[] 哥值， 蛇头横坐标，蛇头纵坐标，蛇头方向, 距离
        Queue<int[]> q = new ArrayDeque<>();
        int[][][] dis = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) Arrays.fill(dis[i][j], 0x3f3f3f3f);
        }
        q.add(new int[]{0,1,RIGHT,0});
        dis[0][1][RIGHT] = 0;
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int status = tmp[2];
            int distance = tmp[3];
            if(x == m-1 && y == n-1 && status == RIGHT) return distance;
            if(status == RIGHT){
                //可以向右移动, 前提是在界内，且不是障碍，距离不会变大
                if(y+1 < n && dis[x][y+1][RIGHT] > distance+1 && grid[x][y+1] == 0){
                    dis[x][y+1][RIGHT] = distance+1;
                    q.add(new int[]{x,y+1,RIGHT,distance+1});
                }
                //可以向下平移,前提是在界内，且不是障碍，距离不会变大
                if(x+1 < m && dis[x+1][y][RIGHT] > distance+1 && grid[x+1][y-1] == 0 && grid[x+1][y] == 0){
                    dis[x+1][y][RIGHT] = distance+1;
                    q.add(new int[]{x+1,y,RIGHT,distance+1});
                }
                //可以向下旋转,前提是在界内，且不是障碍，距离不会变大
                if(x+1 < m && dis[x+1][y-1][DOWN] > distance+1 && grid[x+1][y-1] == 0 && grid[x+1][y] == 0){
                    dis[x+1][y-1][DOWN] = distance+1;
                    q.add(new int[]{x+1,y-1,DOWN,distance+1});
                }
            }
            else{
                //可以向下移动,前提是在界内，且不是障碍，距离不会变大
                if(x+1 < m && dis[x+1][y][DOWN] > distance+1 && grid[x+1][y] == 0){
                    dis[x+1][y][DOWN] = distance+1;
                    q.add(new int[]{x+1,y,DOWN,distance+1});
                }
                //可以向右平移，前提是在界内，且不是障碍，距离不会变大
                if(y+1 < n && dis[x][y+1][DOWN] > distance+1 && grid[x-1][y+1] == 0 && grid[x][y+1] == 0){
                    dis[x][y+1][DOWN] = distance+1;
                    q.add(new int[]{x,y+1,DOWN,distance+1});
                }
                //可以向右旋转，前提是在界内，且不是障碍，距离不会变大
                if(y+1 < n && dis[x-1][y+1][RIGHT] > distance+1 && grid[x-1][y+1] == 0 && grid[x][y+1] == 0){
                    dis[x-1][y+1][RIGHT] = distance+1;
                    q.add(new int[]{x-1,y+1,RIGHT,distance+1});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0,0,1},{1,1,0,0,1,0},{0,0,0,0,1,1},{0,0,1,0,1,0},{0,1,1,0,0,0},{0,1,1,0,0,0}};
        Leetcode1210_1 leetcode1210_1 = new Leetcode1210_1();
        System.out.println(leetcode1210_1.minimumMoves(grid));
    }
}
