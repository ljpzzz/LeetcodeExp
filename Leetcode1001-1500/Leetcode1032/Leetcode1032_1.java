package Leetcode1032;

public class Leetcode1032_1 {
    DictTree root = new DictTree();
    StringBuilder sb = new StringBuilder();
    public Leetcode1032_1(String[] words) {
        for(String w : words) root.add(w);
    }

    public boolean query(char letter) {
        sb.append(letter);
        return root.search(sb);
    }
    public class DictTree {
        public boolean isLeaf = false;
        public String str = null;
        public int count = 0;
        public DictTree[] children = new DictTree[26];

        public void add(String word) {
            DictTree cur = this;
            for (int i = word.length()-1; i >= 0; i--) {
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new DictTree();
                cur = cur.children[c - 'a'];
            }
            cur.isLeaf = true;
            cur.count++;
            cur.str = word;
        }

        public boolean search(StringBuilder word) {
            DictTree cur = this;
            for (int i = word.length()-1; i >= 0; i--) {
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) return false;
                cur = cur.children[c - 'a'];
                if(cur.isLeaf) return true;
            }
            return false;
        }
    }
}
