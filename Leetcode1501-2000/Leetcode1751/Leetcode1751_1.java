package Leetcode1751;

import java.util.*;

public class Leetcode1751_1 {
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        Arrays.sort(events, (a, b)-> a[1] - b[1]);
        TreeMap<Integer, Integer> endTimeInxMap = new TreeMap<>();
        for(int i = 0; i < n; i++) endTimeInxMap.put(events[i][1], i+1);
        int[][] dp = new int[n+1][k + 1]; // dp[i][j] 表示前i个event中，参加j个event，对应的最大收益
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
            dp[i][0] = 0;
            for(int j = 1; j <= k; j++){
                dp[i][j] = dp[i-1][j]; //默认不参加当前会议
                Integer preEndTime = endTimeInxMap.lowerKey(events[i-1][0]);
                if(preEndTime == null) dp[i][j] = Math.max(dp[i][j], events[i-1][2]);
                else {
                    int preInx = endTimeInxMap.get(preEndTime);
                    dp[i][j] = Math.max(dp[i][j], events[i-1][2] + dp[preInx][j-1]);
                }
            }
        }
        return dp[n][k];
    }
}
