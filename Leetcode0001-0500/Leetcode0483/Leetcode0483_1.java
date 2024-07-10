package Leetcode0483;

public class Leetcode0483_1 {
    public String smallestGoodBase(String n) {
        //1*k^0 +1*k^1+...+1*k^m = n
        // n^{1/m}-1 <= k < n^{1/m}
        // 1 <= m < log_k^n < log_2^n
        long num = Long.parseLong(n);
        long M = (long)(Math.log(num)/Math.log(2));
        for(long m = M; m >= 2; m--) {
            long k = (long)(Math.pow(num, 1.0/m));
            long base = 1;
            long val = 1;
            for(int i = 1; i <= m; i++){
                base *= k;
                val += base;
            }
            if(val == num) return String.valueOf(k);
        }
        return String.valueOf(num-1);
    }
}
