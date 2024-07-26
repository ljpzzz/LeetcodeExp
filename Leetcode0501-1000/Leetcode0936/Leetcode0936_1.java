package Leetcode0936;

import java.util.*;

public class Leetcode0936_1 {
    public int[] movesToStamp(String stamp, String target) {
        int m = stamp.length();
        int n = target.length();
        if(stamp.charAt(0) != target.charAt(0) || stamp.charAt(m - 1) != target.charAt(n - 1)) return new int[0];
        int[] degrees = new int[n-m+1];
        List<Integer>[] g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int i = 0; i < n - m + 1; i++){
            for(int j = 0; j < m; j++){
                if(stamp.charAt(j) != target.charAt(i + j)){
                    g[i + j].add(i);
                    degrees[i]++;
                }
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n - m + 1; i++){
            if(degrees[i] == 0){
                q.offer(i);
                ans.add(i);
            }
        }
        boolean[] vis = new boolean[n];
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int pos = 0; pos < m; pos++){
                if(vis[cur + pos]) continue;
                vis[cur + pos] = true;
                for(int next : g[cur + pos]){
                    degrees[next]--;
                    if(degrees[next] == 0){
                        q.offer(next);
                        ans.add(0, next);
                    }
                }
            }
        }
        if(ans.size() != n - m + 1) return new int[0];
        int[] fAns = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++) fAns[i] = ans.get(i);
        return fAns;
    }
    public static void main(String[] args) {
        Leetcode0936_1 l = new Leetcode0936_1();
        String stamp = "abca";
        String target = "aabcaca";
        int[] ans = l.movesToStamp(stamp, target);
        for(int i : ans) System.out.print(i + " ");
    }
}
