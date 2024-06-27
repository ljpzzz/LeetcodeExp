package Leetcode0041;

public class Leetcode0041_1 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(nums[i] <= 0 || nums[i] > n) nums[i] = n+10;
        }
        for(int i =- 0; i < n; i++){
            int x = Math.abs(nums[i]);
            if(x != n+10) nums[x-1] = -Math.abs(nums[x-1]);
        }
        for(int i = 0; i < n; i++){
            if(nums[i] > 0) return i+1;
        }
        return n+1;
    }
}
