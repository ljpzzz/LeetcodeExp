package Leetcode1591;

import java.util.*;

public class Leetcode1591_1 {
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length;
        int n = targetGrid[0].length;
        int[][] sec = new int[61][4]; //每个数字的最小x,最大x, 最小y,最大y
        for(int i = 0; i < 61; i++) {
            sec[i][0] = m;
            sec[i][1] = -1;
            sec[i][2] = n;
            sec[i][3] = -1;
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int num = targetGrid[i][j];
                sec[num][0] = Math.min(sec[num][0], i);
                sec[num][1] = Math.max(sec[num][1], i);
                sec[num][2] = Math.min(sec[num][2], j);
                sec[num][3] = Math.max(sec[num][3], j);
            }
        }
        Set<Integer>[] g = new HashSet[61];
        int[] degrees = new int[61];
        for(int i = 0; i < 61; i++) g[i] = new HashSet<>();
        for(int val = 0; val <= 60; val++) {
            if(sec[val][0] == m) continue;
            for (int i = sec[val][0]; i <= sec[val][1]; i++) {
                for (int j = sec[val][2]; j <= sec[val][3]; j++) {
                    if(targetGrid[i][j] != val && !g[targetGrid[i][j]].contains(val)) {
                        g[targetGrid[i][j]].add(val);
                        degrees[val]++;
                    }
                }
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        int visCnt = 0;
        for(int i = 0; i <= 60; i++) {
            if(degrees[i] == 0) {
                q.add(i);
                visCnt++;
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next : g[cur]) {
                degrees[next]--;
                if(degrees[next] == 0) {
                    q.add(next);
                    visCnt++;
                }
            }
        }
        return visCnt == 61;
    }
    public static void main(String[] args) {
        int[][] targetGrid = {{1,1,1},{3,1,3}};
        Leetcode1591_1 l = new Leetcode1591_1();
        System.out.println(l.isPrintable(targetGrid));
    }
}
