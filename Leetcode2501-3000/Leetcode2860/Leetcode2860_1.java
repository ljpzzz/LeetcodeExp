package Leetcode2860;

import java.util.*;

public class Leetcode2860_1 {
    public int countWays(List<Integer> nums) {
        int n = nums.size();
        Collections.sort(nums);
        int ans = 1; //全选
        //选择cnt个
        for(int cnt = n-1; cnt >= 1; cnt--){
            if(nums.get(cnt-1) < cnt && nums.get(cnt) > cnt) ans++;
        }
        if(nums.get(0) > 0) ans++; //全部不选
        return ans;
    }
}
