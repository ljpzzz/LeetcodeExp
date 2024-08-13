package Leetcode1392;

public class Leetcode1392_1 {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] next = new int[n];
        for (int i = 1; i < n; ++i) {
            int j = next[i - 1];
            while (j != 0 && s.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            if (s.charAt(j) == s.charAt(i)) {
                next[i] = j + 1;
            }
        }
        if(next[n - 1] == 0) return "";
        else return s.substring(0, next[n - 1]);
    }
}
