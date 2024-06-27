package Leetcode0030;

import java.util.*;

public class Leetcode0030_1 {
    //哈希计数+滑动窗口
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length();
        int m = words.length;
        int len = words[0].length();
        Map<String, Integer> wordCntMap = new HashMap<>();
        for (String word : words) {
            wordCntMap.put(word, wordCntMap.getOrDefault(word, 0) - 1); //这里减，后续遇到了加
        }
        List<Integer> ans = new ArrayList<>();
        //滑动窗口，最多len个
        for(int init = 0; init < len; init++){
            Map<String, Integer> wordCntMap2 = new HashMap<>(wordCntMap);
            for(int i = init; i + len <= n; i += len){
                String current = s.substring(i, i+len);
                wordCntMap2.put(current, wordCntMap2.getOrDefault(current, 0) + 1);
                if(wordCntMap2.get(current) == 0) wordCntMap2.remove(current);
                if(i >= m*len){
                    String prev = s.substring(i-m*len, i-(m-1)*len);
                    wordCntMap2.put(prev, wordCntMap2.getOrDefault(prev, 0) - 1);
                    if(wordCntMap2.get(prev) == 0) wordCntMap2.remove(prev);
                }
                if(wordCntMap2.isEmpty()) ans.add(i-(m-1)*len);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0030_1().findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake",
                new String[]{"fooo","barr","wing","ding","wing"}));
    }
}
