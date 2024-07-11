package Leetcode0564;

import java.util.*;

public class Leetcode0564_1 {
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        List<Long> candidates = getCandidates(n);
        long ans = -1;
        long delta = Long.MAX_VALUE;
        for(long tmp : candidates){
            long deltaCurrent = Math.abs(num - tmp);
            if(deltaCurrent == 0) continue;
            else if(deltaCurrent < delta || (deltaCurrent == delta && tmp < ans)){
                ans = tmp;
                delta = deltaCurrent;
            }
        }
        return String.valueOf(ans);
    }
    public List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();
            try {
                candidates.add(Long.parseLong(candidate));
            } catch (NumberFormatException ex) {
                continue;
            }
        }
        return candidates;
    }
}
