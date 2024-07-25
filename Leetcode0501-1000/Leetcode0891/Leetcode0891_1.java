package Leetcode0891;

import java.util.Arrays;

public class Leetcode0891_1 {
    //三刷自己硬推
    public int sumSubseqWidths(int[] nums) {
        int MOD = 1000000007;
        Arrays.sort(nums);
        int n = nums.length;
        long[] pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) pow2[i] = pow2[i - 1] * 2 % MOD;
        long[] dp = new long[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = (((pow2[i]-1+MOD)%MOD)*(nums[i]-nums[i-1])%MOD + dp[i-1]*2%MOD)%MOD;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) ans = (ans + dp[i]) % MOD;
        return (int) ans;
    }
    public static void main(String[] args) {
        Leetcode0891_1 test = new Leetcode0891_1();
        int[] nums = {2, 1, 3};
        System.out.println(test.sumSubseqWidths(nums));
    }
}
