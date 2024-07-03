package Leetcode0312;

public class Leetcode0312_1 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        for (int i = 1; i <= n; i++) {
            arr[i] = nums[i - 1];
        }
        arr[n+1] = 1;
        //dp[i][j]表示开区间(i,j)的最大硬币数量
        int[][] dp = new int[n + 2][n + 2];
        for(int i = 1; i <= n; i++) dp[i-1][i+1] = arr[i];
        for(int len = 3; len <= n+2; len++){
            for(int i = 0; i <= n; i++){
                int j = i + len - 1;
                if(j > n+1) continue;
                //(i,k)和(k,j)
                for(int k = i+1; k < j; k++) dp[i][j] = Math.max(dp[i][j], dp[i][k] + arr[i]*arr[k]*arr[j] + dp[k][j]);
            }
        }
        return dp[0][n+1];
    }
    public static void main(String args[]) {
        int[] nums = {3,1,5,8};
        Leetcode0312_1 leetcode0312_1 = new Leetcode0312_1();
        System.out.println(leetcode0312_1.maxCoins(nums));
    }
}
