package Leetcode0632;

import java.util.*;

public class Leetcode0632_1 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        //int[]3个值，0表示当前值，1表示当前值所在的list索引,2表示当前值所在list的位置索引
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < n; i++) {
            int val = nums.get(i).get(0);
            pq.add(new int[]{val, i, 0});
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        int left = map.firstKey();
        int right = map.lastKey();
        int len = right-left;
        while(true){
            int[] cur = pq.poll();
            int val = cur[0];
            int index = cur[1];
            int pos = cur[2];
            map.put(val, map.getOrDefault(val, 0) - 1);
            if(map.get(val) == 0) map.remove(val);
            if(pos == nums.get(index).size()-1) break;
            int next = nums.get(index).get(pos+1);
            pq.add(new int[]{next, index, pos+1});
            map.put(next, map.getOrDefault(next, 0) + 1);
            int left2 = map.firstKey();
            int right2 = map.lastKey();
            if(right2-left2 < len || right2-left2 == len && left2 < left){
                len = right2-left2;
                left = left2;
                right = right2;
            }
        }
        return new int[]{left, right};
    }
    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(4,10,15,24,26));
        nums.add(Arrays.asList(0,9,12,20));
        nums.add(Arrays.asList(5,18,22,30));
        Leetcode0632_1 l = new Leetcode0632_1();
        int[] res = l.smallestRange(nums);
        System.out.println(res[0] + " " + res[1]);
    }
}
