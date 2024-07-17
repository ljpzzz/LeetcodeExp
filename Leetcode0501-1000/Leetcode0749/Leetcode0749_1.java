package Leetcode0749;

import java.util.*;

public class Leetcode0749_1 {
    int m;
    int n;
    int code; //感染区域编码
    //key: 感染区域编码 value: 感染位置集合
    Map<Integer, Set<Integer>> mapInfect;
    //key: 感染区域编码 value: 威胁位置集合
    Map<Integer, Set<Integer>> mapDanger ;
    //key: 感染区域编码 value: 威胁位置需要的挡板数量
    Map<Integer, Integer> mapDangerBracket;
    int[][] d;
    boolean[][] vis;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int containVirus(int[][] isInfected) {
        m = isInfected.length;
        n = isInfected[0].length;
        d = isInfected;
        int ans = 0;
        while(true) {
            mapInfect = new HashMap<>();
            mapDanger = new HashMap<>();
            vis = new boolean[m][n];
            mapDangerBracket = new HashMap<>();
            code = 0;
            //1. 分块
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][j] == 1 && !vis[i][j]) {
                        code++;
                        mapInfect.put(code, new HashSet<>());
                        mapDanger.put(code, new HashSet<>());
                        dfs(i, j, code);
                    }
                }
            }
            //2. 寻找最大威胁区域
            int maxDange = 0;
            int maxDangerIndex = -1;
            for (int i = 1; i <= code; i++) {
                if(mapDanger.get(i).size() > maxDange){
                    maxDange = mapDanger.get(i).size();
                    maxDangerIndex = i;
                }
            }
            if(maxDangerIndex == -1) break;
            //3. 安装挡板
            ans += mapDangerBracket.get(maxDangerIndex);
            for (int x : mapInfect.get(maxDangerIndex)) {
                int i = x / n;
                int j = x % n;
                d[i][j] = 2;
            }
            //4. 感染新区域
            for (int cc : mapDanger.keySet()) {
                if(cc == maxDangerIndex) continue;
                for(int x : mapDanger.get(cc)){
                    int i = x / n;
                    int j = x % n;
                    d[i][j] = 1;
                }
            }
            /*
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(d[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("==========");
            */
        }
        return ans;
    }
    public void dfs(int i, int j, int code){
        if(i < 0 || i >= m || j < 0 || j >= n || vis[i][j] || d[i][j] == 2) return;
        if(d[i][j] == 1){
            vis[i][j] = true;
            mapInfect.get(code).add(i * n + j);
            for(int[] dir : dirs){
                int x = i + dir[0];
                int y = j + dir[1];
                dfs(x, y, code);
            }
        }
        else if(d[i][j] == 0){
            mapDangerBracket.put(code, mapDangerBracket.getOrDefault(code, 0) + 1);
            mapDanger.get(code).add(i * n + j);
        }
    }
    public static void main(String[] args) {
        int[][] isInfected = {{0,1,0,0,0,0,0,1},{0,1,0,0,0,0,0,1},{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0}};
        Leetcode0749_1 l = new Leetcode0749_1();
        System.out.println(l.containVirus(isInfected));
    }
}
