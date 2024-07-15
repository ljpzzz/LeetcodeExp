package Leetcode0642;


import java.util.*;

class Leetcode0642_1 {
    DictTree root = new DictTree();
    DictTree preRoot;
    Map<String, Integer> cntMap = new HashMap<>();
    String preStr;
    public Leetcode0642_1(String[] sentences, int[] times) {
        int n = sentences.length;
        preStr = "";
        for(int i = 0; i < n; i++) {
            root.add(sentences[i], times[i]);
            cntMap.put(sentences[i], times[i]);
        }
        preRoot = root;
    }

    public List<String> input(char c) {
        List<String> ans = new ArrayList<>();
        if(c == '#'){
            root = preRoot;
            int cnt = cntMap.getOrDefault(preStr, 0);
            root.add(preStr, cnt+1);
            cntMap.put(preStr, cnt+1);
            preStr = "";
            return ans;
        }
        preStr += c;
        if(root == null) return ans;
        if(c == ' ') root = root.children[26];
        else root = root.children[c-'a'];
        if(root == null) return ans;
        DictTree cur = root;
        dfs(cur, ans);
        Collections.sort(ans, (a, b)->{
            int cntA = cntMap.get(a);
            int cntB = cntMap.get(b);
            if(cntA != cntB) return cntB-cntA;
            else return a.compareTo(b);
        });
        if(ans.size() < 3) return ans;
        else return ans.subList(0,3);
    }
    public void dfs(DictTree cur, List<String> ans){
        if(cur == null) return;
        if(cur.isLeaf) ans.add(cur.str);
        for(int i = 0; i < 27; i++) dfs(cur.children[i], ans);
    }
    class DictTree {
        public boolean isLeaf = false;
        public String str = null;
        public int count = 0;
        public DictTree[] children = new DictTree[27];

        public void add(String word, int times) {
            DictTree cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(c == ' '){
                    if (cur.children[26] == null) cur.children[26] = new DictTree();
                    cur = cur.children[26];
                }
                else {
                    if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new DictTree();
                    cur = cur.children[c - 'a'];
                }
            }
            cur.isLeaf = true;
            cur.count = times;
            cur.str = word;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
