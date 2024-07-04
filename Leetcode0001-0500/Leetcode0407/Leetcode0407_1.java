package Leetcode0407;

import java.util.PriorityQueue;

public class Leetcode0407_1 {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || i == m - 1 || j == 0 || j == n - 1){
                    pq.add(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        int ans = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int height = cur[2];
            for(int[] dir : dirs){
                int newX = x + dir[0];
                int newY = y + dir[1];
                if(newX < 0 || newX >= m || newY < 0 || newY >= n || visited[newX][newY]) continue;
                if(heightMap[newX][newY] < height) ans += height - heightMap[newX][newY];
                pq.add(new int[]{newX, newY, Math.max(heightMap[newX][newY], height)});
                visited[newX][newY] = true;
            }
        }
        return ans;
    }
}
