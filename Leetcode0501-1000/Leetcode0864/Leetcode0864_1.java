package Leetcode0864;

import java.util.*;

public class Leetcode0864_1 {
    public int shortestPathAllKeys(String[] grid) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = grid.length;
        int n = grid[0].length();
        int startX = -1;
        int startY = -1;
        int keyCnt = 0;
        Set<Character> keySet = new HashSet<>();
        char[][] d = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = grid[i].charAt(j);
                if (d[i][j] >= 'a' && d[i][j] <= 'f') {
                    keyCnt++;
                    keySet.add(d[i][j]);
                }
                else if(d[i][j] == '@'){
                    startX = i;
                    startY = j;
                }
            }
        }
        List<Character> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        Map<Character, Integer> keyCode = new HashMap<>();
        for (int i = 0; i < keyCnt; i++) keyCode.put(keyList.get(i), i);
        //distance[i][j][mask]表示从位置[startX, startY]到[i, j]位置，钥匙状态为mask所花费的最短距离
        int[][][] distance = new int[m][n][1<<keyCnt];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) Arrays.fill(distance[i][j], 0x3f3f3f3f);
        }
        distance[startX][startY][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int mask = cur[2];
            for(int[] dir : dirs){
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n || d[nx][ny] == '#') continue;
                //遇到空房间，或者起点
                if(d[nx][ny] == '.' || d[nx][ny] == '@'){
                    if(distance[nx][ny][mask] > distance[x][y][mask] + 1){
                        distance[nx][ny][mask] = distance[x][y][mask] + 1;
                        queue.offer(new int[]{nx, ny, mask});
                    }
                }
                //遇到钥匙
                else if(d[nx][ny] >= 'a' && d[nx][ny] <= 'f'){
                    int pos = keyCode.get(d[nx][ny]);
                    int newMask = mask | (1<<pos);
                    if(distance[nx][ny][newMask] > distance[x][y][mask] + 1){
                        distance[nx][ny][newMask] = distance[x][y][mask] + 1;
                        queue.offer(new int[]{nx, ny, newMask});
                    }
                    //钥匙已经找齐了
                    if(newMask == (1<<keyCnt)-1) return distance[nx][ny][newMask];
                }
                //遇到锁
                else if(d[nx][ny] >= 'A' && d[nx][ny] <= 'F'){
                    int pos = keyCode.get(Character.toLowerCase(d[nx][ny]));
                    if((mask & (1<<pos)) == 0) continue;
                    if(distance[nx][ny][mask] > distance[x][y][mask] + 1){
                        distance[nx][ny][mask] = distance[x][y][mask] + 1;
                        queue.offer(new int[]{nx, ny, mask});
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String[] grid = {"@.a.#","###.#","b.A.B"};
        Leetcode0864_1 leetcode0864_1 = new Leetcode0864_1();
        System.out.println(leetcode0864_1.shortestPathAllKeys(grid));
    }
}
