package Leetcode0719;

import java.util.*;

public class Leetcode0719_1 {
    TreeMap<Integer, Integer> numInxMap = new TreeMap<>();
    int n;
    public int smallestDistancePair(int[] nums, int k) {
        n = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < n; i++) {
            if(!numInxMap.containsKey(nums[i])) numInxMap.put(nums[i], i);
        }
        int left = 0;
        int right = nums[n - 1] - nums[0];
        int ans = -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(isOK(nums, k, mid)) {
                ans = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }
        return ans;
    }
    public boolean isOK(int[] nums, int k, int mid){
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            Integer bigger = numInxMap.higherKey(nums[i] + mid);
            if(bigger == null) cnt += n - i - 1;
            else cnt += numInxMap.get(bigger) - i - 1;
        }
        return cnt >= k;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0719_1().smallestDistancePair(new int[]{1,3,1},1));
    }
}
