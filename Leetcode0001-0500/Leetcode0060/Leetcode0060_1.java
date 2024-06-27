package Leetcode0060;

public class Leetcode0060_1 {
    //下一个排列板子题
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) nums[i] = i + 1;
        while(--k > 0){
            nums = getNext(n, nums);
        }
        String ans = "";
        for(int i = 0; i < nums.length; i++){
            ans += nums[i];
        }
        return ans;
    }
    public int[] getNext(int n, int[] nums){
        int inx = -1;
        for(int i = n - 2; i >= 0; i--){
            if(nums[i] < nums[i + 1]){
                inx = i;
                break;
            }
        }
        int nextInx = -1;
        int nextBigger = n;
        for(int i = inx+1; i < n; i++){
            if(nums[i] > nums[inx] && nums[i] <= nextBigger){
                nextBigger = nums[i];
                nextInx = i;
            }
        }
        int tmp = nums[inx];
        nums[inx] = nums[nextInx];
        nums[nextInx] = tmp;

        for(int i = inx + 1; i + (n-inx-1)/2 < n; i++){
            int tmp2 = nums[i];
            nums[i] = nums[n-i+inx];
            nums[n-i+inx] = tmp2;
        }
        return nums;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0060_1().getPermutation(3, 3));
    }
}
