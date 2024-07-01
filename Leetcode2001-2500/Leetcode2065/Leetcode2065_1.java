package Leetcode2065;

import java.util.*;

public class Leetcode2065_1 {
    int n;
    int[] values;
    int maxTime;
    List<int[]>[] g;
    int ans = 0;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        n = values.length;
        this.values = values;
        this.maxTime = maxTime;
        g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int t = edge[2];
            g[x].add(new int[]{y,t});
            g[y].add(new int[]{x,t});
        }
        ans = values[0];
        dfs(0, 0, 0, new boolean[n]);
        return ans;
    }
    public void dfs(int cur, int time, int curAns, boolean[] vis) {
        if(time > maxTime) return;
        if(cur == 0) ans = Math.max(ans, curAns);
        for(int[] tmp : g[cur]) {
            int next = tmp[0];
            int nextT = tmp[1];
            boolean flag = vis[next];
            vis[next] = true;
            dfs(next, time + nextT, flag? curAns:curAns + values[next], vis);
            vis[next] = flag;
        }
    }
    public static void main(String[] args) {
        int[] values = {0,32,10,43};
        int edges[][] = new int[][]{{0,1,10},{1,2,15},{0,3,10}};
        System.out.println(new Leetcode2065_1().maximalPathQuality(values, edges, 49));
    }
}
