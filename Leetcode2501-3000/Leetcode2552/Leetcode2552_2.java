package Leetcode2552;

public class Leetcode2552_2 {
    //内存优化
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        //rightBigger[i][j]表示nums[i]右边大于j的数的个数
        long[][] rightBigger = new long[n][n+1];
        for(int i = n-2; i >= 0; i--){
            rightBigger[i] = rightBigger[i+1].clone();
            int val = nums[i+1];
            for(int j = val-1; j >= 0; j--) rightBigger[i][j]++;
        }
        long ans = 0;
        for(int i = 1; i <= n-3; i++){
            //index: x < i < j < y
            //val: nums[x] < nums[j] < nums[i] < nums[y]
            for(int j = i+1; j < n-1; j++){
                if(nums[j] < nums[i]) {
                    //ans += leftSmaller[i][nums[j]] * rightBigger[j][nums[i]];
                    long rightSmall = n-1-i-rightBigger[i][nums[j]];
                    long leftSmall = nums[j]-rightSmall;
                    ans += leftSmall * rightBigger[j][nums[i]];
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {1,3,2,4,5};
        Leetcode2552_2 leetcode2552_2 = new Leetcode2552_2();
        System.out.println(leetcode2552_2.countQuadruplets(nums));
    }
}
