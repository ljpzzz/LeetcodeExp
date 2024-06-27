package Leetcode0076;

import Leetcode0060.Leetcode0060_1;

import java.util.*;

public class Leetcode0076_1 {
    public String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(m < n) return "";
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            set.add(c);
        }
        int left = 0;
        int right = 0;
        int ans = Integer.MAX_VALUE;
        int ansLeft = 0;
        Map<Character, Integer> mapCur = new HashMap<>();
        while(right < m){
            char c = s.charAt(right);
            if(!set.contains(c)) {
                right++;
                continue;
            }
            mapCur.put(c, mapCur.getOrDefault(c, 0) + 1);
            while(isOK(map, mapCur)) {
                //System.out.println("OK: " + s.substring(left, right + 1));
                if(ans > right - left + 1){
                    ansLeft = left;
                    ans = right - left + 1;
                }
                char cLeft = s.charAt(left);
                if(set.contains(cLeft)) {
                    mapCur.put(cLeft, mapCur.getOrDefault(cLeft, 0) - 1);
                }
                left++;
            }
            right++;
        }
        if(ans == Integer.MAX_VALUE) return "";
        return s.substring(ansLeft, ansLeft + ans);
    }
    public boolean isOK(Map<Character, Integer> map, Map<Character, Integer> mapCur){
        for(Character c : map.keySet()){
            if(!mapCur.containsKey(c) || mapCur.get(c) < map.get(c)) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0076_1().minWindow("abc", "cba"));
    }
}
