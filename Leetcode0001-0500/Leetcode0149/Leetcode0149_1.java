package Leetcode0149;

import java.util.*;

public class Leetcode0149_1 {
    int INF = (int)1e5;
    public int maxPoints(int[][] points) {
        int n = points.length;
        if(n <= 2) return n;
        Map<String, Integer> kMap = new HashMap<>();
        int ans = 0;
        for(int i = 0; i < n; i++) {
            kMap.clear();
            for(int j = i + 1; j < n; j++) {
                int[] k = getK(points[i][0], points[i][1], points[j][0], points[j][1]);
                String hash = k[0] + "#" + k[1];
                kMap.put(hash, kMap.getOrDefault(hash, 0) + 1);
                ans = Math.max(ans, kMap.get(hash) + 1);
            }
        }
        return ans;
    }
    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
    public int[] getK(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        if(dx == 0) return new int[]{1, INF};
        if(dy == 0) return new int[]{0, 1};
        int common =  gcd(Math.abs(dx), Math.abs(dy));
        dx /= common;
        dy /= common;
        if(dx < 0){
            dx = -dx;
            dy = -dy;
        }
        return new int[]{dx, dy};
    }
    public static void main(String[] args){
        System.out.println(new Leetcode0149_1().maxPoints(new int[][]{{1,1},{2,2},{3,3}}));
    }
}
