package Leetcode0956;

import java.util.*;

public class Leetcode0956_2 {
    public int tallestBillboard(int[] rods) {
        //key: 数字和  value：最大的正数和
        Map<Integer, Integer> sumPosMap = new HashMap<>();
        Map<Integer, Integer> sumPosMapCopy = new HashMap<>();
        sumPosMap.put(0, 0);
        for(int rod : rods){
            sumPosMapCopy.clear();
            sumPosMapCopy.putAll(sumPosMap);
            for(int key : sumPosMapCopy.keySet()){
                sumPosMap.put(key+rod, Math.max(sumPosMap.getOrDefault(key+rod, 0), sumPosMapCopy.get(key)+rod));
                sumPosMap.put(key-rod, Math.max(sumPosMap.getOrDefault(key-rod, 0), sumPosMapCopy.get(key)));
            }
        }
        return sumPosMap.get(0);
    }
}
