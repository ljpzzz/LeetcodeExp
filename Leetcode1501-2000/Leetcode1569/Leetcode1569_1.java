package Leetcode1569;

import java.util.*;

public class Leetcode1569_1 {
    int MOD = (int) 1e9 + 7;
    long[][] c;
    public int numOfWays(int[] nums) {
        int n = nums.length;
        c = new long[n][n];
        c[0][0] = 1;
        //预处理组合数
        for(int i = 1; i < n; i++){
            c[i][0] = 1;
            for(int j = 1; j < n; j++){
                c[i][j] = c[i-1][j] + c[i-1][j-1];
                c[i][j] %= MOD;
            }
        }
        List<Integer> d = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) d.add(nums[i]);
        long ans = dfs(d);
        return (int)((ans-1+MOD)%MOD);
    }
    public long dfs(List<Integer> d){
        int len = d.size();
        if(len <= 2) return 1;
        List<Integer> d1 = new ArrayList<>();
        List<Integer> d2 = new ArrayList<>();
        for(int i = 1; i < d.size(); i++){
            if(d.get(i) < d.get(0)) d1.add(d.get(i));
            else d2.add(d.get(i));
        }
        long ans = c[len-1][d1.size()];
        ans *= dfs(d1);
        ans %= MOD;
        ans *= dfs(d2);
        ans %= MOD;
        return ans;
    }
    public static void main(String[] args) {
        Leetcode1569_1 leetcode1569_1 = new Leetcode1569_1();
        int[] nums = {5,7,3,6,8,2,4,1};
        System.out.println(leetcode1569_1.numOfWays(nums));
    }
}
