package Leetcode0847;

import java.util.*;

public class Leetcode0847_1 {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int[][] dp = new int[n][1 << n];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        for(int i = 0; i < n; i++) dp[i][1 << i] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++) q.offer(new int[]{i, 1 << i, 0});
        while(q.size() > 0){
            int[] cur = q.poll();
            int node = cur[0];
            int mask = cur[1];
            int step = cur[2];
            for(int next : graph[node]){
                int nextMask = mask | (1 << next);
                if(dp[next][nextMask] > step + 1){
                    dp[next][nextMask] = step + 1;
                    q.offer(new int[]{next, nextMask, step + 1});
                }
            }
        }
        int ans = 0x3f3f3f3f;
        for(int i = 0; i < n; i++) ans = Math.min(ans, dp[i][(1 << n) - 1]);
        return ans;
    }
}
