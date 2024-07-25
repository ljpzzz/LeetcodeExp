package Leetcode0902;

import java.util.*;

public class Leetcode0902_1 {
    List<Integer> candi = new ArrayList<>();
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String nStr = String.valueOf(n);
        int len = nStr.length();
        for(String d : digits) candi.add(Integer.parseInt(d));
        return getCnt(nStr, 0, true, true, new Integer[len]);
    }
    //dp[index]
    public int getCnt(String s, int index, boolean limit, boolean lead0, Integer[] dp){
        int n = s.length();
        if(index == n) {
            if(!lead0) return 1;
            else return 0;
        }
        if(!limit && !lead0 && dp[index] != null) return dp[index];
        int res = 0;
        if(lead0) res += getCnt(s, index+1, false, true, dp);
        int up = limit ? (s.charAt(index)-'0') : candi.get(candi.size() - 1);

        for (int i : candi) {
            if (i > up) continue;
            boolean newLimit = limit && (i == up);
            res += getCnt(s, index + 1, newLimit, false, dp);
        }

        if(!limit & !lead0) dp[index] = res;
        return res;
    }
    public static void main(String[] args) {
        String[] digits = {"1","4"};
        int n = 1000;
        Leetcode0902_1 leetcode0902_1 = new Leetcode0902_1();
        System.out.println(leetcode0902_1.atMostNGivenDigitSet(digits, n));
    }
}
