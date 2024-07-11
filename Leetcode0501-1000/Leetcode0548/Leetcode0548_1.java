package Leetcode0548;

import java.util.*;

public class Leetcode0548_1 {
    public boolean splitArray(int[] nums) {
        int n = nums.length;
        if(n < 7) return false;
        Map<Integer, TreeSet<Integer>> numPosMap = new HashMap<>();
        Map<Integer, List<Integer>> preSumMap = new HashMap<>();
        int sum = 0;
        int[] ppSum = new int[n+1];
        for(int i = 0; i < n; i++){
            numPosMap.computeIfAbsent(nums[i], k->new TreeSet<>()).add(i);
            sum += nums[i];
            ppSum[i+1] = sum;
            preSumMap.computeIfAbsent(sum, k->new ArrayList<>()).add(i);
        }
        Map<Integer, List<Integer>> postSumMap = new HashMap<>();
        sum = 0;
        for(int i = n-1; i >= 0; i--){
            sum += nums[i];
            postSumMap.computeIfAbsent(sum, k->new ArrayList<>()).add(i);
        }
        for(int sum1 : preSumMap.keySet()){
            if(postSumMap.get(sum1) == null) continue;
            for(int i : preSumMap.get(sum1)){
                for(int k: postSumMap.get(sum1)){
                    if(k-i < 6) continue;
                    int midSum = sum - 2*sum1 - nums[i+1]-nums[k-1];
                    int jVal = midSum - 2*sum1;
                    int jPos = i+2;
                    while(numPosMap.get(jVal) != null && numPosMap.get(jVal).higher(jPos) != null){
                        Integer j = numPosMap.get(jVal).higher(jPos);
                        if(j != null && j < k-2) {
                            if(ppSum[j]-ppSum[i+2] == sum1 && ppSum[k-1]-ppSum[j+1] == sum1) return true;
                        }
                        jPos = j;
                    }
                }
            }
        }
        return false;
    }
}
