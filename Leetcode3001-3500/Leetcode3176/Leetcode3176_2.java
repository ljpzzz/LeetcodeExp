package Leetcode3176;

import java.util.*;

public class Leetcode3176_2 {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, int[]> dp = new HashMap<>();
        int ans = 0;
        for(int i = 0; i < n; i++){
            dp.computeIfAbsent(nums[i], x->new int[k+1]);
            int[] cur = dp.get(nums[i]);
            for(int j = 0; j <= k; j++){
                cur[j]++; //此时相等必选，不会增加不等的数量
                if(j == 0) continue;
                for(int x : dp.keySet()){
                    if(x == nums[i]) continue;
                    if(cur[j] < dp.get(x)[j-1]+1) cur[j] = dp.get(x)[j-1]+1;
                }
            }
            ans = Math.max(ans, cur[k]);
        }
        return ans;
    }
}
