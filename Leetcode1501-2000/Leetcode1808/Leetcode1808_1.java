package Leetcode1808;

public class Leetcode1808_1 {
    int MOD = (int)1e9 + 7;
    public int maxNiceDivisors(int primeFactors) {
        if(primeFactors < 4) return primeFactors;
        int plus = primeFactors % 3;
        int div = primeFactors / 3;
        long ans = 1;
        if(plus == 0) ans = pow(3, div);
        else if(plus == 2) ans = pow(3, div) * 2 % MOD;
        else ans = pow(3, div-1) * 4 % MOD;
        return (int)ans;
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
