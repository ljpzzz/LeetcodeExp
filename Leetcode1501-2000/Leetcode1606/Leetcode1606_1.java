package Leetcode1606;

import java.util.*;

public class Leetcode1606_1 {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        TreeSet<Integer> available = new TreeSet<>(); //空闲的服务器
        for(int i = 0; i < k; i++) available.add(i);
        int[] serverTaskCnt = new int[k]; //每个服务器处理的任务数
        //int[]三个值：服务器id,任务id,完成时间
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[2]-b[2]);
        for(int i = 0; i < n; i++){
            while(!pq.isEmpty() && pq.peek()[2] <= arrival[i]){
                int[] cur = pq.poll();
                available.add(cur[0]);
            }
            if(available.isEmpty()) continue;
            Integer server = available.ceiling(i%k);
            if(server == null) server = available.first();
            serverTaskCnt[server]++;
            available.remove(server);
            pq.offer(new int[]{server,i,arrival[i]+load[i]});
        }
        int maxCnt = 0;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < k; i++){
            if(serverTaskCnt[i] > maxCnt){
                maxCnt = serverTaskCnt[i];
                ans.clear();
                ans.add(i);
            }
            else if(serverTaskCnt[i] == maxCnt) ans.add(i);
        }
        return ans;
    }
}
