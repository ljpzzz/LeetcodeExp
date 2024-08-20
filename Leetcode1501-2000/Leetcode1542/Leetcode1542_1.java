package Leetcode1542;

import java.util.*;

public class Leetcode1542_1 {
    public int longestAwesome(String s) {
        int n = s.length();
        Map<Integer, Integer> maskInxMap = new HashMap<>();
        maskInxMap.put(0, -1); //哨兵
        int ans = 0;
        int mask = 0;
        for(int i = 0; i < n; i++){
            int val = s.charAt(i) - '0';
            mask ^= (1 << val);
            //全偶数个数的回文
            if(maskInxMap.containsKey(mask)) ans = Math.max(ans, i - maskInxMap.get(mask));
            //有一个奇数的回文
            for(int j = 0; j < 10; j++){
                int newMask = mask ^ (1 << j);
                if(maskInxMap.containsKey(newMask)) ans = Math.max(ans, i - maskInxMap.get(newMask));
            }
            if(!maskInxMap.containsKey(mask)) maskInxMap.put(mask, i);
        }
        return ans;
    }
}
