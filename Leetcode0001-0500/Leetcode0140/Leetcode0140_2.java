package Leetcode0140;

import java.util.*;

public class Leetcode0140_2 {
    //DP
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>();
        for(String word :wordDict) wordSet.add(word);
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        // key: dp值为true的index， value：对应的可行的字符串前缀
        Map<Integer, List<String>> candidateMap = new HashMap<>();
        dp[0] = true;
        candidateMap.put(0, new ArrayList<>());
        candidateMap.get(0).add("");
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                String tmp = s.substring(j, i);
                if(dp[j] && wordSet.contains(tmp)) {
                    dp[i] = true;
                    if(candidateMap.get(i) == null) candidateMap.put(i, new ArrayList<>());
                    for(String pre : candidateMap.get(j)){
                        if(j != 0) candidateMap.get(i).add(pre + " " + tmp);
                        else candidateMap.get(i).add(tmp);
                    }
                }
            }
        }
        if(!dp[n]) return new ArrayList<String>();
        else return candidateMap.get(n);
    }
}
