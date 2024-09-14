package Leetcode1761;

import java.util.*;

public class Leetcode1761_1 {
    public int minTrioDegree(int n, int[][] edges) {
        Set<Integer>[] g = new HashSet[n];
        for(int i = 0; i < n; i++) g[i] = new HashSet<>(); //建立有向图，便于去重
        int[] degrees = new int[n]; //度还是2个方向都算
        for(int[] edge: edges){
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            if(x < y) g[x].add(y);
            else g[y].add(x);
            degrees[x]++;
            degrees[y]++;
        }
        int ans = Integer.MAX_VALUE;
        for(int[] edge : edges){
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            for(int z : g[x]){
                if(z == x || z == y) continue;
                if(g[y].contains(z) && g[x].contains(z)){
                    ans = Math.min(ans, degrees[x] + degrees[y] + degrees[z] - 6);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
