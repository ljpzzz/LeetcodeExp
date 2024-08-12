package Leetcode1354;

import java.util.PriorityQueue;

public class Leetcode1354_1 {
    public boolean isPossible(int[] target) {
        int n = target.length;
        if(n == 1)  return target[0] == 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        long sum = 0;
        for(int t : target){
            sum += t;
            pq.offer(t);
        }
        while(!pq.isEmpty()){
            int cur = pq.poll();
            if(cur == 1) return true;
            if(cur <= sum - cur) return false;
            int next = (int)(cur % (sum - cur));
            if(next < 1 && sum-cur != 1) return false;
            sum = sum - cur + next;
            pq.offer(next);
        }
        return false;
    }
    public static void main(String[] args){
        System.out.println(new Leetcode1354_1().isPossible(new int[]{8,5}));
    }
}
