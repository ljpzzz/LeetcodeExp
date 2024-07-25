package Leetcode0899;

import java.util.*;

public class Leetcode0899_1 {
    public String orderlyQueue(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if(k >= 2) {
            Arrays.sort(arr);
            return new String(arr);
        }
        String ans = s;
        for(int i = 1; i < n; i++) {
            String tmp = s.substring(i) + s.substring(0, i);
            if(tmp.compareTo(ans) < 0) ans = tmp;
        }
        return ans;
    }
}
