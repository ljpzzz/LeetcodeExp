package Leetcode1330;

public class Leetcode1330_1 {
    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        if(n <= 1) return 0;
        int total = 0;
        for(int i = 0; i < n-1; i++) total += Math.abs(nums[i]-nums[i+1]);
        int res = total;
        //nums[0]和nums[i]调换
        for(int i = 1; i < n-1; i++){
            int currentRes = total - Math.abs(nums[i]-nums[i+1]) + Math.abs(nums[0]-nums[i+1]);
            res = Math.max(res, currentRes);
        }
        //nums[i]和nums[n-1]调换
        for(int i = 1; i < n-1; i++){
            int currentRes = total - Math.abs(nums[i-1]-nums[i]) + Math.abs(nums[n-1]-nums[i-1]);
            res = Math.max(res, currentRes);
        }
        //i和j对换, 寻找相邻两项最小值中的最大值，以及相邻两项最大值中的最小值
        int min = Math.min(nums[0], nums[1]);
        int max = Math.max(nums[0], nums[1]);
        for(int i = 0; i < n-1; i++){
            min = Math.max(min, Math.min(nums[i], nums[i+1]));
            max = Math.min(max, Math.max(nums[i], nums[i+1]));
        }
        res = Math.max(res, total + 2*(min-max));
        return res;
    }
}
