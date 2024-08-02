package Leetcode1192;

import java.util.*;

public class Leetcode1192_1 {
    int clock = 0;
    int[] time;
    int[] firstTime;
    int n;
    List<Integer>[] g;
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.n = n;
        time = new int[n];
        firstTime = new int[n];
        g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(List<Integer> conn : connections){
            int x = conn.get(0);
            int y = conn.get(1);
            g[x].add(y);
            g[y].add(x);
        }
        dfs(0,-1);
        return res;
    }
    public void dfs(int cur, int parent){
        time[cur] = ++clock;
        firstTime[cur] = clock;
        for(int next : g[cur]){
            if(next == parent) continue;
            if(time[next] == 0){
                dfs(next, cur);
                firstTime[cur] = Math.min(firstTime[cur], firstTime[next]);
                if(firstTime[next] > time[cur]){
                    res.add(Arrays.asList(cur, next));
                }
            }
            else if(time[next] < time[cur]){
                firstTime[cur] = Math.min(firstTime[cur], firstTime[next]);
            }
        }
    }
}
