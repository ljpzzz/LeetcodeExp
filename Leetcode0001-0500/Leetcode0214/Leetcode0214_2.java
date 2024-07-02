package Leetcode0214;

public class Leetcode0214_2 {
    //kmp
    public String shortestPalindrome(String s) {
        int n = s.length();
        String ss = s + "#" + new StringBuilder(s).reverse().toString();
        int[] next = getNext(ss);
        int matchLen = next[ss.length() - 1];
        return new StringBuilder(s.substring(matchLen)).reverse().toString() + s;
    }
    public int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); ++i) {
            int j = next[i - 1];
            while (j != 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = next[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                next[i] = j + 1;
            }
        }
        return next;
    }
    public static void main(String[] args) {
        Leetcode0214_2 leetcode0214_2 = new Leetcode0214_2();
        String s = "aacecaaa";
        String res = leetcode0214_2.shortestPalindrome(s);
        System.out.println(res);
    }
}
