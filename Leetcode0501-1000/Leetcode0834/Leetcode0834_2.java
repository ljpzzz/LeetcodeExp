package Leetcode0834;

import java.util.*;

public class Leetcode0834_2 {
    int[] distance;
    int[] size;
    List<Integer>[] g;
    int[] res;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        distance = new int[n];
        size = new int[n];
        g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        dfs(0, -1);
        res = new int[n];
        dfsSwap(0,-1);
        return res;
    }
    public int[] dfs(int current , int parent){
        int dis = 0;
        int amount = 1;
        for(int next : g[current]){
            if(next == parent) continue;
            int[] tmp = dfs(next, current);
            dis += tmp[0] + tmp[1];
            amount += tmp[1];
        }
        distance[current] = dis;
        size[current] = amount;
        return new int[]{dis, amount};
    }
    public void dfsSwap(int current , int parent){
        res[current] = distance[current];
        for(int next : g[current]){
            if(next == parent) continue;
            int preCurrent = distance[current];
            int preSize = size[current];
            int preNext = distance[next];
            int preSize2 = size[next];

            distance[current] -= distance[next]+size[next];
            size[current] -= size[next];
            distance[next] += distance[current]+size[current];
            size[next] += size[current];
            dfsSwap(next, current);

            distance[current] = preCurrent;
            size[current] = preSize;
            distance[next]  = preNext;
            size[next] = preSize2;
        }
    }
}
