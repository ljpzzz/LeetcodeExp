package Leetcode0880;

import java.util.*;

public class Leetcode0880_1 {
    public String decodeAtIndex(String s, int k) {
        return dfs(s, k);
    }
    public String dfs(String s, long k){
        int n = s.length();
        long cnt = 0;
        for(int i = 0; i < n; i++){
            if(Character.isDigit(s.charAt(i))){
                int val = s.charAt(i) - '0';
                if(cnt*val >= k){
                    long plus = k%cnt;
                    if(plus == 0) plus = cnt;
                    return dfs(s.substring(0, i), plus);
                }
                else cnt *= val;
            }
            else{
                cnt++;
                if(cnt == k) return String.valueOf(s.charAt(i));
            }
        }
        return "";
    }
    public static void main(String[] args) {
        Leetcode0880_1 leetcode0880_1 = new Leetcode0880_1();
        System.out.println(leetcode0880_1.decodeAtIndex("leet2code3", 10));
    }
}
