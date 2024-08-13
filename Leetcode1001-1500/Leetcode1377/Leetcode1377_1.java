package Leetcode1377;

import java.util.*;

public class Leetcode1377_1 {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if(n == 1) return 1; //没有边，永远在根节点
        if(target == 1) return 0; //此时不止一个节点， t>=1，不可能还在根节点
        List<Integer>[] g = new List[n + 1];
        for(int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        int[] degrees = new int[n + 1];
        for(int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }
        long[][] distance = new long[n + 1][2]; //第一个值是到达的时间，第二个值是到达的概率，只保存分母
        for(int i = 1; i <= n; i++) Arrays.fill(distance[i], Long.MAX_VALUE/3);
        distance[1][0] = 0;
        distance[1][1] = 1;
        Queue<long[]> queue = new ArrayDeque<>(); // node, pre, time, prob
        queue.offer(new long[]{1, 0, 0, 1});
        while(!queue.isEmpty()){
            long[] cur = queue.poll();
            int node = (int)cur[0]; int prev = (int)cur[1]; int time = (int)cur[2]; long prob = cur[3];
            for(int nextNode : g[node]){
                if(nextNode == prev) continue;
                int childCnt = degrees[node] - 1;
                if(node == 1) childCnt = degrees[node];
                if(time + 1 <= t && distance[nextNode][0] > time + 1){
                    distance[nextNode][0] = time + 1;
                    distance[nextNode][1] = prob*childCnt;
                    queue.offer(new long[]{nextNode, node, time + 1, prob*childCnt});
                }
            }
        }
        if(distance[target][0] > t) return 0; //到不了
        else if(distance[target][0] == t) return 1.0d/distance[target][1]; //恰好到达
        else{
            if(degrees[target] == 1) return 1.0d/distance[target][1]; //叶子节点
            else return 0; //不是叶子节点，已经走了
        }
    }
}
