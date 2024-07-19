package Leetcode0805;

import java.util.*;

public class Leetcode0805_1 {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if(n == 1) return false;
        int[] d = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            d[i] = nums[i]*n;
            sum += d[i];
        }
        int avg = sum/n;
        for (int i = 0; i < n; i++) d[i] -= avg;

        int m = n/2;
        int[] d1 = new int[m];
        int sum1 = 0;
        for (int i = 0; i < m; i++) {
            d1[i] = d[i];
            sum1 += d1[i];
        }
        Set<Integer> ans1 = geuSubSum(d1);
        if(ans1.contains(0)) return true;

        int[] d2 = new int[n-m];
        for (int i = 0; i < n-m; i++) d2[i] = d[i+m];
        Set<Integer> ans2 = geuSubSum(d2);
        if(ans2.contains(0)) return true;
        for(int val : ans1){
            if(val == sum1) continue;//全取不行
            if(ans2.contains(-val)) {
                return true;
            }
        }
        return false;
    }
    public Set<Integer> geuSubSum(int[] d){
        Set<Integer> ans = new HashSet<>();
        int n = d.length;
        for(int mask = 1; mask < (1<<n); mask++){
            int sum = 0;
            for(int i = 0; i < n; i++){
                if((mask&(1<<i)) != 0) sum += d[i];
            }
            ans.add(sum);
        }
        return ans;
    }
    public static void main(String args[]) {
        System.out.println(new Leetcode0805_1().splitArraySameAverage(new int[]{1,2,3,4,5,6,7,8}));
    }
}
