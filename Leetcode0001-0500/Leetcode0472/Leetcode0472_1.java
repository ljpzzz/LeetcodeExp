package Leetcode0472;

import java.util.*;

public class Leetcode0472_1 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        int n = words.length;
        Arrays.sort(words, (a, b)->{
            if(a.length() != b.length()) return a.length() - b.length();
            return a.compareTo(b);
        });
        DictTree dictTree = new DictTree();
        List<String> ans = new ArrayList<>();
        dictTree.add(words[0]);
        for(int i = 1; i < n; i++){
            String s = words[i];
            int m = s.length();
            boolean[] dp = new boolean[m + 1];
            dp[0] = true;
            for(int j = 1; j <= m; j++){
                for(int k = 0; k < j; k++){
                    if(dp[k] && dictTree.search(s.substring(k, j))) {
                        dp[j] = true;
                        break;
                    }
                }
            }
            if(dp[m]) ans.add(s);
            dictTree.add(s);
        }
        return ans;
    }
    class DictTree {
        public boolean isLeaf = false;
        public String str = null;
        public DictTree[] children = new DictTree[26];

        public void add(String word) {
            DictTree cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new DictTree();
                cur = cur.children[c - 'a'];
            }
            cur.isLeaf = true;
            cur.str = word;
        }

        public boolean search(String word) {
            DictTree cur = this;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) return false;
                cur = cur.children[c - 'a'];
            }
            if(cur.isLeaf) return true;
            else return false;
        }
    }
    public static void main(String[] args) {
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        Leetcode0472_1 leetcode0472_1 = new Leetcode0472_1();
        List<String> ans = leetcode0472_1.findAllConcatenatedWordsInADict(words);
        for(String s : ans) System.out.println(s);
    }
}
