package Leetcode0813;

public class Leetcode0813_1 {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        int[] pSum = new int[n+1];
        for(int i = 0; i < n; i++) pSum[i+1] = pSum[i] + nums[i];
        //dp[i][j]表示将前i个数字分成j组可以得到的最大分数
        double[][] dp = new double[n+1][k+1];
        for(int i = 1; i <= n; i++){
            dp[i][1] = (double)pSum[i]/i;
            for(int j = 2; j <= Math.min(i, k); j++){
                //默认自己一组新的
                dp[i][j] = dp[i-1][j-1] + nums[i-1];
                //前t个数字j-1组，最后i-t个数字1组
                for(int t = j-1; t < i; t++){
                    dp[i][j] = Math.max(dp[i][j], dp[t][j-1] + (double)(pSum[i]-pSum[t])/(i-t));
                }
            }
        }
        return dp[n][k];
    }
    public static void main(String[] args) {
        int[] nums = {9,1,2,3,9};
        int k = 3;
        Leetcode0813_1 leetcode0813_1 = new Leetcode0813_1();
        System.out.println(leetcode0813_1.largestSumOfAverages(nums, k));
    }
}
