package Leetcode1671;

public class Leetcode1671_1 {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        // dpAsc[i]表示以nums[i]结尾的最长递增子序列
        int[] dpAsc = new int[n];
        dpAsc[0] = 1;
        for(int i = 1; i < n; i++){
            dpAsc[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dpAsc[i] = Math.max(dpAsc[i], dpAsc[j] + 1);
                }
            }
        }

        // dpDesc[i]表示以nums[i]结尾的最长递减子序列
        int[] dpDesc = new int[n];
        dpDesc[n-1] = 1;
        for(int i = n - 2; i >= 0; i--){
            dpDesc[i] = 1;
            for(int j = n - 1; j > i; j--){
                if(nums[i] > nums[j]){
                    dpDesc[i] = Math.max(dpDesc[i], dpDesc[j] + 1);
                }
            }
        }

        int maxLen = 0;
        for(int i = 1; i < n - 1; i++) {
            if(dpAsc[i] > 1 && dpDesc[i] > 1) maxLen = Math.max(maxLen, dpAsc[i] + dpDesc[i] - 1);
        }
        return n - maxLen;
    }
}
