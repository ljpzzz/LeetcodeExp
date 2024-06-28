package Leetcode0126;

import java.util.*;

public class Leetcode0126_1 {
    List<List<String>> res = new ArrayList<>();
    Map<String, List<String>> g = new HashMap<>(); //邻接表
    Map<String, Integer> dist = new HashMap<>(); //距离表
    Map<String, Set<String>> pre = new HashMap<>(); //前置节点
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return res;
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
                            pre.computeIfAbsent(next, k -> new HashSet<>()).add(cur);
                            q.offer(next);
                        }
                        if(dist.get(next) == dis + 1){
                            pre.computeIfAbsent(next, k -> new HashSet<>()).add(cur);
                        }
                    }
                }
            }
            if(found) break;
            dis++;
        }
        List<String> curRes = new ArrayList<>();
        curRes.add(endWord);
        dfs(endWord, curRes, beginWord);
        return res;
    }
    public void dfs(String current, List<String> curRes, String beginWord){
        if(current.equals(beginWord)){
            res.add(new ArrayList<>(curRes));
            return;
        }
        if(!pre.containsKey(current)) return;
        for(String next : pre.get(current)){
            curRes.add(0, next);
            dfs(next, curRes, beginWord);
            curRes.remove(0);
        }
    }
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        Leetcode0126_1 solution = new Leetcode0126_1();
        List<List<String>> res = solution.findLadders(beginWord, endWord, wordList);
        for(List<String> list : res){
            for(String s : list){
                System.out.print(s + " ");
            }
            System.out.println();
        }

    }
}
