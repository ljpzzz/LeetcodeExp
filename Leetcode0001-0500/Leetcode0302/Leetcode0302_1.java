package Leetcode0302;

import java.util.*;

public class Leetcode0302_1 {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public int minArea(char[][] image, int x, int y) {
        int xMax = x;
        int xMin = x;
        int yMax = y;
        int yMin = y;
        int m = image.length;
        int n = image[0].length;
        boolean[][] vis = new boolean[m][n];
        vis[x][y] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y});
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int curX = tmp[0];
            int curY = tmp[1];
            for(int[] dir : dirs){
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];
                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n
                        || vis[nextX][nextY] || image[nextX][nextY] == '0') continue;
                xMax = Math.max(nextX, xMax);
                xMin = Math.min(nextX, xMin);
                yMax = Math.max(nextY, yMax);
                yMin = Math.min(nextY, yMin);
                vis[nextX][nextY] = true;
                q.add(new int[]{nextX, nextY});
            }
        }
        return (yMax-yMin+1)*(xMax-xMin+1);
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0302_1().minArea(new char[][]{
                {'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}
        },0,2));
        System.out.println(new Leetcode0302_1().minArea(new char[][]{
                {'1'}
        },0,0));
    }
}
