package Leetcode0403;

import java.util.*;

public class Leetcode0403_2 {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        if(stones[0] != 0 || stones[1] != 1) return false;
        Map<Integer, Integer> valInxMap = new HashMap<>();
        for(int i = 0; i < n; i++) valInxMap.put(stones[i], i);
        if(n == 2) return true;
        //int[]2个值，当前位置pos, 多少步到当前位置, 当前位置的值 stones[pos]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            if(a[0] != b[0]) return a[0]-b[0];
            else return a[1]-b[1];
        });
        pq.add(new int[]{1,1,1});
        Set<String> vis = new HashSet<>(); //val+step
        vis.add(1+"#"+1);
        while (pq.size() > 0){
            int[] tmp = pq.poll();
            int pos = tmp[0];
            int k = tmp[1];
            int val = tmp[2];
            if(pos == n-1) return true;
            if(k > 1 && valInxMap.containsKey(val+k-1) && !vis.contains((val+k-1)+"#"+(k-1))) {
                pq.add(new int[]{valInxMap.get(val+k-1), k-1, val+k-1});
                vis.add((val+k-1)+"#"+(k-1));
            }
            if(k > 0 && valInxMap.containsKey(val+k) && !vis.contains((val+k)+"#"+(k))){
                pq.add(new int[]{valInxMap.get(val+k), k, val+k});
                vis.add((val+k)+"#"+(k));
            }
            if(k > 0 && valInxMap.containsKey(val+k+1) && !vis.contains((val+k+1)+"#"+(k+1))) {
                pq.add(new int[]{valInxMap.get(val+k+1), k+1, val+k+1});
                vis.add((val+k+1)+"#"+(k+1));
            }
        }
        return false;
    }
}
