package Leetcode0632;

import java.util.*;

public class Leetcode0632_2 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        int max = Integer.MIN_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(a[0]-b[0]));
        for(int i = 0; i < n; i++) {
            pq.add(new int[]{nums.get(i).get(0), i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }
        int start = Integer.MIN_VALUE;
        int end = -1;
        int len = 0x3f3f3f3f;
        while(pq.size() == n){
            int[] cur  = pq.poll();
            int val = cur[0];
            int inx = cur[1];
            int pos = cur[2];
            if(max-val < len || max-val== len && val < start){
                len = max-val;
                start = val;
                end = max;
            }
            if(pos == nums.get(inx).size()-1) break;
            pq.add(new int[]{nums.get(inx).get(pos+1),inx, pos+1});
            max = Math.max(max, nums.get(inx).get(pos+1));
        }
        return new int[]{start, end};
    }
}
