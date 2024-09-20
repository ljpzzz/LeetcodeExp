package Leetcode1799;

import java.util.*;

public class Leetcode1799_1 {
    public int maxScore(int[] nums) {
        int n = nums.length;
        int[][] gcdArr = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                gcdArr[i][j] = gcd(nums[i], nums[j]);
                gcdArr[j][i] = gcdArr[i][j];
            }
        }
        int[] dp = new int[1 << n];
        Map<Integer, Integer> candidateGcdMap = new HashMap<>();
        for(int mask = 2; mask < (1 << n); mask++){
            int count = Integer.bitCount(mask);
            if(count == 2){
                int x = -1;
                int y = -1;
                for(int i = 0; i < n; i++){
                    if((mask & (1 << i)) != 0){
                        if(x == -1) x = i;
                        else y = i;
                    }
                }
                candidateGcdMap.put(mask, gcdArr[x][y]);
            }
        }
        for(int mask = 1; mask < (1 << n); mask++){
            int count = Integer.bitCount(mask);
            if(count % 2 == 1) continue;
            for(int candidate : candidateGcdMap.keySet()){
                if((mask & candidate) != candidate) continue;
                dp[mask] = Math.max(dp[mask], dp[mask ^ candidate] + count/2*candidateGcdMap.get(candidate));
            }
        }
        return dp[(1 << n) - 1];
    }
    int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
