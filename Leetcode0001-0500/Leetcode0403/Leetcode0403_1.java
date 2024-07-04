package Leetcode0403;

import java.util.*;

public class Leetcode0403_1 {
    //key: i#k, value:位置i是否可以通过跳k个单位的石子到达
    Map<String, Boolean> memo = new HashMap<>();
    int n;
    Map<Integer, Integer> stoneInxMap = new HashMap<>();
    public boolean canCross(int[] stones) {
        n = stones.length;
        for(int i = 0; i < n; i++) stoneInxMap.put(stones[i], i);
        boolean ans = dfs(stones, 0, 0);
        return ans;
    }
    public boolean dfs(int[] stones, int index, int k){
        if(index == n - 1) return true;
        String hash = index + "#" + k;
        if(memo.containsKey(hash)) return memo.get(hash);
        if(k > 0 && stoneInxMap.containsKey(stones[index] + k)){
            if(dfs(stones, stoneInxMap.get(stones[index] + k), k)){
                memo.put(hash, true);
                return true;
            }
        }
        if(stoneInxMap.containsKey(stones[index] + k + 1)){
            if(dfs(stones, stoneInxMap.get(stones[index] + k + 1), k + 1)){
                memo.put(hash, true);
                return true;
            }
        }
        if(k > 1 && stoneInxMap.containsKey(stones[index] + k - 1)){
            if(dfs(stones, stoneInxMap.get(stones[index] + k - 1), k - 1)){
                memo.put(hash, true);
                return true;
            }
        }
        memo.put(hash, false);
        return false;
    }
    public static void main(String[] args) {
        int[] stones = {0,1,3,5,6,8,12,17};
        Leetcode0403_1 test = new Leetcode0403_1();
        System.out.println(test.canCross(stones));
    }
}
