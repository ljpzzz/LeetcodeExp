package Leetcode0866;

public class Leetcode0866_1 {
    public int primePalindrome(int n) {
        int LEN = Math.max(1,String.valueOf(n).length()/2);
        long ans1 = getOddHuiwen(n, LEN);
        long ans2 = getEvenHuiwen(n, LEN);
        return (int)Math.min(ans1, ans2);
    }
    public long getOddHuiwen(int limit, int LEN){
        int d = 1<<(LEN-1);
        while(true){
            long val = buildHuiwen(d, true);
            //System.out.println("check " + val);
            if(val >= limit && isPrime(val)) return val;
            d++;
        }
    }
    public long getEvenHuiwen(int limit, int LEN){
        int d = 1<<(LEN-1);
        while(true){
            long val = buildHuiwen(d, false);
            if(val >= (long)2*1e8) return Integer.MAX_VALUE;
            //System.out.println("check " + val);
            if(val >= limit && isPrime(val)) return val;
            d++;
        }
    }
    private long buildHuiwen(long x, boolean isOdd){
        long ans = x;
        if(isOdd) x /= 10;
        while (x > 0) {
            ans = 10 * ans + x % 10;
            x /= 10;
        }
        return ans;
    }
    private boolean isPrime(long n){
        if(n == 1) return false;
        for(int i = 2; i <= (int)Math.sqrt(n); i++){
            if(n%i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        //System.out.println(new Leetcode0866_1().primePalindrome(6));
        //System.out.println(new Leetcode0866_1().primePalindrome(8));
        System.out.println(new Leetcode0866_1().primePalindrome(13));
    }
}
