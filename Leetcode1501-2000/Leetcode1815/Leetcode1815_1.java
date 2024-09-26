package Leetcode1815;

import java.util.*;

public class Leetcode1815_1 {
    int LEN = 5; //五进制
    int MASK = (1 << LEN)-1;
    int batchSize;
    Map<Long, Integer> memo = new HashMap<>();
    public int maxHappyGroups(int batchSize, int[] groups) {
        this.batchSize = batchSize;
        int n = groups.length;
        int[] plus = new int[batchSize];
        for(int i = 0; i < n; i++){
            plus[groups[i] % batchSize]++;
        }
        long mask = 0;
        for(int i = batchSize-1; i >= 1; i--) mask = (mask << LEN) + plus[i];
        int ans = plus[0];
        ans += dfs(mask);
        return ans;
    }
    public int dfs(long mask){
        if(mask == 0) return 0;
        if(memo.containsKey(mask)) return memo.get(mask);
        long total = 0;
        for(int i = 1; i < batchSize; i++){
            long current = (mask >>((i-1)*LEN))&MASK;
            total += current*i;
        }
        int ans = 0;
        for(int i = 1; i < batchSize; i++){
            long current = (mask >>((i-1)*LEN))&MASK;
            if(current == 0) continue;
            long preMask = mask - (1L<<((i-1)*LEN));
            int curAns = dfs(preMask);
            if(total%batchSize == i) curAns++;
            ans = Math.max(ans, curAns);
        }
        memo.put(mask, ans);
        return ans;
    }
}
