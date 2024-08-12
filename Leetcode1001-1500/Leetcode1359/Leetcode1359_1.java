package Leetcode1359;

public class Leetcode1359_1 {
    int MOD = (int)1e9+7;
    public int countOrders(int n) {

        long nF = 1; //计算(2n)！
        for(int i = 1; i <= 2*n; i++) nF = (nF*i)%MOD;
        long pow2 = 1; //计算2^n
        for(int i = 1; i <= n; i++) pow2 = (pow2*2)%MOD;
        long ans = (nF*pow(pow2, MOD-2))%MOD;
        return (int)ans;
    }
    //计算a^b%MOD
    private long pow(long a, int b){
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
