package Leetcode1788;

import java.util.*;

public class Leetcode1788_1 {
    public int maximumBeauty(int[] flowers) {
        int n = flowers.length;
        int[] positiveSum = new int[n+1];
        Map<Integer, Integer> numPosStartMap = new HashMap<>();
        Map<Integer, Integer> numPosEndMap = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(flowers[i] >= 0) positiveSum[i+1] = positiveSum[i]+flowers[i];
            else positiveSum[i+1] = positiveSum[i];
            if(numPosStartMap.get(flowers[i]) == null) numPosStartMap.put(flowers[i], i);
            else numPosEndMap.put(flowers[i], i);
        }
        int max = Integer.MIN_VALUE;
        for(int val : numPosEndMap.keySet()){
            int startPos = numPosStartMap.get(val);
            if(numPosEndMap.get(val) == null) continue;
            int endPos = numPosEndMap.get(val);
            int sum = positiveSum[endPos+1]-positiveSum[startPos];
            if(val >= 0) max = Math.max(max, sum);
            else max = Math.max(max, sum+2*val);
        }
        return max;
    }
}
