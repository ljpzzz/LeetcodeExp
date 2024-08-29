package Leetcode0910;

import com.sun.javafx.image.IntPixelGetter;

import java.util.Arrays;

public class Leetcode0910_1 {
    public int smallestRangeII(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = nums[n-1] - nums[0];
        if(k == 0) return ans;
        if(nums[0] == nums[n-1]) return 0;
        //前i个数字+k, 后面n-i个数字-k
        for(int i = 0; i <= n; i++){
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            if(i > 0){
                min = Math.min(min, nums[0] + k);
                max = Math.max(max, nums[i-1] + k);
            }
            if(i < n){
                min = Math.min(min, nums[i] - k);
                max = Math.max(max, nums[n-1] - k);
            }
            if(max - min < ans) ans = max - min;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {1,3,6};
        int k = 3;
        System.out.println(new Leetcode0910_1().smallestRangeII(nums, k));
    }
}
