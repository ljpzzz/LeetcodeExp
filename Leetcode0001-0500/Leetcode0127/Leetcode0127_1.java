package Leetcode0127;

import java.util.*;

public class Leetcode0127_1 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        Map<String, List<String>> g = new HashMap<>(); //邻接表
        Map<String, Integer> dist = new HashMap<>(); //距离表
        for(String s : wordList){
            for(int i = 0; i < s.length(); i++){
                String key = s.substring(0, i) + "*" + s.substring(i + 1);
                g.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
            }
        }
        dist.put(beginWord, 0);
        Queue<String> q = new ArrayDeque<>();
        q.add(beginWord);
        int dis = 0;
        boolean found = false;
        while (q.size() > 0){
            int len = q.size();
            while (len-- > 0){
                String cur = q.poll();
                if(cur.equals(endWord)) {
                    found = true;
                    break;
                };
                for(int i = 0; i < cur.length(); i++){
                    String key = cur.substring(0, i) + "*" + cur.substring(i + 1);
                    if(!g.containsKey(key)) continue;
                    for(String next : g.get(key)){
                        if(next.equals(cur)) continue;
                        if(!dist.containsKey(next)){
                            dist.put(next, dis+1);
                            q.offer(next);
                        }
                    }
                }
            }
            if(found) break;
            dis++;
        }
        return dist.get(endWord) == null ? 0 : dist.get(endWord) + 1;
    }
}
