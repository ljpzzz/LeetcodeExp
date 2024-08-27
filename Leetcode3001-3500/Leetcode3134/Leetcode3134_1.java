package Leetcode3134;

import java.util.*;

public class Leetcode3134_1 {
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) set.add(nums[i]);
        long total = 1L*n*(n+1)/2; //总共total个
        long ans = total/2; //中位数是第ans个
        if(total % 2 == 1) ans++;
        int left = 1, right = set.size();
        int res = -1;
        while(left <= right){
            int mid = (left + right)/2;
            if(isOK(nums, mid, ans)){
                res = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }
        return res;
    }
    public boolean isOK(int[] nums, int mid, long req){
        int n = nums.length;
        int left = 0, right = 0;
        long curAns = 0;
        Map<Integer, Integer> numCntMap = new HashMap<>();
        while(right < n){
            int cur = nums[right];
            numCntMap.put(cur, numCntMap.getOrDefault(cur, 0) + 1);
            if(numCntMap.size() <= mid){
                right++;
                continue;
            }
            while(numCntMap.size() > mid){
                curAns += right - left;
                int leftNum = nums[left];
                numCntMap.put(leftNum, numCntMap.get(leftNum) - 1);
                if(numCntMap.get(leftNum) == 0) numCntMap.remove(leftNum);
                left++;
            }
            right++;
        }
        while(left < n){
            curAns += n-left;
            left++;
        }
        return curAns >= req;
    }
    public static void main(String[] args) {
        Leetcode3134_1 obj = new Leetcode3134_1();
        int[] nums = {1,2,3};
        int ans = obj.medianOfUniquenessArray(nums);
        System.out.println(ans);
    }
}
