package Leetcode0220;

import java.util.TreeMap;

public class Leetcode0220_1 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            if(i-indexDiff-1 >= 0){
                int cnt = map.get(nums[i-indexDiff-1]);
                if(cnt == 1) map.remove(nums[i-indexDiff-1]);
                else map.put(nums[i-indexDiff-1], cnt-1);
            }
            Integer left = map.ceilingKey(nums[i]-valueDiff);
            if(left != null && left <= nums[i]+valueDiff) return true;
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
       }
        return false;
    }
}
