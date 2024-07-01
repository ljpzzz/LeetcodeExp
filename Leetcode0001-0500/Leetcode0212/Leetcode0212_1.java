package Leetcode0212;

import java.util.*;

public class Leetcode0212_1 {
    int m;
    int n;
    DictTree root = new DictTree();
    Set<String> ans = new HashSet<>();
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<String> findWords(char[][] board, String[] words) {
        root = new DictTree();
        for (int i = 0; i < words.length; i++) {
            root.add(words[i]);
        }
        m = board.length;
        n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dfs(board, i, j, new boolean[m][n], root);
            }
        }
        return new ArrayList<>(ans);
    }
    public void dfs(char[][] board, int x, int y, boolean[][] vis, DictTree current){
        if(x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) return;
        char c = board[x][y];
        DictTree next = current.children[c - 'a'];
        if(next == null) return;
        if(next.isLeaf) {
            ans.add(next.str);
        }
        vis[x][y] = true;
        for(int[] dir : dirs){
            int x2 = x + dir[0];
            int y2 = y + dir[1];
            dfs(board, x2, y2, vis, next);
        }
        vis[x][y] = false;
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
        char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[]{"oath","pea","eat","rain"};
        Leetcode0212_1 leetcode0212_1 = new Leetcode0212_1();
        List<String> ans = leetcode0212_1.findWords(board, words);
        for(String s : ans){
            System.out.println(s);
        }
    }
}
