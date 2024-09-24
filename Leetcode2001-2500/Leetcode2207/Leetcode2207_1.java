package Leetcode2207;

public class Leetcode2207_1 {
    public long maximumSubsequenceCount(String text, String pattern) {
        int n = text.length();
        int max01 = 0;
        char leftChar = pattern.charAt(0);
        int[] left = new int[n+1]; //left[i]表示[0, i-1]中leftChar的个数
        for(int i = 0; i < n; i++) {
            left[i+1] = left[i] + (text.charAt(i) == leftChar ? 1 : 0);
            max01 = Math.max(max01, left[i+1]);
        }
        char rightChar = pattern.charAt(1);
        int[] right = new int[n+1]; //right[i]表示[i,n)中rightChar的个数
        for(int i = n-1; i >= 0; i--) {
            right[i] = right[i+1] + (text.charAt(i) == rightChar ? 1 : 0);
            max01 = Math.max(max01, right[i]);
        }
        long ans = max01;
        for(int i = 0; i < n; i++){
            if(text.charAt(i) == rightChar) ans += left[i];
        }
        return ans;
    }
}
