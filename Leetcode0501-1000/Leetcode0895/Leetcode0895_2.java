package Leetcode0895;

import java.util.*;

public class Leetcode0895_2 {
    Map<Integer, Integer> numCntMap;
    PriorityQueue<int[]> pq; //int[]包括值，数量和编号
    int total = 0;
    public Leetcode0895_2() {
        numCntMap = new HashMap<>();
        pq = new PriorityQueue<>((a,b)->{
            if(a[1] != b[1]) return b[1]-a[1];
            else return b[2]-a[2];
        });
    }

    public void push(int val) {
        int cnt = numCntMap.getOrDefault(val, 0);
        numCntMap.put(val, 1+cnt);
        pq.add(new int[]{val, 1+cnt, total++});
    }
    public int pop() {
        int[] tmp = pq.poll();
        int cnt = numCntMap.get(tmp[0]);
        numCntMap.put(tmp[0], cnt-1);
        return tmp[0];
    }
}
