package Leetcode1001;

import java.util.*;

public class Leetcode1001_1 {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> cntRow = new HashMap<>();
        Map<Integer, Integer> cntCol = new HashMap<>();
        Map<Integer, Integer> diagMap1 = new HashMap<>();
        Map<Integer, Integer> diagMap2 = new HashMap<>();
        Set<String> lights = new HashSet<>();
        for(int[] lamp: lamps){
            int x = lamp[0];
            int y = lamp[1];
            if(lights.contains(x+"#" +y)) continue;
            lights.add(x+"#" +y);
            cntRow.put(x, cntRow.getOrDefault(x, 0)+1);
            cntCol.put(y, cntCol.getOrDefault(y, 0)+1);
            diagMap1.put(x-y, diagMap1.getOrDefault(x-y, 0)+1);
            diagMap2.put(x+y, diagMap2.getOrDefault(x+y, 0)+1);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for(int i = 0; i < m; i++){
            int x = queries[i][0];
            int y = queries[i][1];
            if(cntRow.getOrDefault(x, 0) > 0 || cntCol.getOrDefault(y, 0) > 0 || diagMap1.getOrDefault(x-y, 0) > 0 || diagMap2.getOrDefault(x+y, 0) > 0){
                ans[i] = 1;
            }
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1},{0, 0}};
            for(int[] dir : dirs){
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(lights.contains(nx+"#" +ny)){
                    lights.remove(nx+"#" +ny);
                    cntRow.put(nx, cntRow.get(nx)-1);
                    cntCol.put(ny, cntCol.get(ny)-1);
                    diagMap1.put(nx-ny, diagMap1.getOrDefault(nx-ny, 0)-1);
                    diagMap2.put(nx+ny, diagMap2.getOrDefault(nx+ny, 0)-1);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[][] lamps = {{2,5},{4,2},{0,3},{0,5},{1,4},{4,2},{3,3},{1,0}};
        int[][] queries = {{4,3},{3,1}};
        Leetcode1001_1 test = new Leetcode1001_1();
        int[] ans = test.gridIllumination(6, lamps, queries);
        for(int i : ans){
            System.out.print(i + " ");
        }
    }
}
