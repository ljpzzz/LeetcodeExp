package Leetcode1770;

import java.util.Arrays;

public class Leetcode1770_1 {
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        //dp[i][j]表示前面移除i个，后面移除j个，最大分数
        long[][] dp = new long[m+1][m+1];
        for(int i = 0; i <= m; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        dp[0][0] = 0;
        for(int total = 1; total <= m; total++){
            for(int i = 0; i <= total; i++){
                int j = total - i;
                if(i == 0) dp[i][j] = dp[i][j-1] + nums[n-j] * multipliers[total-1];
                else if(j == 0) dp[i][j] = dp[i-1][j] + nums[i-1] * multipliers[total-1];
                else dp[i][j] = Math.max(dp[i-1][j] + nums[i-1] * multipliers[total-1], dp[i][j-1] + nums[n-j] * multipliers[total-1]);
            }
        }
        long ans = Integer.MIN_VALUE;
        for(int i = 0; i <= m; i++) ans = Math.max(ans, dp[i][m-i]);
        return (int)ans;
    }
    public static void main(String[] args)
    {
        Leetcode1770_1 test = new Leetcode1770_1();
        int[] nums = {-1000,-1000,-1000};
        int[] multipliers = {1000,1000};
        System.out.println(test.maximumScore(nums, multipliers));
    }
}
