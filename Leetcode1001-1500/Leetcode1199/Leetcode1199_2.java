package Leetcode1199;

import java.util.*;

public class Leetcode1199_2 {
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->(a-b));
        for(int b : blocks) pq.add(b);
        int ans = 0;
        while(pq.size() > 1){
            pq.poll();
            pq.add(pq.poll()+split);
        }
        return pq.poll();
    }
}
