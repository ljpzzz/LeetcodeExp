package Leetcode0878;

public class Leetcode0878_1 {
    public int nthMagicalNumber(int n, int a, int b) {
        long left = 1;
        long right = Long.MAX_VALUE;
        long ans = -1;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long cnt = mid / a + mid / b - mid / lcm(a, b);
            if (cnt < n) left = mid + 1;
            else {
                ans = mid;
                right = mid - 1;
            }
        }
        return (int)(ans%1000000007);
    }
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
