package Leetcode0732;

import java.util.*;

public class Leetcode0732_1 {
    TreeMap<Integer, Integer> dMap = new TreeMap<>();
    public Leetcode0732_1() {

    }

    public int book(int startTime, int endTime) {
        dMap.put(startTime, 1+dMap.getOrDefault(startTime, 0));
        dMap.put(endTime, -1+dMap.getOrDefault(endTime, 0));
        int delta = 0;
        int ans = 0;
        for(int time : dMap.keySet()){
            delta += dMap.get(time);
            ans = Math.max(ans, delta);
        }
        return ans;
    }
}
