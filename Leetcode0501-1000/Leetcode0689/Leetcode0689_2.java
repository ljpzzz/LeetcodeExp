package Leetcode0689;

public class Leetcode0689_2 {
    //三滑动窗口
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[3];
        int sum1 = 0;
        int maxSum1 = 0;
        int maxSum1Index = 0;
        int sum2 = 0;
        int maxSum2 = 0;
        int maxSum2Index1 = 0;
        int maxSum2Index2 = 0;
        int sum3 = 0;
        int maxSum3 = 0;
        //三个滑动窗口分别是[i-3k+1, i-2k], [i-2k+1,i-k],[i-k+1, i]
        for (int i = 2 * k; i < n; i++) {
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= 3 * k - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Index = i - 3 * k + 1;
                }
                if (maxSum1 + sum2 > maxSum2) {
                    maxSum2 = maxSum1 + sum2;
                    maxSum2Index1 = maxSum1Index;
                    maxSum2Index2 = i - 2 * k + 1;
                }
                if (maxSum2 + sum3 > maxSum3) {
                    maxSum3 = maxSum2 + sum3;
                    res[0] = maxSum2Index1;
                    res[1] = maxSum2Index2;
                    res[2] = i - k + 1;
                }
                //窗口滑动，需要去掉3个窗口最前的一个数字
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return res;
    }
}
