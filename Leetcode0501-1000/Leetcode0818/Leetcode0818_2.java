package Leetcode0818;

import java.util.*;

public class Leetcode0818_2 {
    public int racecar(int target) {
        int n = (int)(Math.log(target+1)/Math.log(2));
        if(target+1 == (int)Math.pow(2, n)) return n;
        int[] dp = new int[target+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 1; i <= target; i++) {
            //向前走j步, 2^(j-1)-1 < i <= 2^j-1
            for(int j = 1; (1<<j) < 2*i+2; j++){
                int distance = (1<<j)-1;
                if(distance == i) {
                    dp[i] = j;
                    continue;
                }
                if(distance > i){
                    dp[i] = Math.min(dp[i], j+1+dp[distance-i]);
                    continue;
                }
                //到此处说明还没有到位置i, 可以向后走k步，再向前走j步
                for(int k = 0; k < j; k++){
                    int backDistance = (1<<k)-1;
                    dp[i] = Math.min(dp[i], j+1+k+1+dp[i-distance+backDistance]);
                }
            }
        }
        return dp[target];
    }
}
