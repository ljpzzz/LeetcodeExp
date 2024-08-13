package Leetcode1406;

public class Leetcode1406_1 {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] pSum = new int[n + 1];
        for(int i = 1; i <= n; i++) pSum[i] = pSum[i - 1] + stoneValue[i - 1];
        //dp[i]表示剩下i堆石子可以得到的最大分数
        int[] dp = new int[n + 1];
        dp[1] = stoneValue[n-1];
        for(int i = 2; i <= n; i++){
            //当前取一堆
            dp[i] = stoneValue[n-i] + (pSum[n] - pSum[n-i+1]-dp[i-1]);
            //当前取两堆
            dp[i] = Math.max(dp[i], stoneValue[n-i] + stoneValue[n-i+1] + (pSum[n] - pSum[n-i+2]-dp[i-2]));
            //当前取三堆
            if(i >= 3) {
                dp[i] = Math.max(dp[i], stoneValue[n-i] + stoneValue[n-i+1] + stoneValue[n-i+2] + (pSum[n] - pSum[n-i+3]-dp[i-3]));
            }
        }
        if(dp[n] > pSum[n] - dp[n]) return "Alice";
        else if(dp[n] == pSum[n] - dp[n]) return "Tie";
        else return "Bob";
    }
}
