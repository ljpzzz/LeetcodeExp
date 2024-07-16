package Leetcode0675;

import java.util.*;

public class Leetcode0675_1 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m;
    int n;
    int INF = 0x3f3f3f3f;
    public int cutOffTree(List<List<Integer>> forest) {
        if(forest.get(0).get(0) == 0) return -1;
        m = forest.size();
        n = forest.get(0).size();
        int[][][][] distance = new int[m][n][m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2]-b[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(forest.get(i).get(j) == 0) continue;
                distance[i][j] = bfsDistance(forest, i, j);
                for (int k = 0; k < m; k++) {
                    for (int l = 0; l < n; l++) {
                        if(forest.get(k).get(l) == 0) continue;
                        if(distance[i][j][k][l] == INF) return -1;
                    }
                }
                if(forest.get(i).get(j) > 1) pq.offer(new int[]{i, j, forest.get(i).get(j)});
            }
        }
        int ans = 0;
        int preX = 0;
        int preY = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            ans += distance[preX][preY][x][y];
            preX = x;
            preY = y;
        }
        return ans;
    }
    public int[][] bfsDistance (List<List<Integer>> forest, int x, int y) {
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) ans[i][j] = INF;
        }
        ans[x][y] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});
        boolean[][] visited = new boolean[m][n];
        visited[x][y] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x1 = cur[0];
            int y1 = cur[1];
            int dis1 = cur[2];
            for (int[] dir : dirs) {
                int x2 = x1 + dir[0];
                int y2 = y1 + dir[1];
                if(x2 < 0 || x2 >= m || y2 < 0 || y2 >= n || forest.get(x2).get(y2) == 0 || visited[x2][y2]) continue;
                ans[x2][y2] = dis1 + 1;
                queue.offer(new int[]{x2, y2, dis1 + 1});
                visited[x2][y2] = true;
            }
        }
        return ans;
    }
}
