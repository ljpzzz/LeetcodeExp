package Leetcode1595;

import java.util.*;

public class Leetcode1595_2 {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size();
        int n = cost.get(0).size();
        int[][] dp = new int[m + 1][1 << n];
        for (int i = 0; i <= m; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j < (1 << n); j++) {
                //i和k相连
                for (int k = 0; k < n; k++) {
                    if ((j & (1 << k)) == 0) continue;
                    // opt1: i仅仅和k相连，k仅仅和i相连
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j ^ (1 << k)] + cost.get(i - 1).get(k));
                    //opt2: i仅仅和k相连, k无要求
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + cost.get(i - 1).get(k));
                    //op3: i无要求，k仅仅和i相连
                    dp[i][j] = Math.min(dp[i][j], dp[i][j ^ (1 << k)] + cost.get(i - 1).get(k));

                }
            }
        }
        return dp[m][(1 << n) - 1];
    }
}
