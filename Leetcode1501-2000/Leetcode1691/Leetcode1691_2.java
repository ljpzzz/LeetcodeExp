package Leetcode1691;

import java.util.*;

public class Leetcode1691_2 {
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for(int i = 0; i < n; i++) Arrays.sort(cuboids[i]);
        Arrays.sort(cuboids, (a,b)->(a[0]+a[1]+a[2]-b[0]-b[1]-b[2]));
        int[] dp = new int[n+1];
        dp[0] = 0;
        int max = 0;
        for(int i = 1; i <= n; i++){
            dp[i] = cuboids[i-1][2];
            for(int j = 1; j < i; j++){
                if(cuboids[j-1][0] <= cuboids[i-1][0] && cuboids[j-1][1] <= cuboids[i-1][1]
                        && cuboids[j-1][2] <= cuboids[i-1][2]){
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i-1][2]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
