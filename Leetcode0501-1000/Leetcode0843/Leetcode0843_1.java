package Leetcode0843;

import java.util.*;

// This is the Master's API interface.
  // You should not implement it, or speculate about its implementation
interface Master {
      public int guess(String word);
 }

public class Leetcode0843_1 {
    public void findSecretWord(String[] words, Master master) {
        int n = words.length;
        // key|:str, value-key: match cnt, value-value: match strs
        Map<String, Map<Integer, List<String>>> maps = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                int cnt = 0;
                for(int k = 0; k < 6; k++){
                    if(words[i].charAt(k) == words[j].charAt(k)) cnt++;
                }
                maps.computeIfAbsent(words[i], k->new HashMap<>()).computeIfAbsent(cnt, k->new ArrayList<>()).add(words[j]);
            }
        }
        Set<String> candidates = new HashSet<>(Arrays.asList(words));
        while(!candidates.isEmpty()){
            int minMaxCnt = n;
            String minMaxStr = "";
            for(String str : candidates){
                Map<Integer, List<String>> mapCur = maps.get(str);
                int maxCnt = 0;
                for(int cnt : mapCur.keySet()){
                    if(mapCur.get(cnt).size() > maxCnt) maxCnt = mapCur.get(cnt).size();
                }
                if(maxCnt < minMaxCnt){
                    minMaxCnt = maxCnt;
                    minMaxStr = str;
                }
            }
            int res = master.guess(minMaxStr);
            if(res == 6) return;
            Set<String> candidates2 = new HashSet<>();
            for(String str : maps.get(minMaxStr).get(res)){
                if(candidates.contains(str)) candidates2.add(str);
            }
            candidates = candidates2;
        }
    }
}
