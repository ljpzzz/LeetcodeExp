package Leetcode1153;

import java.util.*;

public class Leetcode1153_1 {
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2)) return true;
        int n = str1.length();
        Map<Integer, Integer> m = new HashMap<>();
        Set<Integer> vis2 = new HashSet<>();
        for(int i = 0; i < n; i++){
            int c1 = str1.charAt(i)-'a';
            int c2 = str2.charAt(i)-'a';
            if(m.get(c1) == null) m.put(c1, c2);
            else if(m.get(c1) != c2) return false;
            //vis2.add(c1);
            vis2.add(c2);
        }
        //就算有环，可以借助没有出现的字符来帮忙解耦
        if(vis2.size() < 26) return true;
        int[] degrees = new int[26];
        for(int c1 : m.keySet()){
            int c2 = m.get(c1);
            degrees[c2]++;
        }
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> vis = new HashSet<>();
        for(int i = 0; i < 26; i++){
            if(degrees[i] == 0){
                vis.add(i);
                q.add(i);
            }
        }
        while(q.size() > 0){
            int cur  = q.poll();
            if(m.get(cur) == null) continue;
            int next = m.get(cur);
            degrees[next]--;
            if(degrees[next] == 0){
                vis.add(next);
                q.add(next);
            }
        }
        return vis.size() == 26;
    }
}
