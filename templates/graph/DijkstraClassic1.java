package graph;

import java.util.Arrays;
/* 迪杰特斯加暴力解法+邻接矩阵*/
public class DijkstraClassic1 {
    public long dijkstra(int n, int source, int dest, int[][] g) {
        long[] dis = new long[n];
        Arrays.fill(dis, 0x3f3f3f3f);
        dis[source] = 0;
        boolean[] vis = new boolean[n];
        for (int times = 0; times < n; times++) {
            int minIndex = -1;
            long mindis = 0x3f3f3f3f;
            for (int i = 0; i < n; i++) {
                if (!vis[i] && dis[i] < mindis) {
                    minIndex = i;
                    mindis = dis[i];
                }
            }
            vis[minIndex] = true;
            for (int i = 0; i < n; i++) {
                if (!vis[i] && g[minIndex][i] != -1 && dis[minIndex] + g[minIndex][i] < dis[i]) {
                    dis[i] = dis[minIndex] + g[minIndex][i];
                }
            }
        }
        return dis[dest];
    }
}
