package Leetcode0773;

import java.util.*;

public class Leetcode0773_1 {
    int[][] g = {{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{2,4}};
    public int slidingPuzzle(int[][] board) {
        String init = "" + board[0][0] + board[0][1] + board[0][2] + board[1][0] + board[1][1] + board[1][2];
        Set<String> vis = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        int step = 0;
        q.add(init);
        vis.add(init);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals("123450")) {
                    return step;
                }
                int pos  = cur.indexOf('0');
                for(int exchange : g[pos]){
                    char[] ss = cur.toCharArray();
                    ss[pos] = ss[exchange];
                    ss[exchange] = '0';
                    String next = new String(ss);
                    if (!vis.contains(next)) {
                        q.add(next);
                        vis.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode0773_1().slidingPuzzle(new int[][]{{1,2,3},{4,0,5}}));
    }
}
