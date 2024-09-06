package Leetcode3177;

import java.util.*;

public class Leetcode3177_1 {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, int[]> dp = new HashMap<>();
        int[] pre = new int[k+2]; //保存最大值
        for(int i = 0; i < n; i++){
            dp.computeIfAbsent(nums[i], x->new int[k+1]);
            int[] cur = dp.get(nums[i]);
            for(int j = k; j >= 0; j--) {
                cur[j] = Math.max(cur[j], pre[j])+1;
                pre[j+1] = Math.max(pre[j+1], cur[j]);
            }
        }
        return pre[k+1];
    }
}
