package Leetcode1345;

import java.util.*;

public class Leetcode1345_1 {
    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> numInxMap = new HashMap<>();
        for(int i = 0; i < n; i++) numInxMap.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        int[] dis = new int[n];
        Arrays.fill(dis, 0x3f3f3f3f);
        dis[0] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur == n - 1) return dis[cur];
            if(cur + 1 < n && dis[cur + 1] > dis[cur] + 1){
                dis[cur + 1] = dis[cur] + 1;
                q.add(cur + 1);
            }
            if(cur - 1 >= 0 && dis[cur - 1] > dis[cur] + 1){
                dis[cur - 1] = dis[cur] + 1;
                q.add(cur - 1);
            }
            if(numInxMap.containsKey(arr[cur])){
                for(int next : numInxMap.get(arr[cur])){
                    if(dis[next] > dis[cur] + 1){
                        dis[next] = dis[cur] + 1;
                        q.add(next);
                    }
                }
                numInxMap.remove(arr[cur]);
            }
        }
        return -1;
    }
}
