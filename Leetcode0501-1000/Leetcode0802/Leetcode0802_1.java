package Leetcode0802;

import java.util.*;

public class Leetcode0802_1 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<Integer>[] gRev = new ArrayList[n];
        int[] degrees = new int[n];
        for (int i = 0; i < n; i++) gRev[i] = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int next : graph[i]) {
                gRev[next].add(i);
                degrees[i]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            if(degrees[i] == 0){
                q.offer(i);
                ans.add(i);
            }
        }
        while(q.size() > 0){
            int cur = q.poll();
            for(int next : gRev[cur]) {
                degrees[next]--;
                if(degrees[next] == 0) {
                    q.offer(next);
                    ans.add(next);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
