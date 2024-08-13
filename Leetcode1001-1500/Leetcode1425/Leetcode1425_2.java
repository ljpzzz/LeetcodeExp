package Leetcode1425;

import java.util.*;

public class Leetcode1425_2 {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        //dp[i]表示以位置i结束的最大子序列和
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        //int[]有2个值， i和对应的dp[i]
        Deque<int[]> pq = new ArrayDeque<>();
        pq.add(new int[]{0, dp[0]});
        for(int i = 1; i < n; i++){
            //踢出序号距离大于k的值
            while(pq.size() > 0 && i-pq.peekFirst()[0] > k) pq.pollFirst();
            dp[i] = Math.max(pq.peekFirst()[1]+nums[i], nums[i]);
            max = Math.max(max, dp[i]);
            while(pq.size() > 0 && dp[i] > pq.peekLast()[1]) pq.pollLast();
            //将当前值加入单调栈
            pq.addLast(new int[]{i, dp[i]});
        }
        return max;
    }
}
