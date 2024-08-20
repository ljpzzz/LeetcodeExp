package Leetcode1521;

public class Leetcode1521_1 {
    public int closestToTarget(int[] arr, int target) {
        int n = arr.length;
        RMQ rmq = new RMQ(arr);
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            int left = i;
            int right = n - 1;
            while(left <= right){
                int mid = left + (right-left)/2;
                int cnt = rmq.queryAnd(i+1, mid+1);
                if(cnt == target) return 0;
                if(Math.abs(cnt - target) < ans) ans = Math.abs(cnt - target);
                if(cnt > target) left = mid+1;
                else right = mid-1;
            }
        }
        return ans;
    }
    class RMQ {
        int[][] rmqAnd;
        int STEP = 20;
        public RMQ(int[] nums){
            int n = nums.length;
            rmqAnd = new int[n+1][STEP];
            for(int i = 1; i <= n; i++) rmqAnd[i][0] = nums[i-1];
            int len = 1;
            for(int j = 1; j < STEP; j++){
                len *= 2;
                for(int i = 1; i + len -1 <= n; i++) {
                    rmqAnd[i][j] = rmqAnd[i][j-1]&rmqAnd[i + len/2][j-1];
                }
            }
        }
        public int queryAnd(int left, int right){
            int len = right - left + 1;
            int step = (int)(Math.log(len)/Math.log(2));
            return rmqAnd[left][step]&rmqAnd[right - (1<<step) + 1][step];
        }
    }
    public static void main(String[] args){
        System.out.println(new Leetcode1521_1().closestToTarget(new int[]{70,15,21,96}, 4));
    }
}
