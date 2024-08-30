package Leetcode1015;

public class Leetcode1015_1 {
    public int smallestRepunitDivByK(int k) {
        if(k%2 == 0) return -1;
        if(k == 1) return 1;
        long val = 1;
        for(int len = 1; len <= 1000000; len++){
            val = val%k;
            if(val == 0) return len;
            val = val*10+1;
        }
        return -1;
    }
    public static void main(String[] args) {
        Leetcode1015_1 leetcode1015 = new Leetcode1015_1();
        System.out.println(leetcode1015.smallestRepunitDivByK(3));
    }
}
