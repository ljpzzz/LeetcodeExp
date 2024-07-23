package Leetcode0818;

import java.util.*;

public class Leetcode0818_1 {
    Map<Integer, Integer> map = new HashMap<>();
    public int racecar(int target) {
        map.put(0, 0);
        int ans = dfs(target);
        return ans;
    }
    public int dfs(int target){
        if(map.get(target) != null) return map.get(target);
        int k = (int)Math.ceil(Math.log(target+1)/Math.log(2));
        int ans = 0x3f3f3f3f;
        for(int i = 1; i <= k; i++){
            int dis = (int)Math.pow(2, i)-1;
            if(dis == target) {
                map.put(target, i);
                return i;
            }
            else if(dis > target) ans = Math.min(ans, dfs(dis-target) + i + 1);
            else{
                for(int j = 0; j < i; j++){
                    int backDis = (int)Math.pow(2, j)-1;
                    ans = Math.min(ans, dfs(target-dis+backDis) + j + 1 + i+1);
                }
            }
        }
        map.put(target, ans);
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0818_1().racecar(5));
    }
}
