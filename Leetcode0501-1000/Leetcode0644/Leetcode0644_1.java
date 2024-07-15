package Leetcode0644;

public class Leetcode0644_1 {
    public double findMaxAverage(int[] nums, int k) {
        double right = Integer.MAX_VALUE;
        double left = Integer.MIN_VALUE;
        double ans = left;
        while(right-left > 1e-6){
            double mid = left + (right-left)/2;
            if(isOK(nums, k, mid)){
                ans = mid;
                left = mid;
            }
            else right = mid;
        }
        return ans;
    }
    public boolean isOK(int[] nums, int k, double mid){
        int n = nums.length;
        double[] d = new double[n];
        for(int i = 0; i < n; i++) d[i] = nums[i]-mid;
        double preSum = 0d;
        double sum = 0d;
        for(int i = 0; i < k; i++) sum += d[i];
        if(sum > 0) return true;
        for(int i = k; i < n; i++){
            sum += d[i];
            preSum += d[i-k];
            if(preSum < 0){
                sum -= preSum;
                preSum = 0;
            }
            if(sum > 0) return true;
        }
        return false;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0644_1().findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
        System.out.println(new Leetcode0644_1().findMaxAverage(new int[]{5}, 1));
    }
}

