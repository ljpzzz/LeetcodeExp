package Leetcode0996;

import java.util.*;

public class Leetcode0996_1 {
    int ans = 0;
    public int numSquarefulPerms(int[] nums) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        for(int num : nums) cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        dfs(-1, cntMap);
        return ans;
    }
    public void dfs(int num, Map<Integer, Integer> cntMap){
        if(cntMap.isEmpty()){
            ans++;
            return;
        }
        Map<Integer, Integer> cntMap2 = new HashMap<>(cntMap);
        for(int key : cntMap2.keySet()){
            if(num == -1 || (long)Math.sqrt(num + key)*(long)Math.sqrt(num + key) == num + key){
                int cnt = cntMap.get(key);
                if(cnt > 1) cntMap.put(key, cnt - 1);
                else cntMap.remove(key);
                dfs(key, cntMap);
                cntMap.put(key, cnt);
            }
        }
    }
    public static void main(String[] args)
    {
        int[] nums = {2, 2, 2};
        Leetcode0996_1 test = new Leetcode0996_1();
        System.out.println(test.numSquarefulPerms(nums));
    }
}
