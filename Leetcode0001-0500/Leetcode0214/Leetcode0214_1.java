package Leetcode0214;

public class Leetcode0214_1 {
    //manacher+前缀判断
    public String shortestPalindrome(String s) {
        int n = s.length();
        int[][] dp = isHuiwenPreAndPost(s);
        int maxLen = 0;
        for (int i = n-1; i >= 0; i--) {
            if (dp[i][0] > 0) {
                return new StringBuffer(s.substring(i+1)).reverse().toString() + s;
            }
        }
        return "";
    }
    // 使用Manacher算法寻找以index结束和开始的子串是否回文子串
    //返回值里第一维是字符串的位置index， 第二维大小是2，第一个是[0, index]是否是回文,第二个是[index, n-1]是否是回文
    public int[][] isHuiwenPreAndPost(String s) {
        int n = s.length();
        StringBuffer tmp = new StringBuffer("#");
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                tmp.append('*');
            }
            tmp.append(s.charAt(i));
        }
        tmp.append('!');
        int m = n * 2;
        int[] len = new int[m];
        int[][] ret = new int[n][2];
        int p = 0, maxn = -1;
        for (int i = 1; i < m; i++) {
            len[i] = maxn >= i ? Math.min(len[2 * p - i], maxn - i) : 0;
            while (tmp.charAt(i - len[i] - 1) == tmp.charAt(i + len[i] + 1)) {
                len[i]++;
            }
            if (i + len[i] > maxn) {
                p = i;
                maxn = i + len[i];
            }
            if (i - len[i] == 1) {
                ret[(i + len[i]) / 2][0] = 1;
            }
            if (i + len[i] == m - 1) {
                ret[(i - len[i]) / 2][1] = 1;
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        Leetcode0214_1 solution = new Leetcode0214_1();
        String s = "aacecaaa";
        System.out.println(solution.shortestPalindrome(s));
    }
}
