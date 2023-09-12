package graph;

import java.util.Arrays;
import java.util.List;
/* 迪杰特斯加暴力解法+邻接表*/
public class DijkstraClassic2 {
    public long dijkstra(int n, int source, int dest, List<int[]>[] g) {
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
            for(int[] tmp : g[minIndex]){
                int next = tmp[0];
                int cost = tmp[1];
                if(dis[next] > dis[minIndex] + cost) dis[next] = dis[minIndex] + cost;
            }
        }
        return dis[dest];
    }
}
