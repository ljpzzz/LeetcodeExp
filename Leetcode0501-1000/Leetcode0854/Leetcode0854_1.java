package Leetcode0854;

import javafx.util.Pair;

import java.util.*;

public class Leetcode0854_1 {
    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        Set<String> vis = new HashSet<>();
        vis.add(s1);
        int step = 0;
        Queue<Pair<String, Integer>> q = new ArrayDeque<>();
        q.add(new Pair<>(s1, 0));
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                Pair<String, Integer> pair = q.poll();
                String cur = pair.getKey();
                int j1 = pair.getValue();
                if(cur.equals(s2)) return step;
                while(j1 < n && cur.charAt(j1) == s2.charAt(j1) ) j1++; //剪枝1：已经好的别动
                for(int j2 = j1 + 1; j2 < n; j2++) {
                    if (cur.charAt(j2) == s2.charAt(j2)) continue; //剪枝2：已经好的别动
                    if (cur.charAt(j1) == cur.charAt(j2)) continue;//剪枝3：动了字符串一样的不动
                    if (cur.charAt(j2) != s2.charAt(j1)) continue;//剪枝4：移动了没有帮助的不动
                    String next = cur.substring(0, j1) + cur.charAt(j2) + cur.substring(j1 + 1, j2) +
                            cur.charAt(j1) + cur.substring(j2 + 1);
                    if (!vis.contains(next)) {
                        vis.add(next);
                        q.add(new Pair<>(next, j1+1));
                    }
                }

            }
            step++;
        }
        return -1;
    }
    public static void main(String[] args)
    {
        Leetcode0854_1 test = new Leetcode0854_1();
        System.out.println(test.kSimilarity("ab", "ba"));
        System.out.println(test.kSimilarity("abc", "bca"));
    }
}
