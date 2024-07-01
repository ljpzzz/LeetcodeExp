package Leetcode0140;

import java.util.*;

public class Leetcdoe0140_1 {
    List<Integer>[] g;
    List<String> ans = new ArrayList<>();
    List<String> wordDict;
    //KMP+DFS
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        int n = wordDict.size();
        this.wordDict = wordDict;
        KMP kmp = new KMP();
        g = new ArrayList[len];
        for (int i = 0; i < len; ++i) g[i] = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<Integer> strInxList = kmp.kmp(s, wordDict.get(i));
            for(int inx : strInxList) g[inx].add(i);
        }
        dfs(s, 0, "");
        return ans;
    }
    public void dfs(String s, int index, String curAns){
        if(index == s.length()){
            ans.add(curAns.trim());
            return;
        }
        for(int strInx : g[index]){
            int len = wordDict.get(strInx).length();
            dfs(s, index+len, curAns+" "+wordDict.get(strInx));
        }
    }
    class KMP {
        public int[] getNext2(String pattern) {
            int[] next = new int[pattern.length()];
            for (int i = 1; i < pattern.length(); ++i) {
                int j = next[i - 1];
                while (j != 0 && pattern.charAt(j) != pattern.charAt(i)) {
                    j = next[j - 1];
                }
                if (pattern.charAt(j) == pattern.charAt(i)) {
                    next[i] = j + 1;
                }
            }
            return next;
        }
        public List<Integer> kmp(String str, String pattern) {
            List<Integer> res = new ArrayList<>();
            String cur = pattern + "#" + str;
            int len1 = str.length();
            int len2 = pattern.length();
            int[] next = getNext2(cur);
            for(int i = len2+1; i <= len1+len2; i++){
                if(next[i] == len2) res.add(i-2*len2);
            }
            return res;
        }
    }
    public static void main(String[] args) {
        List<String> ans = new Leetcdoe0140_1().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
        for(String s : ans) System.out.println(s);
    }
}
