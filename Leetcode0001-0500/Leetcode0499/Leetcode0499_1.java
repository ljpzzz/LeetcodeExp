package Leetcode0499;

import java.util.*;

public class Leetcode0499_1 {
    int m;
    int n;
    int[][] maze;
    Map<Integer, Integer> dis = new HashMap<>();
    Map<Integer, String> disStr = new HashMap<>();
    String ans = null;
    int ansLength = 0x3f3f3f3f;
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        m = maze.length;
        n = maze[0].length;
        this.maze = maze;
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->(a[2]-b[2]));
        q.add(new int[]{ball[0], ball[1], 0});
        dis.put(ball[0]*n+ball[1], 0);
        disStr.put(ball[0]*n+ball[1], "");
        while(q.size() > 0){
            int[] cur = q.poll();
            int x = cur[0]; int y = cur[1]; int d = cur[2];
            if(dis.get(x*n+y) != null && dis.get(x*n+y) < d) continue;
            //y大于0可以尝试向左
            if(y > 0){
                int y2 = y;
                while(y2 > 0 && maze[x][y2-1] != 1) {
                    y2--;
                    if(x == hole[0] && y2 == hole[1]){
                        if(ans == null || d+y-y2 < ansLength || d+y-y2 == ansLength && ans.compareTo(disStr.get(x*n+y) + "l") > 0){
                            ans = disStr.get(x*n+y) + "l";
                            ansLength = d+y-y2;
                        }
                    }
                }
                if(dis.get(x*n+y2) == null || dis.get(x*n+y2) > d+y-y2 ||
                        (dis.get(x*n+y2) == d+y-y2 && disStr.get(x*n+y2).compareTo(disStr.get(x*n+y) + "l") > 0)){
                    q.add(new int[]{x, y2, d+y-y2});
                    dis.put(x*n+y2, d+y-y2);
                    disStr.put(x*n+y2, disStr.get(x*n+y) + "l");
                }
            }
            if(y < n-1){
                int y2 = y;
                while(y2 < n-1 && maze[x][y2+1] != 1) {
                    y2++;
                    if(x == hole[0] && y2 == hole[1]){
                        if(ans == null || d+y2-y < ansLength || d+y2-y == ansLength && ans.compareTo(disStr.get(x*n+y) + "r") > 0){
                            ans = disStr.get(x*n+y) + "r";
                            ansLength = d+y2-y;
                        }
                    }
                }
                if(dis.get(x*n+y2) == null || dis.get(x*n+y2) > d+y2-y ||
                        (dis.get(x*n+y2) == d+y2-y && disStr.get(x*n+y2).compareTo(disStr.get(x*n+y) + "r") > 0)){
                    q.add(new int[]{x, y2, d+y2-y});
                    dis.put(x*n+y2, d+y2-y);
                    disStr.put(x*n+y2, disStr.get(x*n+y) + "r");
                }
            }
            if(x > 0){
                int x2 = x;
                while(x2 > 0 && maze[x2-1][y] != 1){
                    x2--;
                    if(x2 == hole[0] && y == hole[1]){
                        if(ans == null || d+x-x2 < ansLength || d+x-x2 == ansLength && ans.compareTo(disStr.get(x*n+y) + "u") > 0){
                            ans = disStr.get(x*n+y) + "u";
                            ansLength = d+x-x2;
                        }
                    }
                }
                if(dis.get(x2*n+y) == null || dis.get(x2*n+y) > d+x-x2 ||
                        (dis.get(x2*n+y) == d+x-x2 && disStr.get(x2*n+y).compareTo(disStr.get(x*n+y) + "u") > 0)){
                    q.add(new int[]{x2, y, d+x-x2 });
                    dis.put(x2*n+y, d+x-x2);
                    disStr.put(x2*n+y, disStr.get(x*n+y) + "u");
                }
            }
            if(x < m-1){
                int x2 = x;
                while(x2 < m-1 && maze[x2+1][y] != 1){
                    x2++;
                    if(x2 == hole[0] && y == hole[1]){
                        if(ans == null || d+x2-x < ansLength || d+x2-x == ansLength && ans.compareTo(disStr.get(x*n+y) + "d") > 0){
                            ans = disStr.get(x*n+y) + "d";
                            ansLength = d+x2-x;
                        }
                    }
                }
                if(dis.get(x2*n+y) == null || dis.get(x2*n+y) > d+x2-x ||
                        (dis.get(x2*n+y) == d+x2-x && disStr.get(x2*n+y).compareTo(disStr.get(x*n+y) + "d") > 0)){
                    q.add(new int[]{x2, y, d+x2-x });
                    dis.put(x2*n+y, d+x2-x);
                    disStr.put(x2*n+y, disStr.get(x*n+y) + "d");
                }
            }
        }
        if(ans != null) return ans;
        else return "impossible";
    }
}
