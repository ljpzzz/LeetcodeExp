package Leetcode1611;

public class Leetcode1611_1 {
    public int minimumOneBitOperations(int n) {
        int ans = 0;
        while(n > 0){
            ans ^= n;
            n >>= 1;
        }
        return ans;
    }
}
