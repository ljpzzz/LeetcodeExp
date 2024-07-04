package Leetcode0425;

import java.util.*;

public class Leetcode0425_1 {
    public List<List<String>> wordSquares(String[] words) {
        int m = words.length;
        int n = words[0].length();
        List<List<String>> ans = new ArrayList<>();
        if(n == 1){
            for(String w : words) ans.add(Arrays.asList(w));
            return ans;
        }
        Map<String, Set<String>> p1 = new HashMap<>();
        for(String w : words) p1.computeIfAbsent(w.substring(0,1), k->new HashSet<>()).add(w);
        if(n == 2){
            for(String w : words){
                String prev = w.substring(1,2);
                if(!p1.containsKey(prev)) continue;
                for(String w2 : p1.get(prev)) ans.add(Arrays.asList(w, w2));
            }
            return ans;
        }
        Map<String, Set<String>> p2 = new HashMap<>();
        for(String w : words) p2.computeIfAbsent(w.substring(0,2), k->new HashSet<>()).add(w);
        if(n == 3){
            for(String w : words) {
                String prev = w.substring(1, 2);
                if(!p1.containsKey(prev)) continue;
                for (String w2 : p1.get(prev)) {
                    String prev2 = w.substring(2, 3) + w2.substring(2, 3);
                    if(!p2.containsKey(prev2)) continue;
                    for (String w3 : p2.get(prev2)) ans.add(Arrays.asList(w, w2, w3));
                }
            }
            return ans;
        }
        Map<String, Set<String>> p3 = new HashMap<>();
        for(String w : words) p3.computeIfAbsent(w.substring(0,3), k->new HashSet<>()).add(w);
        for(String w : words) {
            String prev = w.substring(1, 2);
            if(!p1.containsKey(prev)) continue;
            for (String w2 : p1.get(prev)) {
                String prev2 = w.substring(2, 3) + w2.substring(2, 3);
                if(!p2.containsKey(prev2)) continue;
                for (String w3 : p2.get(prev2)) {
                    String prev3 = w.substring(3, 4) + w2.substring(3, 4)  + w3.substring(3,4);
                    if(!p3.containsKey(prev3)) continue;
                    for(String w4 : p3.get(prev3)) ans.add(Arrays.asList(w, w2, w3, w4));
                }
            }
        }
        return ans;
    }
    public static void main(String args[]){
        List<List<String>> ans = new Leetcode0425_1().wordSquares(new String[]{"area", "lead","wall", "lady", "ball"});
        for(List<String> cur : ans) {
            for(String tmp : cur) System.out.print(tmp + " " );
            System.out.println();
        }
        List<List<String>> ans2 = new Leetcode0425_1().wordSquares(new String[]{"abat", "baba","atan", "atal"});
        for(List<String> cur : ans2) {
            for(String tmp : cur) System.out.print(tmp + " " );
            System.out.println();
        }
    }
}

