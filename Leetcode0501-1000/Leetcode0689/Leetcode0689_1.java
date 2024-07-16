package Leetcode0689;

import java.util.Arrays;

public class Leetcode0689_1 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] pSum = new long[n + 1];
        for (int i = 0; i < n; i++) pSum[i + 1] = pSum[i] + nums[i];
        int m = 3;
        //dp[i][j]表示前i个数字,j个无重叠数组的最大和
        long[][] dp = new long[n+1][m + 1];
        int[][] dpPre = new int[n+1][m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                //默认不选第i个数字
                dp[i][j] = dp[i-1][j];
                dpPre[i][j] = dpPre[i-1][j];
                if(i >= k){
                    //选第i个数字
                    if(dp[i-k][j-1] + (pSum[i] - pSum[i-k]) > dp[i][j]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - k][j - 1] + (pSum[i] - pSum[i - k]));
                        dpPre[i][j] = i - k;
                    }
                }
            }
        }
        int[] ans = new int[m];
        ans[m-1] = dpPre[n][m];
        int index = ans[m-1];
        for(int j = m - 2; j >= 0; j--){
            ans[j] = dpPre[index][j+1];
            index = ans[j];
        }
        return ans;
    }
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(new Leetcode0689_1().maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2)));
    }
}
