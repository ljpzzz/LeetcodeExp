package Leetcode0996;

import java.util.*;

public class Leetcode0996_2 {
    int MOD = (int)1e9+7;
    int BASE = (int)1e9+9;
    int n;
    Set<Long> hashVal = new HashSet<>();
    int[] nums;
    List<Integer>[] g;
    public int numSquarefulPerms(int[] nums) {
        n = nums.length;
        this.nums = nums;
        g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        //预处理可以作为相邻值的2个数字的邻接表
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                long multi = 1L*nums[i]+nums[j];
                long sqrt = (long)Math.sqrt(multi);
                if(sqrt*sqrt == multi){
                    g[i].add(j);
                    g[j].add(i);
                }
            }
        }
        dfs(0, 0L, -1);
        return hashVal.size();
    }
    public void dfs(int mask, long hash, int prev){
        if(mask == ((1<<n)-1)){
            hashVal.add(hash);
            return;
        }
        Set<Integer> selected = new HashSet<>();
        //还是取第一个数字
        if(prev == -1){
            for(int i = 0; i < n; i++) {
                if(selected.contains(nums[i])) continue;
                selected.add(nums[i]);
                dfs(1<<i, nums[i], i);
            }
        }
        else{
            for(int next: g[prev]){
                if((mask&(1<<next)) == 0){
                    if(selected.contains(nums[next])) continue;
                    selected.add(nums[next]);
                    dfs(mask|(1<<next),(hash*BASE+nums[next])%MOD,next);
                }
            }
        }
    }
}
