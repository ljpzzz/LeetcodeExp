package Leetcode0829;

public class Leetcode0829_1 {
    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        int m = (int)Math.sqrt(2 * n);
        for(int i = 1; i <= m; i++){
            int delta = n - i * (i - 1) / 2;
            if(delta % i == 0) ans++;
        }
        return ans;
    }
}
