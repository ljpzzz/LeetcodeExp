package Leetcode2742;

import java.util.Arrays;

public class Leetcode2742_1 {
    //01背包解法，恰好为V
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        //dp[i]表示使用的容积为j时，最小的代价
        int[] dp = new int[2*n + 1];
        int ans = 0x3f3f3f3f;
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for(int t = 0; t < n; t++){
            int v = time[t]+1;
            if(v >= n){
                ans = Math.min(ans, cost[t]);
                continue;
            }
            for(int i = 2*n; i >= v; i--) dp[i] = Math.min(dp[i], dp[i-v] + cost[t]);
        }
        for(int i = n; i <= 2*n; i++) ans = Math.min(ans, dp[i]);
        return ans;
    }
    public static void main(String[] args)
    {
        int[] cost = {1,2,3,2};
        int[] time = {1,2,3,2};
        System.out.println(new Leetcode2742_1().paintWalls(cost, time));
    }
}
