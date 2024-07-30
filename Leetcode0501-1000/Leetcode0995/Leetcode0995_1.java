package Leetcode0995;

public class Leetcode0995_1 {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] d = new int[n+1];
        int op = 0;
        int ans = 0;
        for(int i = 0; i < n; i++){
            op += d[i];
            if((nums[i] + op) % 2 == 0){
                if(i > n-k) return -1;
                ans++;
                op++;
                d[i]++;
                d[i+k]--;
            }
        }
        return ans;
    }
}
