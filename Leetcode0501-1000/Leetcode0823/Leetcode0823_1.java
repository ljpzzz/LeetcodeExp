package Leetcode0823;

import java.util.*;

public class Leetcode0823_1 {
    public int numFactoredBinaryTrees(int[] arr) {
        int MOD = (int)1e9 + 7;
        Arrays.sort(arr);
        int n = arr.length;
        long[] dp = new long[n];
        Arrays.fill(dp, 1);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) map.put(arr[i], i);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(arr[i]%arr[j] != 0 || !map.containsKey(arr[i]/arr[j])) continue;
                int k = map.get(arr[i]/arr[j]);
                dp[i] += dp[j] * dp[k];
                dp[i] %= MOD;
            }
        }
        long ans = 0;
        for(int i = 0; i < n; i++) ans = (ans + dp[i])%MOD;
        return (int)ans;
    }
}
