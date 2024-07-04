package Leetcode0391;

import java.util.*;

public class Leetcode0391_1 {
    public boolean isRectangleCover(int[][] rectangles) {
        //key : x, value:  y_down, y_up, 矩形左半边map
        TreeMap<Integer, List<int[]>> mapStart = new TreeMap<>();
        //key : x, value:  y_down, y_up, 矩形右半边map
        TreeMap<Integer, List<int[]>> mapEnd = new TreeMap<>();
        Set<Integer> yPos = new HashSet<>();
        for(int[] rec : rectangles){
            mapStart.computeIfAbsent(rec[0], k -> new ArrayList<>()).add(new int[]{rec[1], rec[3]});
            mapEnd.computeIfAbsent(rec[2], k -> new ArrayList<>()).add(new int[]{rec[1], rec[3]});
            yPos.add(rec[1]);
            yPos.add(rec[3]);
        }
        int n = yPos.size();
        List<Integer> yPosList = new ArrayList<>(yPos);
        Collections.sort(yPosList);
        Map<Integer, Integer> posInxMap = new HashMap<>();
        for(int i = 0; i < yPosList.size(); i++) posInxMap.put(yPosList.get(i), i);
        int[] delta = new int[n]; //差分数组
        delta[0]++;
        delta[n-1]--;
        int minY = yPosList.get(0);
        int maxY = yPosList.get(n - 1);

        //检查最左边
        int xStart = mapStart.firstKey();
        List<int[]> yList = mapStart.get(xStart);
        Collections.sort(yList, (a, b) -> a[0] - b[0]);
        if(yList.get(0)[0] != minY || yList.get(yList.size() - 1)[1] != maxY) return false;
        for(int i = 1; i < yList.size(); i++){
            if(yList.get(i)[0] != yList.get(i - 1)[1]) return false;
        }

        //检查最右边
        int xEnd = mapEnd.lastKey();
        yList = mapEnd.get(xEnd);
        Collections.sort(yList, (a, b) -> a[0] - b[0]);
        if(yList.get(0)[0] != minY || yList.get(yList.size() - 1)[1] != maxY) return false;
        for(int i = 1; i < yList.size(); i++){
            if(yList.get(i)[0] != yList.get(i - 1)[1]) return false;
        }
        //检查中间所有的点，差分数组计数都是1
        for(int x : mapStart.keySet()){
            if(x == xStart || x == xEnd) continue;
            List<int[]> yListStart = mapStart.get(x) == null ? new ArrayList<>() : mapStart.get(x);
            List<int[]> yListEnd = mapEnd.get(x) == null ? new ArrayList<>() : mapEnd.get(x);
            for(int[] seg : yListStart){
                int inx1 = posInxMap.get(seg[0]);
                int inx2 = posInxMap.get(seg[1]);
                delta[inx1]++;
                delta[inx2]--;
            }
            for(int[] seg : yListEnd){
                int inx1 = posInxMap.get(seg[0]);
                int inx2 = posInxMap.get(seg[1]);
                delta[inx1]--;
                delta[inx2]++;
            }
            int dd = 0;
            for(int i = 0; i < n-1; i++){
                dd += delta[i];
                if(dd != 1) return false;
            }
            dd += delta[n-1];
            if(dd != 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int[][] rec = {{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}};
        Leetcode0391_1 leetcode0391_1 = new Leetcode0391_1();
        boolean res = leetcode0391_1.isRectangleCover(rec);
        System.out.println(res);
    }
}
