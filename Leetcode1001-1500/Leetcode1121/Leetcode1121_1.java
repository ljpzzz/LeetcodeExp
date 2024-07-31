package Leetcode1121;

import java.util.*;

public class Leetcode1121_1 {
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        if(k == 1) return true;
        int n = nums.length;
        Map<Integer, Integer> m = new HashMap<>();
        for(int num : nums) m.put(num, 1+m.getOrDefault(num, 0));
        int maxCnt = 0;
        for(int num : m.keySet()) {
            maxCnt = Math.max(maxCnt, m.get(num));
        }
        return n >= maxCnt*k;
    }
}
