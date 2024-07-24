package Leetcode0871;

import java.util.PriorityQueue;

public class Leetcode0871_1 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int pos = 0;
        int fuel = startFuel;
        int ans = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < stations.length; i++) {
            int[] station = stations[i];
            int nextPos = station[0];
            int nextFuel = station[1];
            fuel -= nextPos - pos;
            if(fuel < 0){
                while(!queue.isEmpty() && fuel < 0){
                    fuel += queue.poll();
                    ans++;
                }
                if(fuel < 0) return -1;
            }
            queue.add(nextFuel);
            pos = nextPos;
        }
        while(fuel < target - pos){
            if(queue.isEmpty()) return -1;
            fuel += queue.poll();
            ans++;
        }
        return ans;
    }
}
