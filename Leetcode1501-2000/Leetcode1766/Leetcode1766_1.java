package Leetcode1766;

import java.util.*;

public class Leetcode1766_1 {
    int n;
    int[] nums;
    List<Integer>[] g;
    int[] ans;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        n = nums.length;
        this.nums = nums;
        g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges){
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        ans = new int[n];
        Arrays.fill(ans, -1);
        int[][] lastLv = new int[51][2]; //保存每个数值最近一次出现的层数和对应的节点序号
        for(int i = 0; i < 51; i++) Arrays.fill(lastLv[i], -1);
        dfs(0, -1, lastLv, 0);
        return ans;
    }
    public void dfs(int cur, int prev, int[][] lastLv, int depth){
        int val = nums[cur];
        int ansDepth = -1;
        for(int i = 1; i <= 50; i++){
            if(lastLv[i][0] != -1 && gcd(val, i) == 1){
                if(ansDepth == -1 || ansDepth < lastLv[i][0]) {
                    ans[cur] = lastLv[i][1];
                    ansDepth = lastLv[i][0];
                }
            }
        }
        int[] oldLv = lastLv[val];
        lastLv[val] = new int[]{depth, cur};
        for(int next : g[cur]){
            if(next == prev) continue;
            dfs(next, cur, lastLv, depth+1);
        }
        lastLv[val] = oldLv;
    }
    public int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
    public static void main(String[] args) {
        int[] nums = {2,3,3,2};
        int[][] edges = {{0,1},{1,2},{1,3}};
        Leetcode1766_1 leetcode1766_1 = new Leetcode1766_1();
        System.out.println(Arrays.toString(leetcode1766_1.getCoprimes(nums, edges)));
    }
}
