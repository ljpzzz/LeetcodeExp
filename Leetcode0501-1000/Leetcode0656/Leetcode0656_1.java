package Leetcode0656;

import java.util.*;

public class Leetcode0656_1 {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        int[] dp = new int[n]; //dp[i]是到位置i的最小花费
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        //dpPre[i]是到位置i的最小花费对应的路径列表
        List<Integer>[] dpPre = new List[n];
        for(int i = 0; i < n; i++) dpPre[i] = new ArrayList<>();
        dpPre[0].add(1);
        for(int i = 1; i < n; i++){
            if(coins[i] == -1) continue;
            for(int j = Math.max(0, i-maxJump); j < i; j++){
                if(coins[j] == -1 || dp[i] < dp[j] + coins[i]) continue;
                if(dp[i] > dp[j] + coins[i]){
                    dp[i] = dp[j] + coins[i];
                    dpPre[i] = new ArrayList<>(dpPre[j]);
                    dpPre[i].add(i+1);
                }
                else{
                    List<Integer> tmp = new ArrayList<>(dpPre[j]);
                    tmp.add(i+1);
                    if(cmp(tmp, dpPre[i]) < 0) dpPre[i] = tmp;
                }
            }
        }
        return dpPre[n-1];
    }
    public int cmp(List<Integer> l1, List<Integer> l2){
        int m = l1.size();
        int n = l2.size();
        int len = Math.min(m,n);
        for(int i = 0; i < len; i++){
            if(l1.get(i) < l2.get(i)) return -1;
            else if(l1.get(i) > l2.get(i)) return 1;
        }
        if(m < n) return -1;
        else if(m > n) return 1;
        else return 0;
    }
    public static void main(String args[]){
        List<Integer> ans = new Leetcode0656_1().cheapestJump(new int[]{1,2,4,2,1},2);
        for(int tmp : ans) System.out.print(tmp + " ");
        System.out.println();
    }
}
