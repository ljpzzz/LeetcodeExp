package Leetcode1036;

import java.util.*;

public class Leetcode1036_2 {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        TreeSet<Integer> xSet = new TreeSet<>();
        TreeSet<Integer> ySet = new TreeSet<>();
        for(int[] b : blocked) {
            xSet.add(b[0]);
            ySet.add(b[1]);
        }
        xSet.add(source[0]);
        ySet.add(source[1]);
        xSet.add(target[0]);
        ySet.add(target[1]);
        int xCnt = 0;
        Map<Integer, Integer> xMap = new HashMap<>();
        int preV = -1;
        if(xSet.first() > 0) {
            xMap.put(0, xCnt++);
            preV = 0;
        }
        for(int xVal : xSet){
            if(xVal != preV+1) xCnt++;
            xMap.put(xVal, xCnt++);
            preV = xVal;
        }
        if(xSet.last() < (int)1e6-1) xCnt++;
        int yCnt = 0;
        Map<Integer, Integer> yMap = new HashMap<>();
        if(ySet.first() > 0) yMap.put(0, yCnt++);
        preV = -1;
        if(ySet.first() > 0) {
            yMap.put(0, yCnt++);
            preV = 0;
        }
        for(int yVal : ySet){
            if(yVal != preV+1) yCnt++;
            yMap.put(yVal, yCnt++);
            preV = yVal;
        }
        if(ySet.last() < (int)1e6-1) yCnt++;
        Set<Integer> bk = new HashSet<>();
        for(int[] b : blocked){
            int x = xMap.get(b[0]);
            int y = yMap.get(b[1]);
            bk.add(x*xCnt+y);
        }
        int sx = xMap.get(source[0]);
        int sy = yMap.get(source[1]);
        int tx = xMap.get(target[0]);
        int ty = yMap.get(target[1]);

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sx, sy});
        boolean[][] vis = new boolean[xCnt][yCnt];
        vis[sx][sy] = true;

        while(queue.size() > 0){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if(x == tx && y == ty) return true;
            for(int[] dir : dirs){
                int x2 = x+dir[0];
                int y2 = y+dir[1];
                if(x2 < 0 || x2 >= xCnt || y2 < 0 || y2 >= yCnt || vis[x2][y2]) continue;
                if(bk.contains(x2*xCnt+y2)) continue;
                queue.add(new int[]{x2, y2});
                vis[x2][y2] = true;
            }
        }
        return false;
    }
}
