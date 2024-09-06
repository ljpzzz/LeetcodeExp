package Leetcode3176;

import java.util.*;

public class Leetcode3176_1 {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        //dp[i][j]表示以第i个数字结尾,不等数量最多为j的子序列的最大长度
        int[][] dp = new int[n][k+1];
        Map<Integer, Integer> numCntMap = new HashMap<>();
        //初始化j=0，即没有不相等的子序列最大长度，此时其实就是相等数字的计数
        for(int i = 0; i < n; i++){
            numCntMap.put(nums[i], numCntMap.getOrDefault(nums[i], 0) + 1);
            dp[i][0] = numCntMap.get(nums[i]);
        }
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= k; j++){
                dp[i][j] = dp[i][j-1];
                for(int l = 0; l < i; l++){
                    if(nums[i] == nums[l]){
                        dp[i][j] = Math.max(dp[i][j], dp[l][j] + 1);
                    }
                    else{
                        dp[i][j] = Math.max(dp[i][j], dp[l][j-1] + 1);
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, dp[i][k]);
        }
        return ans;
    }
}
