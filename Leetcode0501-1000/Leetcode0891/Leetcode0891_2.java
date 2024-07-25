package Leetcode0891;

import java.util.Arrays;

public class Leetcode0891_2 {
    //二刷方法
    int MOD = (int)1e9+7;
    public int sumSubseqWidths(int[] nums) {
        long res = 0;
        int n = nums.length;
        Arrays.sort(nums);
        long sum = nums[0];
        //计算区间[i-1，i]的贡献次数
        for(int i = 1; i < n; i++){
            long times1 = pow(2, i)-1;
            if(times1 < 0) times1 += MOD;
            long times2 = pow(2, n-i)-1;
            if(times2 < 0) times2 += MOD;
            long times = times1*times2%MOD;
            //System.out.println("section (" + (i-1) + "," + i + "), res :" + (times*(nums[i]-nums[i-1])));
            res += times*(nums[i]-nums[i-1]);
            res %= MOD;
        }
        return (int)res;
    }
    //计算a^b%MOD
    private long pow(int a, int b){
        long res = 1;
        long a1 = a;
        while(b > 0){
            if((b&1) > 0) res =  (res*a1)%MOD;
            a1 = (a1*a1)%MOD;
            b = b/2;
        }
        return res;
    }
}
