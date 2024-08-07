package Leetcode1203;

import java.util.*;

public class Leetcode1203_1 {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        List<Integer>[] gItem = new List[n];
        int[] itemDegrees = new int[n];
        for (int i = 0; i < n; i++) {
            gItem[i] = new ArrayList<>();
            if(group[i] == -1) group[i] = m++;
        }
        List<Integer>[] gGroup = new List[m];
        int[] groupDegrees = new int[m];
        for (int i = 0; i < m; i++) gGroup[i] = new ArrayList<>();
        for(int item = 0; item < n; item++){
            for(int pre : beforeItems.get(item)) {
                gItem[pre].add(item);
                itemDegrees[item]++;
                if (group[item] != group[pre]) {
                    gGroup[group[pre]].add(group[item]);
                    groupDegrees[group[item]]++;
                }
            }
        }
        List<Integer> itemTopo = topo(n, gItem, itemDegrees);
        if(itemTopo.size() != n) return new int[0];
        List<Integer> groupTopo = topo(m, gGroup, groupDegrees);
        if(groupTopo.size() != m) return new int[0];
        Map<Integer, List<Integer>> grpItems = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int item = itemTopo.get(i);
            int grp = group[item];
            grpItems.computeIfAbsent(grp, k -> new ArrayList<>()).add(item);
        }
        int[] ans  = new int[n];
        int inx = 0;
        for(int grp : groupTopo){
            if(grpItems.get(grp) == null) continue;
            List<Integer> items = grpItems.get(grp);
            for(int item : items) ans[inx++] = item;
        }
        return ans;
    }
    public List<Integer> topo(int n, List<Integer>[] g, int[] degrees){
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++) if(degrees[i] == 0) q.offer(i);
        while(!q.isEmpty()){
            int cur = q.poll();
            ans.add(cur);
            for(int next : g[cur]){
                degrees[next]--;
                if(degrees[next] == 0) q.offer(next);
            }
        }
        return ans.size() == n ? ans : new ArrayList<>();
    }
}
