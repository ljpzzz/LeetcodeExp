package Leetcode1755;

import java.util.*;

public class Leetcode1755_1 {
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        if(n == 1) return Math.min(Math.abs(nums[0] - goal), Math.abs(goal));
        int m = n / 2;
        int[] nums1 = new int[m];
        for(int i = 0; i < m; i++) nums1[i] = nums[i];
        int[] nums2 = new int[n - m];
        for(int i = m; i < n; i++) nums2[i - m] = nums[i];

        Map<Integer, Integer> map1 = new HashMap<>(); //key: mask, value: 序列值
        map1.put(0, 0);
        for(int i = 0; i < m; i++) map1.put(1<<i, nums1[i]);
        int ans = Math.abs(goal);
        for(int mask = 1; mask < (1 << m); mask++){
            int subMask = mask&(mask-1);
            int leftMask = mask - subMask;
            int val = map1.get(subMask) + map1.get(leftMask);
            map1.put(mask, val);
            ans = Math.min(ans, Math.abs(goal - val));
            if(ans == 0) return ans;
        }

        TreeSet<Integer> set1 = new TreeSet<>(map1.values());

        Map<Integer, Integer> map2 = new HashMap<>(); //key: mask, value: 序列值
        for(int i = 0; i < n-m; i++) map2.put(1<<i, nums2[i]);
        map2.put(0, 0);
        for(int mask = 1; mask < (1 << (n-m)); mask++){
            int subMask = mask&(mask-1);
            int leftMask = mask - subMask;
            int val = map2.get(subMask) + map2.get(leftMask);
            map2.put(mask, val);
            ans = Math.min(ans, Math.abs(goal - val));
            // x+y >= goal
            Integer ceil = set1.ceiling(goal - val);
            if(ceil != null) ans = Math.min(ans, Math.abs(goal - val - ceil));
            //x+y <= goal
            Integer floor = set1.floor(goal - val);
            if(floor != null) ans = Math.min(ans, Math.abs(goal - val - floor));
            if(ans == 0) return ans;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {5,-7,3,5};
        int goal = 6;
        Leetcode1755_1 leetcode1755_1 = new Leetcode1755_1();
        System.out.println(leetcode1755_1.minAbsDifference(nums, goal));
    }
}
