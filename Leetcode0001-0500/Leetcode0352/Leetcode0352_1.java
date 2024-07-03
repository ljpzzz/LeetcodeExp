package Leetcode0352;

import java.util.*;

public class Leetcode0352_1 {
    TreeMap<Integer, Integer> startEndMap;
    Map<Integer, Integer> endStartMap;
    public Leetcode0352_1() {
        startEndMap = new TreeMap<>();
        endStartMap = new HashMap<>();
    }

    public void addNum(int value) {
        Integer prev = startEndMap.floorKey(value);
        if(prev != null && startEndMap.get(prev) >= value) return;
        int start = value;
        int end = value;
        if(endStartMap.get(start-1) != null){
            int preStart = endStartMap.get(start-1);
            startEndMap.remove(preStart);
            endStartMap.remove(start-1);
            start = preStart;
        }
        if(startEndMap.get(end+1) != null){
            int nextEnd = startEndMap.get(end+1);
            startEndMap.remove(end+1);
            endStartMap.remove(nextEnd+1);
            end = nextEnd;
        }
        startEndMap.put(start, end);
        endStartMap.put(end, start);
    }

    public int[][] getIntervals() {
        int n = startEndMap.size();
        int[][] ans = new int[n][2];
        int index = 0;
        for(int start : startEndMap.keySet()){
            ans[index][0] = start;
            ans[index][1] = startEndMap.get(start);
            index++;
        }
        return ans;
    }
}
