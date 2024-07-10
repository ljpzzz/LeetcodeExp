package Leetcode0514;

import java.util.*;

public class Leetcode0514_1 {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; i++) pos[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) pos[ring.charAt(i) - 'a'].add(i);
        int m = key.length();
        //dp[i][j]表示ring位置在j, key位置在i时的最小步数, 此时只有ring[j] = key[i]时, 才能进行操作
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        //初始化，从位置0开始，遍历所有和key[0]相等的位置
        for(int p : pos[key.charAt(0) - 'a']) dp[0][p] = Math.min(p, n - p) + 1;
        //当前key位置在i
        for(int i = 1; i < m; i++) {
            //当前ring位置在j
            for(int j : pos[key.charAt(i) - 'a']) {
                //之前ring位置在k
                for(int k : pos[key.charAt(i - 1) - 'a']){
                    int offset = Math.abs(j - k);
                    int distance = Math.min(offset, n - offset);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + distance + 1);
                }
            }
        }
        int ans = 0x3f3f3f3f;
        for(int j : pos[key.charAt(m - 1) - 'a']) ans = Math.min(ans, dp[m - 1][j]);
        return ans;
    }
    public static void main(String[] args){
        Leetcode0514_1 leetcode0514_1 = new Leetcode0514_1();
        System.out.println(leetcode0514_1.findRotateSteps("godding", "gd"));
    }
}
