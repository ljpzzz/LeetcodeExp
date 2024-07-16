package Leetcode0689;

public class Leetcode0689_3 {
    //前缀后缀和
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] pSum = new long[n+1];
        for(int i = 0; i < n; i++) pSum[i+1] = pSum[i] + nums[i];
        long[][] maxPre = new long[n+1][2];
        maxPre[k][0] = pSum[k];
        maxPre[k][1] = 0;
        for(int i = k; i < n; i++) {
            maxPre[i+1] = maxPre[i].clone();
            if(pSum[i+1]-pSum[i-k+1] > maxPre[i+1][0]){
                maxPre[i+1][0] = pSum[i+1]-pSum[i-k+1];
                maxPre[i+1][1] = i-k+1;
            }
        }
        long[][] maxPost = new long[n+1][2];
        maxPost[n-k][0] = pSum[n]-pSum[n-k];
        maxPost[n-k][1] = n-k;
        for(int i = n-k-1; i >= 0; i--) {
            maxPost[i] =  maxPost[i+1].clone();
            //这里要等于，因为需要字典序最小，所以相等时选择前面的
            if(pSum[i+k]-pSum[i] >= maxPost[i][0]){
                maxPost[i][0] = pSum[i+k]-pSum[i];
                maxPost[i][1] = i;
            }
        }
        int maxMidIndex = -1;
        long maxSum = 0;
        int maxLeftInx = 0;
        int maxRightInx = 0;
        //中间部分为[i, i+k), 左边部分为[0.i)的最大长度k的和， 右边部分为[i+k, n)的最大长度k的和
        for(int i = k; i <= n-2*k; i++){
            long currentSum = maxPre[i][0] + pSum[i+k] - pSum[i] + maxPost[i+k][0];
            if(currentSum > maxSum){
                maxMidIndex = i;
                maxSum = currentSum;
                maxLeftInx = (int)maxPre[i][1];
                maxRightInx = (int)maxPost[i+k][1];
            }
            else if(currentSum == maxSum){
                if(maxLeftInx > maxPre[i][1] ||
                        (maxLeftInx == maxPre[i][1] && maxMidIndex > i) ||
                        (maxLeftInx == maxPre[i][1] && maxMidIndex == i && maxRightInx > maxPost[i+k][1])){
                    maxMidIndex = i;
                    maxLeftInx = (int)maxPre[i][1];
                    maxRightInx = (int)maxPost[i+k][1];
                }
            }
        }
        return new int[]{maxLeftInx, maxMidIndex, maxRightInx};
    }
}
