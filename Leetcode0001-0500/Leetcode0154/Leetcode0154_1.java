package Leetcode0154;

public class Leetcode0154_1 {
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int ans = Math.min(nums[0], nums[n - 1]);
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] < nums[right]){
                ans = Math.min(ans, nums[mid]);
                right = mid-1;
            }else if(nums[mid] > nums[right]){
                left = mid + 1;
            }
            else {
                ans = Math.min(ans, nums[mid]);
                right--;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0154_1().findMin(new int[]{4,5,6,7,0,1,4}));
        System.out.println(new Leetcode0154_1().findMin(new int[]{0,1,4,4,5,6,7}));
    }
}
