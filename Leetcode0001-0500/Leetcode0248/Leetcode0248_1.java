package Leetcode0248;

import java.util.*;

public class Leetcode0248_1 {
    public int strobogrammaticInRange(String low, String high) {
        int len1 = low.length();
        int len2 = high.length();
        List<String> s1 = Arrays.asList("0","1","8");
        List<String> s2 = Arrays.asList("11","69","88","96");
        int ans  = 0;
        if(len1 == 1 && len2 >= 1){
            for(String cur : s1) {
                if(isOK(cur, low, high)) ans++;
            }
        }
        if(len2 == 1) return ans;
        if(len1 <= 2 || len2 >= 2){
            for(String cur : s2) {
                if(isOK(cur, low, high)) ans++;
            }
        }
        if(len2 == 2) return ans;
        for(int i = 3; i <= len2; i++){
            //奇数的在偶数中间加上0,1,8
            if(i%2 == 1){
                List<String> s23 = new ArrayList<>();
                int halfLen = (i-1)/2;
                for(String cur : s2){
                    s23.add(cur.substring(0, halfLen) + "0" + cur.substring(halfLen));
                    s23.add(cur.substring(0, halfLen) + "1" + cur.substring(halfLen));
                    s23.add(cur.substring(0, halfLen) + "8" + cur.substring(halfLen));
                }
                if(len1 == i || len2 == i){
                    for(String cur : s23) {
                        if(isOK(cur, low, high)) ans++;
                    }
                }
                else if(len1 < i) ans += s23.size();
            }
            //偶数的在偶数中间加上11,69,88,96
            else{
                List<String> s22 = new ArrayList<>();
                int halfLen = (i-2)/2;
                for(String cur : s2){
                    s22.add(cur.substring(0, halfLen) + "00" + cur.substring(halfLen));
                    s22.add(cur.substring(0, halfLen) + "11" + cur.substring(halfLen));
                    s22.add(cur.substring(0, halfLen) + "69" + cur.substring(halfLen));
                    s22.add(cur.substring(0, halfLen) + "88" + cur.substring(halfLen));
                    s22.add(cur.substring(0, halfLen) + "96" + cur.substring(halfLen));
                }
                if(len1 == i || len2 == i){
                    for(String cur : s22) {
                        if(isOK(cur, low, high)) ans++;
                    }
                }
                else if(len1 < i) ans += s22.size();
                s2 = s22;
            }
        }
        return ans;
    }
    public boolean isOK(String cur, String low, String high){
        long curVal = Long.parseLong(cur);
        long lowVal = Long.parseLong(low);
        long highVal = Long.parseLong(high);
        return curVal >= lowVal && curVal <= highVal;
    }
    public static void main(String[] args) {
        Leetcode0248_1 leetcode0248_1 = new Leetcode0248_1();
        System.out.println(leetcode0248_1.strobogrammaticInRange("50", "100"));
    }
}
