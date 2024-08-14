package Leetcode1458;

public class Leetcode1458_1 {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m][n];
        dp[0][0] = nums1[0] * nums2[0];
        for(int i = 1; i < m; i++) dp[i][0] = Math.max(dp[i - 1][0], nums1[i] * nums2[0]);
        for(int j = 1; j < n; j++) dp[0][j] = Math.max(dp[0][j - 1], nums1[0] * nums2[j]);
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); //不选当前对
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + nums1[i] * nums2[j]); //选择当前对,和之前的一起
                dp[i][j] = Math.max(dp[i][j], nums1[i] * nums2[j]); //单独选一个
            }
        }
        return dp[m - 1][n - 1];
    }
}
