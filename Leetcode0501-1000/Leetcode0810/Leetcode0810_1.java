package Leetcode0810;

public class Leetcode0810_1 {
    public boolean xorGame(int[] nums) {
        int n = nums.length;
        if (n % 2 == 0) return true;
        else {
            int xor = 0;
            for (int i = 0; i < n; i++) xor ^= nums[i];

            if (xor == 0) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
