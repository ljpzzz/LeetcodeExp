package Leetcode2742;

import java.util.Arrays;

public class Leetcode2742_2 {
    ////01背包解法，至少为V
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        for(int i = 0; i < n; i++) time[i]++;
        int[] dp = new int[n+1]; //dp[i]表示时间至少为i时的最小cost
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = n; j >= 1; j--){
                if(time[i] > j) dp[j] = Math.min(dp[j], cost[i]);
                else dp[j] = Math.min(dp[j], dp[j-time[i]]+cost[i]);
            }
        }
        return dp[n];
    }
}
