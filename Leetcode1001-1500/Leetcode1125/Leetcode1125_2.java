package Leetcode1125;

import java.util.*;

public class Leetcode1125_2 {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> codeMap = new HashMap<>();
        int n = req_skills.length;
        for(int i = 0; i < n; i++) codeMap.put(req_skills[i], i);
        int m = people.size();
        int[] pArr = new int[m];
        for(int i = 0; i < m; i++){
            List<String> cur = people.get(i);
            int mask = 0;
            for(String skill : cur) mask |= 1<<(codeMap.get(skill));
            pArr[i] = mask;
            //System.out.println("Mask is " + Integer.toBinaryString(mask));
        }
        //dp[i]表示mask为i时需要的最少人数
        int[] dp = new int[1<<n];
        List<Integer>[] dpList = new List[1<<n];
        for(int i = 0; i < (1<<n); i++) dpList[i] = new ArrayList<>();
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for(int i = 0; i < (1<<n); i++){
            //第j个人有i里面没有的课，则可以尝试更新
            for(int j = 0; j < m; j++){
                if((i|pArr[j]) == i) continue;
                if(dp[i|pArr[j]]> 1+dp[i]){
                    dp[i|pArr[j]] =  1+dp[i];
                    dpList[i|pArr[j]] = new ArrayList<>(dpList[i]);
                    dpList[i|pArr[j]].add(j);
                }
            }
        }
        int size = dpList[(1<<n)-1].size();
        int[] res =  new int[size];
        for(int i = 0; i < size; i++) res[i] = dpList[(1<<n)-1].get(i);
        return res;
    }
}
