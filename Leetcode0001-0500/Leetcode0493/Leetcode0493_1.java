package Leetcode0493;

import java.util.*;

public class Leetcode0493_1 {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        List<Integer> d =  new ArrayList<>();
        d.add(nums[0]);
        int ans = 0;
        for(int i = 1; i < n; i++){
            long limit = nums[i]*2L+1;
            int len = d.size();
            if(d.get(len-1) >= limit){
                int inx = bSearch(d, limit);
                ans += len - inx;
            }
            if(nums[i] >= d.get(len-1)) d.add(nums[i]);
            else{
                int inx = bSearch(d, nums[i]);
                d.add(inx, nums[i]);
            }
        }
        return ans;
    }
    //在d中寻找大于等于limit的最小值的位置
    public int bSearch(List<Integer> d, long limit){
        int len = d.size();
        int left = 0;
        int right = len-1;
        int ans = -1;
        while (left <= right){
            int mid = left + (right-left)/2;
            if(d.get(mid) >= limit){
                ans = mid;
                right = mid-1;
            }
            else left = mid+1;
        }
        return ans;
    }
    public static void main(String[] args)
    {
        Leetcode0493_1 leetcode0493_1 = new Leetcode0493_1();
        int[] nums = {1,3,2,3,1};
        int ans = leetcode0493_1.reversePairs(nums);
        System.out.println(ans);
    }
}
