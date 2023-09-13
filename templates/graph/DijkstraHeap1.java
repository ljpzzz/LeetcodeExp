package graph;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
/* 迪杰特斯加最大堆解法+邻接表*/
public class DijkstraHeap1 {
    public long[] dijkstra(int n, int source, List<int[]>[] g){
        long[] distance = new long[n];
        Arrays.fill(distance, 0x3f3f3f3f);
        distance[source] = 0L;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b)->{
            if(a[1] < b[1]) return -1;
            else if(a[1] > b[1]) return 1;
            else return 0;
        });
        pq.add(new long[]{source, 0L});

        while(pq.size() > 0){
            long[] tmp = pq.poll();
            int cur = (int)tmp[0];
            if(distance[cur] < tmp[1]) continue;
            for(int[] gCur : g[cur]){
                int next = gCur[0];
                int weight = gCur[1];
                if(distance[next] > distance[cur] + weight){
                    distance[next] = distance[cur] + weight;
                    pq.add(new long[]{next, distance[next]});
                }
            }
        }
        return distance;
    }
    public long dijkstra2(int n, int source, int dest, List<int[]>[] g){
        long[] distance = new long[n];
        Arrays.fill(distance, 0x3f3f3f3f);
        distance[source] = 0L;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b)->{
            if(a[1] < b[1]) return -1;
            else if(a[1] > b[1]) return 1;
            else return 0;
        });
        pq.add(new long[]{source, 0L});

        while(pq.size() > 0){
            long[] tmp = pq.poll();
            int cur = (int)tmp[0];
            if(cur == dest) return tmp[1];
            if(distance[cur] < tmp[1]) continue;
            for(int[] gCur : g[cur]){
                int next = gCur[0];
                int weight = gCur[1];
                if(distance[next] > distance[cur] + weight){
                    distance[next] = distance[cur] + weight;
                    pq.add(new long[]{next, distance[next]});
                }
            }
        }
        return -1;
    }
}
