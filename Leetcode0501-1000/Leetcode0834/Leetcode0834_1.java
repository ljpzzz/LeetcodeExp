package Leetcode0834;

import java.util.*;

public class Leetcode0834_1 {
    int total = 0;
    int[] depth;
    int[] nodeCnt;
    List<Integer>[] g;
    int[] ans;
    int n;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        this.n = n;
        g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }

        depth = new int[n];
        Arrays.fill(depth, -1);
        depth[0] = 0;
        dfsDepth(0, -1 , 0);

        nodeCnt = new int[n];
        Arrays.fill(nodeCnt, -1);
        dfsNodeCnt(0, -1);

        for(int i = 0; i < n; i++) total += depth[i];

        ans = new int[n];
        dfsSwap(0, -1);
        return ans;
    }
    public void dfsDepth(int cur, int prev, int dis){
        depth[cur] = dis;
        for(int next : g[cur]){
            if(next == prev) continue;
            dfsDepth(next, cur, dis+1);
        }
    }
    public int dfsNodeCnt(int cur, int prev){
        if(nodeCnt[cur] != -1) return nodeCnt[cur];
        int cnt = 1;
        for(int next : g[cur]){
            if(next == prev) continue;
            cnt += dfsNodeCnt(next, cur);
        }
        nodeCnt[cur] = cnt;
        return cnt;
    }
    public void dfsSwap(int cur, int prev){
        ans[cur] = total;
        for(int next : g[cur]){
            if(next == prev) continue;
            int preCnt = total;
            total -= nodeCnt[next];
            total += n-nodeCnt[next];
            dfsSwap(next, cur);
            total = preCnt;
        }
    }
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
        Leetcode0834_1 test = new Leetcode0834_1();
        int[] ans = test.sumOfDistancesInTree(n, edges);
        for(int i : ans) System.out.print(i + " ");
    }
}
