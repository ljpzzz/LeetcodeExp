package Leetcode0465;

import java.util.Arrays;

public class Leetcode0465_1 {
    public int minTransfers(int[][] transactions) {
        int n = 12;
        int[] amount = new int[n];
        for(int[] trans : transactions){
            amount[trans[0]] -= trans[2];
            amount[trans[1]] += trans[2];
        }
        int[] dp = new int[1<<n];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for(int i = 1; i < (1<<n); i++){
            int sum = 0;
            for(int j = 0; j < n; j++){
                if((i&(1<<j)) != 0) sum += amount[j];
            }
            if(sum != 0) continue;
            dp[i] = Integer.bitCount(i)-1;
            for(int j = (i&(i-1)); j > 0; j = ((j-1)&i)){
                dp[i] = Math.min(dp[i], dp[j]+dp[i^j]);
            }
        }
        return dp[(1<<n)-1];
    }
}
