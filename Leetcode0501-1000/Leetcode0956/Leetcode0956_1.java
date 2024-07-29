package Leetcode0956;

import java.util.*;

public class Leetcode0956_1 {
    public int tallestBillboard(int[] rods) {
        int n = rods.length;
        int m = n/2;
        int[] arr1 = new int[m];
        for(int i = 0; i < m; i++) arr1[i] = rods[i];
        int[] arr2 = new int[n-m];
        for(int i = 0; i < n-m; i++) arr2[i] = rods[i+m];
        int ans = 0;
        Map<Integer, Integer> m1 = getHalf(arr1);
        Map<Integer, Integer> m2 = getHalf(arr2);
        for(int val : m1.keySet()){
            if(m2.containsKey(-val)){
                ans = Math.max(ans, m1.get(val)+m2.get(-val));
            }
        }
        return ans;
    }
    public Map<Integer, Integer> getHalf(int[] nums){
        //dp[i][0]表示选择第i个组到当前组对应的总和
        //dp[i][1]表示选择第i个组到相反组对应的总和
        int[][] dp = new int[60000][2];
        int len = 0;
        dp[len++] = new int[]{0,0};
        for(int num : nums){
            int currentLen = len;
            for(int i = 0; i < currentLen; i++){
                dp[len++] = new int[]{dp[i][0]+num, dp[i][1]};//选到当前组
                dp[len++] = new int[]{dp[i][0], dp[i][1]+num};//选到相反组
            }
        }
        //key: cur[0]-cur[1], value:cur[0]最大值
        Map<Integer, Integer> res = new HashMap<>();
        for(int[] cur : dp){
            int val = Math.max(cur[0], res.getOrDefault(cur[0]-cur[1], 0));
            res.put(cur[0]-cur[1], val);
        }
        return res;
    }
}
