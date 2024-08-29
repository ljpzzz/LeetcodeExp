package Leetcode0909;

import java.util.*;

public class Leetcode0909_1 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Map<Integer, int[]> codeMap = new HashMap<>();
        boolean fromLeft = true;
        int codeVal = 1;
        for(int i = n-1; i >= 0; i--){
            if(fromLeft){
                for(int j = 0; j < n; j++) codeMap.put(codeVal++, new int[] {i,j});
                fromLeft = !fromLeft;
            }
            else {
                for(int j = n-1; j >= 0; j--) codeMap.put(codeVal++, new int[] {i,j});
                fromLeft = !fromLeft;
            }
        }
        int[] distance = new int[n*n+1];
        Arrays.fill(distance,0x3f3f3f3f);
        distance[1] = 0;
        //int[] 包括code，distance
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {1,0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int code = cur[0];
            int distanceCur = cur[1];
            if(distanceCur > distance[code]) continue;
            if(code == n*n) return distanceCur;
            for(int i = 1; i <= 6 ; i++){
                int nextCode = code + i;
                if(nextCode > n*n) break;
                int[] nextPos = codeMap.get(nextCode);
                if(board[nextPos[0]][nextPos[1]] != -1) nextCode = board[nextPos[0]][nextPos[1]];

                if(distance[nextCode] > distanceCur+1){
                    distance[nextCode] = distanceCur+1;
                    q.add(new int[] {nextCode,distanceCur+1});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] board = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},
                         {-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        Leetcode0909_1 leetcode0909_1 = new Leetcode0909_1();
        System.out.println(leetcode0909_1.snakesAndLadders(board));
    }
}
