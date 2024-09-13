package Leetcode1723;

import java.util.*;

public class Leetcode1723_1 {
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        Arrays.sort(jobs);
        if(n == k) return jobs[n-1];
        int[] maskSum = new int[1<<n];
        for(int i = 0; i < (1<<n); i++){
            int sum = 0;
            for(int j = 0; j < n; j++){
                if((i&(1<<j)) != 0) sum += jobs[j];
            }
            maskSum[i] = sum;
        }
        //dp[i][j]表示前i个人分配mask为j任务，需要的最少时间
        int[][] dp = new int[k+1][(1<<n)];
        for(int i = 0; i < k+1; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        dp[0][0] = 0;
        for(int i = 1; i <= k; i++){
            dp[i][0] = 0;
            for(int j = 1; j < (1<<n); j++){
                int mask = j;
                for(; mask > 0; mask = (mask-1)&j){
                    int curVal = Math.max(dp[i-1][j-mask], maskSum[mask]);
                    dp[i][j] = Math.min(dp[i][j], curVal);
                }
            }
        }
        return dp[k][(1<<n)-1];
    }
    public static void main(String[] args)
    {
        int[] jobs = {1,2,4,7,8};
        int k = 2;
        Leetcode1723_1 leetcode1723_1 = new Leetcode1723_1();
        System.out.println(leetcode1723_1.minimumTimeRequired(jobs, k));
    }
}
