package Leetcode0411;

import java.util.*;

class Leetcode0411_1 {
    Set<String> candidates = new HashSet<>();
    int m;
    public String minAbbreviation(String target, String[] dictionary) {
        m = target.length();
        int n = dictionary.length;
        if (n == 0) return String.valueOf(m);
        dfs(target, 0, "");
        for(String dict :dictionary){
            if(dict.length() != m) continue;
            dfsCheck(dict, 0, "");
        }
        String ans = target;
        int ansCnt = m;
        for(String can : candidates){
            int cnt = 0;
            for(int i = 0; i < can.length(); i++){
                if(can.charAt(i) >= 'a' && can.charAt(i) <= 'z') cnt++;
                else if(i == 0 || can.charAt(i-1) >= 'a' && can.charAt(i-1) <= 'z') cnt++;
            }
            if(cnt < ansCnt) {
                ans = can;
                ansCnt = cnt;
            }
        }
        return ans;
    }
    public void dfs(String target, int pos, String prev){
        if(pos == m){
            candidates.add(prev);
            return;
        }
        //当前为字母
        dfs(target, pos+1, prev+target.charAt(pos));
        //之前为字母或者空，则当前可以为数字
        if(prev.isEmpty() || prev.charAt(prev.length()-1) >= 'a' && prev.charAt(prev.length()-1) <= 'z'){
            for(int len = 1; len <= m-pos; len++) dfs(target, pos+len, prev+len);
        }
    }
    public void dfsCheck(String target, int pos, String prev){
        if(pos == m){
            candidates.remove(prev);
            return;
        }
        //当前为字母
        dfsCheck(target, pos+1, prev+target.charAt(pos));
        //之前为字母或者空，则当前可以为数字
        if(prev.isEmpty() || prev.charAt(prev.length()-1) >= 'a' && prev.charAt(prev.length()-1) <= 'z'){
            for(int len = 1; len <= m-pos; len++) dfsCheck(target, pos+len, prev+len);
        }
    }
}
