package Leetcode0358;

import java.util.*;

public class Leetcode0358_1 {
    public String rearrangeString(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];
        for(int i = 0; i< n; i++) cnt[s.charAt(i)-'a']++;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(b[1]-a[1]));
        for(int i = 0; i < 26; i++) {
            if(cnt[i] > 0) pq.add(new int[]{i,cnt[i]});
        }
        TreeSet<Integer> avail = new TreeSet<>();
        int[] prev = new int[26];
        Arrays.fill(prev, -1000000);
        for(int i = 0; i <n; i++) avail.add(i);
        char[] ans = new char[n];
        while(pq.size() > 0){
            int[] tmp = pq.poll();
            Integer index = avail.ceiling(prev[tmp[0]]+k);
            if(index == null) return "";
            ans[index] = (char)('a' + tmp[0]);
            prev[tmp[0]] = index;
            avail.remove(index);
            if(tmp[1] > 1) pq.add(new int[]{tmp[0], tmp[1]-1});
        }
        return  new String(ans);
    }
}
