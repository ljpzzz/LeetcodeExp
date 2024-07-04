package Leetcode0410;

public class Leetcode0410_1 {
    //二分搜索
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            right += nums[i];
            left = Math.max(left, nums[i]);
        }
        int ans = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isOK(nums, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    public boolean isOK(int[] nums, int k, int mid){
        int n = nums.length;
        int cnt = 0;
        int curSum = 0;
        for(int i = 0; i < n; i++){
            if(curSum + nums[i] > mid){
                cnt++;
                if(cnt > k) return false;
                curSum = nums[i];
            }
            else curSum += nums[i];
        }
        if(curSum > 0) cnt++;
        return cnt <= k;
    }
    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8};
        int k = 2;
        Leetcode0410_1 leetcode0410_1 = new Leetcode0410_1();
        int ans = leetcode0410_1.splitArray(nums, k);
        System.out.println(ans);
    }
}
