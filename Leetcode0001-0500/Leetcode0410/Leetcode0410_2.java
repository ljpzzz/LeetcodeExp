package Leetcode0410;

import java.util.*;

public class Leetcode0410_2 {
    //DP
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n+1];
        for(int i = 0; i < n; i++) prefixSum[i+1] = prefixSum[i]+nums[i];

        //dp[i][j]表示[0,i]分成j段后每段和对应的最小的最大值
        int[][] dp = new int[n][k+1];
        //所有的dp值初始化为无穷大
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        //初始化分成一段和的最大值，此时就是前缀和
        for(int i = 0; i < n; i++) dp[i][1] = prefixSum[i+1];
        //递推，当前需要分成len段
        for(int len = 2; len <= k; len++){
            //需要计算dp[i][len]，即将[0,i]分成len段
            for(int i = 0; i < n; i++){
                //[0,i]最多只能分成i+1段
                if(len > i+1) continue;
                //递推，将[0,j]分成len-1段，将[j+1,i]分成1段，计算最小的dp[i][len]
                for(int j = 0; j < i; j++){
                    dp[i][len] = Math.min(dp[i][len],Math.max(dp[j][len-1], prefixSum[i+1]-prefixSum[j+1]));
                }
            }
        }
        return dp[n-1][k];
    }
}
