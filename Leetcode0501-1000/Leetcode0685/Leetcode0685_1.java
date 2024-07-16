package Leetcode0685;

import java.util.*;

public class Leetcode0685_1 {
    //枚举+拓补排序
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        for(int i = n-1; i >= 0; i--){
            List<Integer>[] g = new ArrayList[n+1];
            for(int j = 1; j <= n; j++) g[j] = new ArrayList<>();
            int[] degrees = new int[n+1];
            for(int j = 0; j < n; j++){
                if(j == i) continue;
                int u = edges[j][0], v = edges[j][1];
                g[u].add(v);
                degrees[v]++;
            }
            Deque<Integer> q = new ArrayDeque<>();
            Set<Integer> vis = new HashSet<>();
            for(int j = 1; j <= n; j++){
                if(degrees[j] == 0) {
                    q.offer(j);
                    vis.add(j);
                }
            }
            if(q.size() != 1) continue;
            while(!q.isEmpty()){
                int u = q.poll();
                for(int v : g[u]){
                    degrees[v]--;
                    if(degrees[v] == 0) {
                        q.offer(v);
                        vis.add(v);
                    }
                }
            }
            if(vis.size() == n) return new int[]{edges[i][0], edges[i][1]};
        }
        return new int[]{-1,-1};
    }
    public static void main(String[] args) {
        int[][] edges = {{1,2},{2,3},{3,4},{4,1},{1,5}};
        Leetcode0685_1 test = new Leetcode0685_1();
        int[] ans = test.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(ans));
    }
}
