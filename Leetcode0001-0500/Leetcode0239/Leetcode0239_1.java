package Leetcode0239;

import java.util.*;

public class Leetcode0239_1 {
    //滑动窗口+优先队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int index = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < k; i++) map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        ans[index++] = map.lastKey();
        for(int i = k; i < n; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            int cnt = map.get(nums[i - k]);
            if(cnt == 1) map.remove(nums[i - k]);
            else map.put(nums[i - k], cnt - 1);
            ans[index++] = map.lastKey();
        }
        return ans;
    }
}
