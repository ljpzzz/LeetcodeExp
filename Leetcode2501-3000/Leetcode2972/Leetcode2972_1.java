package Leetcode2972;

import java.util.*;

public class Leetcode2972_1 {
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        long ans = 1;//全部删除单独计数
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(nums[n-1], n-1);
        int right = n-2;
        while(right >= 0 && nums[right] < nums[right+1]) {
            map.put(nums[right], right);
            right--;
        }
        if(right < 0) return 1L*n*(n+1)/2;
        ans += n-right-1; //删除左边所有，保留右边的[right+1, n-1]，有n-right-1种方法
        for(int left = 0; left < n; left++){
            if(left > 0 && nums[left] <= nums[left-1]) break;
            ans++; //删除left右边所有
            Integer bigger = map.higherKey(nums[left]);
            if(bigger == null) continue;
            int rightInx = map.get(bigger);
            ans += n-rightInx; //删除中间从left到rightInx之前的所有,右端点可以持续到n-2
        }
        return ans;
    }
}
