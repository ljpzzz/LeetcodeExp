package Leetcode0446;

import java.util.*;

public class Leetcode0446_1 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] d = new Map[n];
        long total = 0;
        for (int i = 0; i < n; i++) {
            d[i] = new HashMap<>();
            for(int j = 0; j < i; j++){
                long delta = (long)nums[i] - nums[j];
                int cnt = d[j].getOrDefault(delta, 0);
                total += cnt;
                d[i].put(delta, d[i].getOrDefault(delta, 0) + cnt + 1);
            }
        }
        return (int)total;
    }
    public static void main(String[] args) {
        int[] nums = {2,2,3,4};
        Leetcode0446_1 leetcode0446_1 = new Leetcode0446_1();
        int ans = leetcode0446_1.numberOfArithmeticSlices(nums);
        System.out.println(ans);
    }
}
