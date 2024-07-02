package Leetcode0269;

import java.util.*;

public class Leetcode0269_1 {
    //LCR 114. 火星词典 https://leetcode.cn/problems/Jf1JuT/description/
    public String alienOrder(String[] words) {
        List<Character[]> edges = new ArrayList<>();
        int n = words.length;
        Set<Character> wordSet = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) wordSet.add(word.charAt(i));
        }
        for (int i = 0; i < n-1; i++) {
            String s1 = words[i];
            String s2 = words[i+1];
            //相同的话没有任何信息提供
            if(s1.equals(s2)) continue;
            int len1 = s1.length();
            int len2 = s2.length();
            //s2是s1的前缀，矛盾
            if(s1.startsWith(s2)) return "";
            //s1是s2的前缀，此时没有任何信息可以提供
            if(s2.startsWith(s1)) continue;
            for (int j = 0; j < Math.min(len1, len2); j++) {
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                if(c1 != c2){
                    edges.add(new Character[]{c1, c2});
                    break;
                }
            }
        }
        Map<Character, Set<Character>> g = new HashMap<>();
        Map<Character, Integer> degrees = new HashMap<>();

        for(Character[] edge : edges) {
            Character c1 = edge[0];
            Character c2 = edge[1];
            g.computeIfAbsent(c1, k -> new HashSet<>());
            //不重复的边才能加入度
            if(g.get(c1).add(c2)) degrees.put(c2, degrees.getOrDefault(c2, 0) + 1);
        }

        //拓扑排序
        Deque<Character> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for(Character c : wordSet) {
            if(!degrees.containsKey(c)) {
                q.offer(c);
                sb.append(c);
            }
        }

        while(!q.isEmpty()) {
            Character c = q.poll();
            if(!g.containsKey(c)) continue;
            for(Character next : g.get(c)){
                degrees.put(next, degrees.get(next) - 1);
                if(degrees.get(next) == 0) {
                    q.offer(next);
                    sb.append(next);
                }
            }
        }
        if(sb.length() == wordSet.size()) return sb.toString();
        else return "";
    }
    public static void main(String[] args) {
        String[] words = {"ac","ab","zc","zb"};
        Leetcode0269_1 leetcode0269_1 = new Leetcode0269_1();
        String s = leetcode0269_1.alienOrder(words);
        System.out.println(s);
    }
}
