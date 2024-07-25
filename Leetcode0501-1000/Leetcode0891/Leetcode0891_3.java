package Leetcode0891;

import java.util.Arrays;

public class Leetcode0891_3 {
    public int sumSubseqWidths(int[] nums) {
        int MOD = 1000000007;
        int n = nums.length;
        if(n == 1) return 0;
        Arrays.sort(nums);
        if(n == 2) return nums[1]-nums[0];
        long total = 0L;
        long y = 2;
        long x = nums[0];
        for(int i = 1; i < n; i++){
            total = (total + nums[i]*(y-1) - x)%MOD;
            y = (y*2)%MOD;
            x = (2*x+nums[i])%MOD;
        }
        return (int)total;
    }

}
