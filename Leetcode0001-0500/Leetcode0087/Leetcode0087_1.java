package Leetcode0087;

import java.util.*;

public class Leetcode0087_1 {
    Map<String, Boolean> validMap = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        //记忆化搜索
        if(validMap.get(s1+"#"+s2) != null) return validMap.get(s1+"#"+s2);
        int n = s1.length();
        if(n == 1) {
            boolean res = s1.equals(s2);
            validMap.put(s1+"#"+s2, res);
            return res;
        }
        if(s1.equals(s2)){
            validMap.put(s1+"#"+s2, Boolean.TRUE);
            return true;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for(int i = 0; i < n; i++){
            cnt1[s1.charAt(i)-'a']++;
            cnt2[s2.charAt(i)-'a']++;
        }
        //2个字符串总体不相等，不合法
        if(!isSameCnt(cnt1, cnt2)) {
            validMap.put(s1+"#"+s2, Boolean.FALSE);
            return false;
        }

        for(int i = 1; i < n; i++){
            //满足x+y和x+y的匹配,递归处理
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                validMap.put(s1+"#"+s2, Boolean.TRUE);
                return true;
            }
            //满足x+y和y+x的匹配，递归处理
            if(isScramble(s1.substring(0, i), s2.substring(n-i, n)) && isScramble(s1.substring(i), s2.substring(0, n-i))) {
                validMap.put(s1+"#"+s2, Boolean.TRUE);
                return true;
            }
        }
        validMap.put(s1+"#"+s2, Boolean.FALSE);
        return false;
    }
    public boolean isSameCnt(int[] cnt1, int[] cnt2){
        for(int i = 0; i < 26; i++){
            if(cnt1[i] != cnt2[i]) return false;
        }
        return true;
    }
    public static void main(String[] args)
    {
        System.out.println(new Leetcode0087_1().isScramble("eebaacbcbcadaaedceaaacadccd",
                "eadcaacabaddaceacbceaabeccd"));
    }
}
