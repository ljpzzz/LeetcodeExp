package Leetcode1235;

import java.util.*;

public class Leetcode1235_1 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] d = new int[n][3];
        for(int i = 0; i < n; i++){
            d[i][0] = startTime[i];
            d[i][1] = endTime[i];
            d[i][2] = profit[i];
        }
        Arrays.sort(d, (a, b) -> a[1]-b[1]);
        TreeMap<Integer, Integer> endInxMap = new TreeMap<>();
        endInxMap.put(0, 0);//哨兵
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            int start = d[i-1][0];
            int end = d[i-1][1];
            int cost = d[i-1][2];
            Integer preEnd = endInxMap.floorKey(start);
            int preInx = endInxMap.get(preEnd);
            dp[i] = Math.max(dp[i-1], dp[preInx] + cost);
            endInxMap.put(end, i);
        }
        return dp[n];
    }
}
