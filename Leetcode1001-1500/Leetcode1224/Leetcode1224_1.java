package Leetcode1224;

import java.util.*;

public class Leetcode1224_1 {
    public int maxEqualFreq(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> numCntMap = new HashMap<>();
        Map<Integer, Integer> cntNumMap = new HashMap<>();
        numCntMap.put(nums[0], 1);
        cntNumMap.put(1, 1);
        int ans = 1;
        for(int i = 1; i < n; i++){
            if(!numCntMap.containsKey(nums[i])){
                numCntMap.put(nums[i], 1);
                cntNumMap.put(1, 1+cntNumMap.getOrDefault(1, 0));
            }
            else{
                int oldCnt = numCntMap.get(nums[i]);
                numCntMap.put(nums[i], oldCnt+1);
                cntNumMap.put(oldCnt, cntNumMap.get(oldCnt)-1);
                if(cntNumMap.get(oldCnt) == 0) cntNumMap.remove(oldCnt);
                cntNumMap.put(oldCnt+1, cntNumMap.getOrDefault(oldCnt+1, 0)+1);
            }
            //只有一个数字,符合要求
            if(numCntMap.size() == 1){
                ans = i+1;
                continue;
            }
            //不止一个数字，就要看频率了
            //有三个不同的频率，肯定不行
            if(cntNumMap.size() >= 3 ) continue;
            //只有一个频率，那么只能是频率1才行
            if(cntNumMap.size() == 1){
                if(cntNumMap.containsKey(1)) ans = i+1;
                continue;
            }
            //到此就是两个频率，只有一个是频率1且只有一个，或者2个相邻的频率，且较大的频率对应的值只有一个
            List<Integer> kList = new ArrayList<>(cntNumMap.keySet());
            Collections.sort(kList);
            if(kList.get(0) == 1 && cntNumMap.get(kList.get(0)) == 1 ||
                    kList.get(0) + 1 == kList.get(1) && cntNumMap.get(kList.get(1)) == 1) ans = i+1;
        }
        return ans;
    }
}
