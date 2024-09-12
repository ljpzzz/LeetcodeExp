package Leetcode2576;

import java.util.Arrays;

public class Leetcode2576_1 {
    public int maxNumOfMarkedIndices(int[] nums) {
        int n = nums.length;
        if(n == 1) return 0;
        Arrays.sort(nums);
        int left = 0;
        int right = n / 2;
        int ans = 0;
        while(left < n/2 && right < n){
            if(nums[left] * 2 <= nums[right]){
                ans += 2;
                left++;
                right++;
            }
            else right++;
        }
        return ans;
    }
}
