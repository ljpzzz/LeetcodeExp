package Leetcode0992;

import java.util.*;

public class Leetcode0992_1 {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return mostK(nums, k) - mostK(nums, k - 1);
    }
    public int mostK(int[] nums, int k){
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int ans = 0;
        while(right < n){
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while(map.size() > k){
                map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
                if(map.get(nums[left]) == 0) map.remove(nums[left]);
                left++;
            }
            ans += right - left;
            right++;
        }
        return ans;
    }
    public static void main(String[] args) {
        Leetcode0992_1 leetcode0992_1 = new Leetcode0992_1();
        int[] nums = {1,2,1,2,3};
        int k = 2;
        System.out.println(leetcode0992_1.subarraysWithKDistinct(nums, k));
    }
}
