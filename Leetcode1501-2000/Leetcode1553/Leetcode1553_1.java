package Leetcode1553;

import java.util.*;

public class Leetcode1553_1 {
    Map<Integer, Integer> memo = new HashMap<>();
    public int minDays(int n) {
        memo.put(0, 0);
        return dfs(n);
    }
    public int dfs(int n){
        if(memo.containsKey(n)) return memo.get(n);
        int ans = n;
        ans = Math.min(ans, dfs(n/2) + n%2 + 1);
        ans = Math.min(ans, dfs(n/3) + n%3 + 1);
        memo.put(n, ans);
        return ans;
    }
    public static void main(String[] args) {
        Leetcode1553_1 leetcode1553_1 = new Leetcode1553_1();
        System.out.println(leetcode1553_1.minDays(6));
    }
}
