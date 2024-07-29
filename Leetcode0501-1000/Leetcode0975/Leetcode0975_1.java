package Leetcode0975;

import java.util.*;

public class Leetcode0975_1 {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        //dp[i][0]表示从位置i，当前是奇数跳跃能否到达终点
        //dp[i][1]表示从位置i，当前是偶数跳跃能否到达终点
        boolean[][] dp = new boolean[n][2];
        Arrays.fill(dp[n-1], true);
        TreeMap<Integer, Integer> numPosMap = new TreeMap<>();
        numPosMap.put(arr[n-1], n-1);
        int ans = 1;
        for(int i = n-2; i >= 0; i--){
            //检查奇数条
            Integer nextBigger = numPosMap.ceilingKey(arr[i]);
            if(nextBigger != null && dp[numPosMap.get(nextBigger)][1]) dp[i][0] = true;
            Integer nextSmaller = numPosMap.floorKey(arr[i]);
            if(nextSmaller != null && dp[numPosMap.get(nextSmaller)][0]) dp[i][1] = true;
            numPosMap.put(arr[i], i);
            if(dp[i][0]) ans++;
        }
        return ans;
    }
}
