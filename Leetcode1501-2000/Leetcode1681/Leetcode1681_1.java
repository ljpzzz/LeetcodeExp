package Leetcode1681;

import java.util.*;

public class Leetcode1681_1 {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        if(n == k) return 0;
        int grpCnt = n / k;

        int[] dp = new int[1 << n]; //dp[mask]是mask的不兼容和最小值
        Arrays.fill(dp, 0x3f3f3f3f);
        Queue<Integer> queue = new ArrayDeque<>();
        //预处理所有合法的分配方式
        Map<Integer, Integer> maskDeltaMap = new HashMap<>();
        for(int mask = 1; mask < (1 << n); mask++){
            int cnt1 = Integer.bitCount(mask);
            if(cnt1 != grpCnt) continue;
            Set<Integer> vis = new HashSet<>();
            boolean valid = true;
            int max = 0;
            int min = n;
            for(int i = 0; i < n; i++){
                if((mask & (1 << i)) == 0) continue;
                if(vis.contains(nums[i])){
                    valid = false;
                    break;
                }
                vis.add(nums[i]);
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);
            }
            if(valid) {
                maskDeltaMap.put(mask, max - min);
                queue.offer(mask);
                dp[mask] = max-min;
            }
        }

        while(!queue.isEmpty()){
            int mask = queue.poll();
            for(int segMask : maskDeltaMap.keySet()){
                if((mask & segMask) != 0) continue; //已经有被选过的位置，跳过
                int newMask = mask | segMask;
                int delta = maskDeltaMap.get(segMask);
                if(dp[mask] + delta < dp[newMask]){
                    dp[newMask] = dp[mask] + delta;
                    queue.offer(newMask);
                }
            }
        }
        if(dp[(1 << n) - 1] == 0x3f3f3f3f) return -1;
        return dp[(1 << n) - 1];
    }
}
