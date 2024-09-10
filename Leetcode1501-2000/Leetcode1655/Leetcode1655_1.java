package Leetcode1655;

import java.util.*;

public class Leetcode1655_1 {
    public boolean canDistribute(int[] nums, int[] quantity) {
        int m = quantity.length;
        if(nums.length < m) return false;
        Map<Integer, Integer> numCntMap = new HashMap<>();
        for(int num : nums) numCntMap.put(num, numCntMap.getOrDefault(num, 0)+1);
        int n = numCntMap.size();
        List<Integer> cntList = new ArrayList<>(numCntMap.values());
        //dp[i][mask]表示前i个数字，已经分配的人是mask时，是否可行
        boolean[][] dp = new boolean[n+1][1<<m];
        dp[0][0] = true;
        for(int i = 1; i <= n; i++){
            int room = cntList.get(i-1);
            for(int mask = 0; mask < (1<<m); mask++){
                if(dp[i-1][mask]){
                    dp[i][mask] = true;
                    continue;
                }
                int subMask = mask;
                for(; subMask > 0; subMask = (subMask-1)&mask){
                    if(dp[i-1][mask^subMask] && room >= getMaskReq(quantity, subMask)){
                        dp[i][mask] = true;
                        break;
                    }
                }
            }
        }
        return dp[n][(1<<m)-1];
    }
    public int getMaskReq(int[] quantity, int subMask){
        int maskReq = 0;
        for(int i = 0; i < quantity.length; i++){
            if((subMask&(1<<i)) != 0) maskReq += quantity[i];
        }
        return maskReq;
    }
    public static void main(String[] args) {
        Leetcode1655_1 leetcode1655_1 = new Leetcode1655_1();
        int[] nums = {1,2,3,3};
        int[] quantity = {2};
        System.out.println(leetcode1655_1.canDistribute(nums, quantity));
    }
}
