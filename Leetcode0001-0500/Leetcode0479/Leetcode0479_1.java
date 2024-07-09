package Leetcode0479;

public class Leetcode0479_1 {
    public int largestPalindrome(int n) {
        if(n == 1) return 9;
        int half = (int)Math.pow(10, n) - 1;
        for(int x = half; x >= (int)Math.pow(10, n - 1); x--){
            long val = x;
            for(int i = x; i > 0; i /= 10){
                val = val * 10 + i % 10;
            }
            for(int factor = half; 1L*factor * factor >= val; factor--){
                if(val % factor == 0){
                    return (int)(val % 1337);
                }
            }
        }
        return 0;
    }
}
