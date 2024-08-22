package Leetcode0756;

import java.util.*;

public class Leetcode0756_1 {
    Map<String, List<Character>> g = new HashMap<>();
    Map<String, Boolean> memo = new HashMap<>();
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for(String allow : allowed) {
            g.computeIfAbsent(allow.substring(0, 2), k -> new ArrayList<>()).add(allow.charAt(2));
        }
        return dfs(bottom, 0, "");
    }
    public boolean dfs(String current, int index, String next){
        int len = current.length();
        if(len == 1) return true;
        if(memo.containsKey(current + index + next)) return memo.get(current + index + next);
        if(index == len-1) return dfs(next, 0, ""); //当前已经处理完
        String key = current.substring(index, index+2);
        if(!g.containsKey(key)) {
            memo.put(current + index + next, false);
            return false;
        }
        boolean ans = false;
        for(char c : g.get(key)) {
            ans |=  dfs(current, index+1, next+c);
            if(ans) break; //关键剪枝, 不加会TLE
        }
        memo.put(current + index + next, ans);
        return ans;
    }
    public static void main(String[] args){
        String bottom = "CACAC";
        List<String> allowed = Arrays.asList("ACB","AAC","AAB","BCD","BCC","BAA","CCD","CCA","CAD","DAD","DAC","DCD","ACD","DCA","ABA","BDA","BDC","BDB","BBA","ADD","ADC","CDB","DDA","CBB","CBC","CBA","CDA","CBD","DBA","DBC","DBD");
        Leetcode0756_1 l = new Leetcode0756_1();
        System.out.println(l.pyramidTransition(bottom, allowed));
    }
}
