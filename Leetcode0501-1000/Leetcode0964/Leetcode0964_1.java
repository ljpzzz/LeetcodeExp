package Leetcode0964;

import java.util.*;

public class Leetcode0964_1 {
    Map<Integer, Integer> memo = new HashMap<>();
    int target;
    int x;
    public int leastOpsExpressTarget(int x, int target) {
        this.target = target;
        this.x = x;
        memo.put(x, 0);
        return dfs(target);
    }
    public int dfs(int val){
        if(memo.get(val) != null) return memo.get(val);
        int ans = Integer.MAX_VALUE;
        //当val<x时，有两种表示方式：
        // 1. x/x+x/x+...+x/x, 即val组x/x相加,共2*val-1个运算符
        // 2. x-x/x-x/x-...x/x, 即x减去(x-val)组x/x相加， 共2*(x-val)个运算符
        if(val < x) ans =  Math.min(2*val-1, 2*(x-val));
        else{
            //x^p < val <= x^(p+1) = t
            int p = 0;
            long t = x;
            while(t < val) {
                t *= x;
                p++;
            }
            //x^(p+1)比val大太多，此时可以先到x^p，再处理val-x^p
            if(t-val >= val) ans =  p-1+dfs(val-(int)(t/x))+1;
            else {
                //两种情况，先到x^p，再处理val-x^p，或者先到x^(p+1)， 再次处理x^(p+1)-val
                ans = Math.min(p-1+dfs(val - (int) (t / x))+1, p + dfs((int) (t - val))+1);
            }
        }
        memo.put(val, ans);
        return ans;
    }
    public static void main(String[] args)
    {
        Leetcode0964_1 test = new Leetcode0964_1();
        System.out.println(test.leastOpsExpressTarget(5, 501));
    }
}
