package Leetcode0711;

import java.util.*;


public class Leetcode0711_1 {
    boolean[][] vis;
    int m;
    int n;
    int[][] grid;
    Set<String> ans = new HashSet<>();
    List<int[]> curAns = new ArrayList<>();
    Comparator<int[]> comp = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        }
    };

    public int numDistinctIslands2(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        vis = new boolean[m][n];
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || vis[i][j]) continue;
                curAns = new ArrayList<>();
                dfs(i, j);
                String code = encode();
                ans.add(code);
            }
        }
        return ans.size();
    }

    public void dfs(int x, int y) {
        vis[x][y] = true;
        curAns.add(new int[]{x, y});
        if (x - 1 >= 0 && grid[x - 1][y] == 1 && !vis[x - 1][y]) dfs(x - 1, y);
        if (x + 1 < m && grid[x + 1][y] == 1 && !vis[x + 1][y]) dfs(x + 1, y);
        if (y - 1 >= 0 && grid[x][y - 1] == 1 && !vis[x][y - 1]) dfs(x, y - 1);
        if (y + 1 < n && grid[x][y + 1] == 1 && !vis[x][y + 1]) dfs(x, y + 1);
    }

    public String encode() {
        int minX = curAns.get(0)[0];
        int minY = curAns.get(0)[1];
        for (int[] tmp : curAns) {
            minX = Math.min(minX, tmp[0]);
            minY = Math.min(minY, tmp[1]);
        }
        //(x,y)
        List<int[]> ans1 = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        for (int[] tmp : curAns) {
            ans1.add(new int[]{tmp[0] - minX, tmp[1] - minY});
            maxX = Math.max(maxX, tmp[0] - minX);
            maxY = Math.max(maxY, tmp[1] - minY);
        }
        Collections.sort(ans1, this.comp);
        String key = "";
        for (int[] tmp : ans1) key += tmp[0] + "," + tmp[1] + "#";
        //(x, -y)
        List<int[]> ans2 = new ArrayList<>();
        for (int[] tmp : ans1) ans2.add(new int[]{tmp[0], maxY - tmp[1]});
        Collections.sort(ans2, this.comp);
        String key2 = "";
        for (int[] tmp : ans2) key2 += tmp[0] + "," + tmp[1] + "#";
        if (key2.compareTo(key) < 0) key = key2;
        //(-x,y)
        ans2 = new ArrayList<>();
        for (int[] tmp : ans1) ans2.add(new int[]{maxX - tmp[0], tmp[1]});
        Collections.sort(ans2, this.comp);
        key2 = "";
        for (int[] tmp : ans2) key2 += tmp[0] + "," + tmp[1] + "#";
        if (key2.compareTo(key) < 0) key = key2;
        //(-x, -y)
        ans2 = new ArrayList<>();
        for (int[] tmp : ans1) ans2.add(new int[]{maxX - tmp[0], maxY - tmp[1]});
        Collections.sort(ans2, this.comp);
        key2 = "";
        for (int[] tmp : ans2) key2 += tmp[0] + "," + tmp[1] + "#";
        if (key2.compareTo(key) < 0) key = key2;

        //(y, x)
        ans2 = new ArrayList<>();
        for (int[] tmp : ans1) ans2.add(new int[]{tmp[1], tmp[0]});
        Collections.sort(ans2, this.comp);
        key2 = "";
        for (int[] tmp : ans2) key2 += tmp[0] + "," + tmp[1] + "#";
        if (key2.compareTo(key) < 0) key = key2;

        //(y, -x)
        ans2 = new ArrayList<>();
        for (int[] tmp : ans1) ans2.add(new int[]{tmp[1], maxX - tmp[0]});
        Collections.sort(ans2, this.comp);
        key2 = "";
        for (int[] tmp : ans2) key2 += tmp[0] + "," + tmp[1] + "#";
        if (key2.compareTo(key) < 0) key = key2;

        //(-y, x)
        ans2 = new ArrayList<>();
        for (int[] tmp : ans1) ans2.add(new int[]{maxY - tmp[1], tmp[0]});
        Collections.sort(ans2, this.comp);
        key2 = "";
        for (int[] tmp : ans2) key2 += tmp[0] + "," + tmp[1] + "#";
        if (key2.compareTo(key) < 0) key = key2;

        //(-y, -x)
        ans2 = new ArrayList<>();
        for (int[] tmp : ans1) ans2.add(new int[]{maxY - tmp[1], maxX - tmp[0]});
        Collections.sort(ans2, this.comp);
        key2 = "";
        for (int[] tmp : ans2) key2 += tmp[0] + "," + tmp[1] + "#";
        if (key2.compareTo(key) < 0) key = key2;

        return key;
    }

    public static void main(String args[]) {
        System.out.println(new Leetcode0711_1().numDistinctIslands2(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1}
        }));
        System.out.println(new Leetcode0711_1().numDistinctIslands2(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 1, 0, 1},
                {0, 1, 0, 1, 1}
        }));
        System.out.println(new Leetcode0711_1().numDistinctIslands2(new int[][]{
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 0}
        }));
    }
}
