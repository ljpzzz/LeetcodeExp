package Leetcode0761;

import java.util.*;

public class Leetcode0761_1 {
    public String makeLargestSpecial(String s) {
        int n = s.length();
        if(n <= 2) return s;
        List<String> strs = new ArrayList<>();
        int cnt = 0;
        int left = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') cnt++;
            else{
                cnt--;
                if (cnt == 0) {
                    strs.add("1" + makeLargestSpecial(s.substring(left+1, i)) + "0");
                    left = i+1;
                }
            }
        }
        Collections.sort(strs, (a,b)-> b.compareTo(a));
        StringBuilder ans = new StringBuilder();
        for (String str : strs) ans.append(str);
        return ans.toString();
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0761_1().makeLargestSpecial("11011000"));
    }
}
