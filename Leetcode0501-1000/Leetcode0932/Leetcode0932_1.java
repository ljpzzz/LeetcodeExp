package Leetcode0932;

import java.util.*;

public class Leetcode0932_1 {
    Map<Integer, int[]> memo = new HashMap<>();
    public int[] beautifulArray(int n) {
        memo.put(1, new int[]{1});
        return dfs(n);
    }
    public int[] dfs(int n){
        if(memo.containsKey(n)) return memo.get(n);
        int[] res = new int[n];
        int inx = 0;
        int[] odd = dfs((n+1)/2);
        for(int i = 0; i < odd.length; i++) res[inx++] = odd[i]*2-1;
        int[] even = dfs(n/2);
        for(int i = 0; i < even.length; i++) res[inx++] = even[i]*2;
        memo.put(n, res);
        return res;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Leetcode0932_1().beautifulArray(10)));
    }
}
