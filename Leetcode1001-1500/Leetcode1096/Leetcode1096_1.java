package Leetcode1096;

import java.util.*;

public class Leetcode1096_1 {
    public List<String> braceExpansionII(String expression) {
        Set<String> ans = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        q.offer(expression);
        while(!q.isEmpty()){
            String cur = q.poll();
            if(cur.indexOf('{') == -1){
                ans.add(cur);
                continue;
            }
            int start = -1;
            int end = -1;
            for(int i = 0; i < cur.length(); i++){
                if(cur.charAt(i) == '{') start = i;
                else if(cur.charAt(i) == '}'){
                    end = i;
                    break;
                }
            }
            String left = cur.substring(0, start);
            String[] mid = cur.substring(start + 1, end).split(",");
            String right = cur.substring(end + 1);
            for(String m : mid) q.add(left + m + right);

        }
        List<String> finalAns = new ArrayList<>(ans);
        Collections.sort(finalAns);
        return finalAns;
    }
}
