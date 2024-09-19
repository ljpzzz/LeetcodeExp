package Leetcode1787;

import java.util.*;

public class Leetcode1787_1 {
    public int minChanges(int[] nums, int k) {
        int MAX = 1024;
        int n = nums.length;
        //dp[i][j], 前i列， xor值为j的最小修改次数
        int[][] dp = new int[k+1][MAX];
        for(int i = 0; i < MAX; i++) Arrays.fill(dp[0], 0x3f3f3f3f);
        dp[0][0] = 0;

        for(int i = 1; i <= k; i++){
            Map<Integer, Integer> numCntMap = new HashMap<>();
            int posSize = 0;
            for(int j = i-1; j < n; j += k){
                numCntMap.put(nums[j], 1+numCntMap.getOrDefault(nums[j], 0));
                posSize++;
            }
            int minDp = 0x3f3f3f3f;
            for(int j = 0; j < MAX; j++) minDp = Math.min(minDp, dp[i-1][j]);
            Arrays.fill(dp[i], minDp + posSize);

            for(int j = 0; j < MAX; j++){
                for(int x : numCntMap.keySet()){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j^x] + posSize - numCntMap.get(x));
                }
            }
        }
        return dp[k][0];
    }
}
