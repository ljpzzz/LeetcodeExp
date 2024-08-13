package Leetcode1425;

import java.util.PriorityQueue;

public class Leetcode1425_1 {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n]; //dp[i]表示以第i个数字结尾的最大子序列和
        dp[0] = nums[0];
        int ans = dp[0];
        //int[] 2个值，索引对应的dp值, 索引
        PriorityQueue<int[]> pq  = new PriorityQueue<>((a,b)->b[0]-a[0]);
        pq.add(new int[]{dp[0],0});
        for(int i = 1; i < n; i++){
            while(!pq.isEmpty() && i - pq.peek()[1] > k) pq.poll();
            if(pq.isEmpty() || pq.peek()[0] <= 0) dp[i] = nums[i];
            else dp[i] = pq.peek()[0] + nums[i];
            pq.add(new int[]{dp[i],i});
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }
}
