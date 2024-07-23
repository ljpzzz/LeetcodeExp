package Leetcode0828;

import java.util.Arrays;

public class Leetcode0828_1 {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int[][] pre = new int[n][26];
        int[] tmp = new int[26];
        Arrays.fill(tmp, -1);
        for (int i = 0; i < n; i++) {
            pre[i] = tmp.clone();
            int index = s.charAt(i) - 'A';
            tmp[index] = i;
        }
        int[][] suf = new int[n][26];
        tmp = new int[26];
        Arrays.fill(tmp, n);
        for (int i = n - 1; i >= 0; i--) {
            suf[i] = tmp.clone();
            int index = s.charAt(i) - 'A';
            tmp[index] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'A';
            int prefix = pre[i][index];
            int suffix = suf[i][index];
            ans += (i - prefix) * (suffix - i);
        }
        return ans;
    }
}
