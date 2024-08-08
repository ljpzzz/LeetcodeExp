package Leetcode1298;

import java.util.*;

public class Leetcode1298_1 {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int ans = 0;
        Set<Integer> keySet = new HashSet<>(); //已获得的没有打开盒子的钥匙
        Set<Integer> boxSet = new HashSet<>(); //已获得但是没有打开的盒子
        Queue<Integer> q = new ArrayDeque<>();
        for(int init : initialBoxes){
            if(status[init] == 1) q.offer(init);
            else boxSet.add(init);
        }
        while(!q.isEmpty()){
            int box = q.poll();
            ans += candies[box];
            for(int key : keys[box]){
                if(boxSet.contains(key)){
                    boxSet.remove(key);
                    q.offer(key);
                }
                else keySet.add(key);
            }
            for(int box2 : containedBoxes[box]){
                if(status[box2] == 1) q.offer(box2);
                else if(keySet.contains(box2)){
                    keySet.remove(box2);
                    q.offer(box2);
                }
                else boxSet.add(box2);
            }
        }
        return ans;
    }
}
