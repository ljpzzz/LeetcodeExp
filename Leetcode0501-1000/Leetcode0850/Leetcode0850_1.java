package Leetcode0850;

import java.util.*;

public class Leetcode0850_1 {
    public int rectangleArea(int[][] rectangles) {
        int MOD = (int) 1e9 + 7;
        Set<Integer> yPos = new HashSet<>();
        Set<Integer> xPos = new HashSet<>();
        for(int[] rec : rectangles){
            yPos.add(rec[1]);
            yPos.add(rec[3]);
            xPos.add(rec[0]);
            xPos.add(rec[2]);
        }
        List<Integer> yList = new ArrayList<>(yPos);
        Collections.sort(yList);
        Map<Integer, Integer> yCodeMap = new HashMap<>();
        int n = 0;
        for(int y : yList) yCodeMap.put(y, n++);

        List<Integer> xList = new ArrayList<>(xPos);
        Collections.sort(xList);
        Map<Integer, Integer> xCodeMap = new HashMap<>();
        int m = 0;
        for(int x : xList) xCodeMap.put(x, m++);

        int[][] delta = new int[m][n];
        for(int i = 0; i < rectangles.length; i++){
            int x1 = xCodeMap.get(rectangles[i][0]);
            int x2 = xCodeMap.get(rectangles[i][2]);
            int y1 = yCodeMap.get(rectangles[i][1]);
            int y2 = yCodeMap.get(rectangles[i][3]);
            delta[x1][y1]++;
            delta[x1][y2]--;
            delta[x2][y1]--;
            delta[x2][y2]++;
        }

        long ans = 0;
        int[][] dSum = new int[m+1][n+1];
        for(int i = 0; i < m-1; i++){
            for(int j = 0; j < n-1; j++){
                dSum[i+1][j+1] = dSum[i][j+1] + dSum[i+1][j] - dSum[i][j] + delta[i][j];
                if(dSum[i+1][j+1] == 0) continue;
                long curAns = 1L* (xList.get(i+1) - xList.get(i)) * (yList.get(j+1) - yList.get(j));
                ans = (ans + curAns)%MOD;
            }
        }
        return (int) ans;
    }
    public static void main(String[] args)
    {
        int[][] rectangles = {{0,0,2,2},{1,0,2,3},{1,0,3,1}};
        Leetcode0850_1 test = new Leetcode0850_1();
        int ans = test.rectangleArea(rectangles);
        System.out.println(ans);
    }
}
