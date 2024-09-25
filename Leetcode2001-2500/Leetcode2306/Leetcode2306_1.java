package Leetcode2306;

import java.util.*;

public class Leetcode2306_1 {
    public long distinctNames(String[] ideas) {
        Set<String>[] sets = new Set[26];
        for(int i = 0; i < 26; i++) sets[i] = new HashSet<>();
        for(String idea : ideas){
            int first = idea.charAt(0) - 'a';
            String post = idea.substring(1);
            sets[first].add(post);
        }
        long ans = 0;
        for(int i = 0; i < 26; i++){
            for(int j = i+1; j < 26; j++){
                long common = 0;
                for(String s : sets[i]){
                    if(sets[j].contains(s)) common++;
                }
                ans += (sets[i].size()-common)*(sets[j].size()-common);
            }
        }
        return ans*2;
    }
    public static void main(String[] args) {
        String[] ideas = {"aaa","baa","caa","bbb","cbb","dbb"};
        Leetcode2306_1 leetcode2306_1 = new Leetcode2306_1();
        System.out.println(leetcode2306_1.distinctNames(ideas));
    }
}
