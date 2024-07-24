package Leetcode0882;

import java.util.*;

public class Leetcode0882_1 {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, int[]>[] g = new Map[n];
        for(int i = 0; i < n; i++) g[i] = new HashMap<Integer, int[]>();
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            g[x].put(y, new int[]{w, 0});
            g[y].put(x, new int[]{w, 0});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, 0x3f3f3f3f);
        distance[0] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->(a[1]-b[1]));
        q.add(new int[]{0, 0}); //node, moves
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0];
            int moves = cur[1];
            for(int next : g[node].keySet()){
                int nextMove = g[node].get(next)[0];
                if(moves + nextMove + 1 < distance[next] && moves + nextMove + 1 <= maxMoves) {
                    distance[next] = moves + nextMove + 1;
                    g[node].get(next)[1] = nextMove;
                    q.add(new int[]{next, distance[next]});
                }
                else {
                    g[node].get(next)[1] = Math.max(g[node].get(next)[1], maxMoves - moves);
                    if(g[node].get(next)[1] > g[node].get(next)[0]) g[node].get(next)[1] = nextMove;
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(distance[i] <= maxMoves) ans++;
        }
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            int xy = g[x].get(y)[1];
            int yx = g[y].get(x)[1];
            if(xy + yx >= w) ans += w;
            else ans += xy + yx;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] edges = {{0,1,10},{0,2,1},{1,2,2}};
        int maxMoves = 6;
        int n = 3;
        Leetcode0882_1 test = new Leetcode0882_1();
        System.out.println(test.reachableNodes(edges, maxMoves, n));
    }
}
