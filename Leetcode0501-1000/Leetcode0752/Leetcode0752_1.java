package Leetcode0752;

import java.util.*;

public class Leetcode0752_1 {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        String start = "0000";
        if(dead.contains(start)) return -1;
        Queue<String> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        q.add("0000");
        vis.add("0000");
        int distance = 0;
        while(!q.isEmpty()){
            int len = q.size();
            for(int t = 0; t < len; t++) {
                String cur = q.poll();
                if (cur.equals(target)) return distance;
                for (int i = 0; i < 4; i++) {
                    StringBuilder sb = new StringBuilder(cur);
                    char pos = cur.charAt(i);
                    if (pos != '9') sb.setCharAt(i, (char) (cur.charAt(i) + 1));
                    else sb.setCharAt(i, '0');
                    if (!dead.contains(sb.toString()) && !vis.contains(sb.toString())) {
                        q.add(sb.toString());
                        vis.add(sb.toString());
                    }
                    if (pos != '0') sb.setCharAt(i, (char) (cur.charAt(i) - 1));
                    else sb.setCharAt(i, '9');
                    if (!dead.contains(sb.toString()) && !vis.contains(sb.toString())) {
                        q.add(sb.toString());
                        vis.add(sb.toString());
                    }
                }
            }
            distance++;
        }
        return -1;
    }
    public static void main(String[] args) {
        Leetcode0752_1 leetcode0752_1 = new Leetcode0752_1();
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(leetcode0752_1.openLock(deadends, target));
    }
}
