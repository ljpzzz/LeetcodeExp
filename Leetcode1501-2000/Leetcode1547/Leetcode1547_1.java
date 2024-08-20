package Leetcode1547;

import java.util.*;

public class Leetcode1547_1 {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        List<Integer> segs = new ArrayList<>();
        segs.add(cuts[0]);
        for(int i = 1; i < m; i++) segs.add(cuts[i] - cuts[i - 1]);
        segs.add(n - cuts[m - 1]);
        int ans = 0;
        int len = segs.size();
        int[] pSum = new int[len+1];
        for(int i = 0; i < len; i++) pSum[i+1] = pSum[i] + segs.get(i);
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
            dp[i][i] = 0;
        }
        for(int len2 = 2; len2 <= len; len2++){
            for(int i = 0; i+len2 <= len; i++){
                int j = i + len2 - 1;
                for(int k = i; k < j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + pSum[j+1] - pSum[i]);
                }
            }
        }
        return dp[0][len-1];
    }
    public static void main(String[] args) {
        int n = 7;
        int[] cuts = {1,3,4,5};
        Leetcode1547_1 leetcode1547_1 = new Leetcode1547_1();
        System.out.println(leetcode1547_1.minCost(n, cuts));
    }
}
