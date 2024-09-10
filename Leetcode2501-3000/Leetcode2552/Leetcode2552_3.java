package Leetcode2552;

public class Leetcode2552_3 {
    public long countQuadruplets(int[] nums) {
        //i < j < k < l, 对应值模式的1324
        int n = nums.length;
        long cnt4 = 0;
        long[] cnt3 = new long[n];
        for(int l = 2; l < n; l++){
            long cnt2 = 0;
            for(int j = 0; j < l; j++){
                if(nums[j] < nums[l]) {
                    cnt4 += cnt3[j]; // 3 < 4
                    cnt2++; // 1 < 2
                }
                else cnt3[j] += cnt2; // 2 < 3
            }
        }
        return cnt4;
    }
}
