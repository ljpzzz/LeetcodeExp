package Leetcode1250;

public class Leetcode1250_1 {
    public boolean isGoodArray(int[] nums) {
        int common = nums[0];
        for(int i = 1; i < nums.length; i++) common = gcd(common, nums[i]);
        return common == 1;
    }
    public int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
