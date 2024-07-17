package Leetcode0745;

import java.util.*;

public class Leetcode0745_1 {
    DictTree rootPre;
    DictTree rootSuff;
    public Leetcode0745_1(String[] words) {
        rootPre = new DictTree();
        rootSuff = new DictTree();
        for(int i = 0; i < words.length; i++) {
            rootPre.add(words[i], i);
            rootSuff.add(new StringBuilder(words[i]).reverse().toString(), i);
        }
    }

    public int f(String pref, String suff) {
        List<Integer> prefList = rootPre.search(pref);
        List<Integer> suffList = rootSuff.search(new StringBuilder(suff).reverse().toString());
        int p1 = prefList.size() - 1;
        int p2 = suffList.size() - 1;
        while(p1 >= 0 && p2 >= 0){
            if (prefList.get(p1).equals(suffList.get(p2))) return prefList.get(p1);
            else if (prefList.get(p1) > suffList.get(p2)) p1--;
            else p2--;
        }
        return -1;
    }
    public class DictTree {
        public boolean isLeaf = false;
        public List<Integer> strIndex = new ArrayList<>();
        public DictTree[] children = new DictTree[26];

        public void add(String word, int inx) {
            DictTree cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new DictTree();
                cur = cur.children[c - 'a'];
                cur.strIndex.add(inx);
            }
            cur.isLeaf = true;
        }

        public List<Integer> search(String word) {
            DictTree cur = this;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) return new ArrayList<>();
                cur = cur.children[c - 'a'];
            }
            return cur.strIndex;
        }
    }
    public static void main(String[] args) {
        String[] words = {"apple"};
        Leetcode0745_1 leetcode0745_1 = new Leetcode0745_1(words);
        System.out.println(leetcode0745_1.f("a","e"));
    }
}
