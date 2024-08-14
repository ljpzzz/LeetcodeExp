package Leetcode1449;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1449_1 {
    public String largestNumber(int[] cost, int target) {
        int[] dp = new int[target + 1]; // dp[i] 表示 i 的最大长度
        Arrays.fill(dp, -1);
        String[] dpStr = new String[target + 1]; // dpStr[i] 表示 i 的最大长度对应的字符串
        dp[0] = 0;
        dpStr[0] = "";
        for(int i = 9; i >= 1; i--){
            for(int j = cost[i - 1]; j <= target; j++){ //完全背包
                if(dp[j - cost[i - 1]] == -1) continue;
                if(dp[j - cost[i - 1]] + 1 > dp[j]){
                    dp[j] = dp[j - cost[i - 1]] + 1;
                    dpStr[j] = dpStr[j - cost[i - 1]] + String.valueOf(i);
                }
                else if(dp[j - cost[i - 1]] + 1 == dp[j]){
                   if(dpStr[j].compareTo(dpStr[j - cost[i - 1]] + String.valueOf(i)) < 0){
                       dpStr[j] = dpStr[j - cost[i - 1]] + String.valueOf(i);
                   }
                }
            }
        }
        if(dp[target] == -1) return "0";
        else return dpStr[target];
    }
}
