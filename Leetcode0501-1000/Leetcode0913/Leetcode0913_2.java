package Leetcode0913;

import java.util.*;

public class Leetcode0913_2 {
    //拓部排序版本
    static final int MOUSE_TURN = 0, CAT_TURN = 1;
    static final int DRAW = 0, MOUSE_WIN = 1, CAT_WIN = 2;
    int n;
    int[][] g;
    int[][][] degrees;
    int[][][] res;
    public int catMouseGame(int[][] graph) {
        n = graph.length;
        g = graph;
        //degress[i][j][0]表示老鼠在位置i, 猫在位置j, 当前老鼠行动时的入度
        //degress[i][j][1]表示老鼠在位置i, 猫在位置j, 当前猫行动时的入度
        degrees = new int[n][n][2];
        //res[i][j][0]表示老鼠在位置i, 猫在位置j, 当前老鼠行动时的最后结果
        //res[i][j][1]表示老鼠在位置i, 猫在位置j, 当前猫行动时的最后结果
        res = new int[n][n][2];
        //创建BFS队列, int[]有三个值，第一个是老鼠位置，第二个是猫位置，第三个是当前行动者：0(老鼠)， 1（猫）
        Queue<int[]> queue = new ArrayDeque<>();
        //初始化入度数组degrees
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                degrees[i][j][MOUSE_TURN] = g[i].length;
                degrees[i][j][CAT_TURN] = g[j].length;
            }
        }
        //特殊处理和位置0相邻的点的猫的入度，因为猫不能到位置0
        for(int node : g[0]){
            for(int i = 0; i < n; i++) degrees[i][node][CAT_TURN]--;
        }
        //初始化BFS队列
        //将老鼠可以赢的状态加入BFS队列
        for(int j = 0; j < n; j++){
            res[0][j][MOUSE_TURN] = MOUSE_WIN;
            res[0][j][CAT_TURN] = MOUSE_WIN;
            queue.add(new int[]{0, j, MOUSE_TURN});
            queue.add(new int[]{0, j, CAT_TURN});
        }
        //将猫可以赢的状态加入BFS队列
        for(int i = 1; i < n; i++){
            res[i][i][MOUSE_TURN] = CAT_WIN;
            res[i][i][CAT_TURN] = CAT_WIN;
            queue.add(new int[]{i, i, MOUSE_TURN});
            queue.add(new int[]{i, i, CAT_TURN});
        }

        //开始BFS+拓补排序
        while(queue.size() > 0){
            int[] cur = queue.poll();
            int mousePos = cur[0];
            int catPos = cur[1];
            int turn = cur[2];
            int result = res[mousePos][catPos][turn];
            List<int[]> prevStates = getPrevStates(mousePos, catPos, turn);
            for(int[] prevState :prevStates){
                int prevMousePos = prevState[0];
                int prevCatPos = prevState[1];
                int prevTurn = prevState[2];
                //已经分出胜负的上一个状态不需要继续BFS
                if(res[prevMousePos][prevCatPos][prevTurn] != DRAW) continue;
                boolean canWin = (result == CAT_WIN && prevTurn == CAT_TURN) || (result == MOUSE_WIN && prevTurn == MOUSE_TURN);
                if(canWin){
                    res[prevMousePos][prevCatPos][prevTurn] = result;
                    queue.add(new int[]{prevMousePos, prevCatPos, prevTurn});
                }
                else{
                    //无法确定胜负，则当前入度减1
                    degrees[prevMousePos][prevCatPos][prevTurn]--;
                    if(degrees[prevMousePos][prevCatPos][prevTurn] == 0){
                        int lossResult = (prevTurn==MOUSE_TURN)?CAT_WIN:MOUSE_WIN;
                        res[prevMousePos][prevCatPos][prevTurn] = lossResult;
                        queue.add(new int[]{prevMousePos, prevCatPos, prevTurn});
                    }
                }
            }
        }
        return res[1][2][MOUSE_TURN];
    }
    public List<int[]> getPrevStates(int mouse, int cat, int turn) {
        List<int[]> prevStates = new ArrayList<int[]>();
        int prevTurn = turn == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
        if (prevTurn == MOUSE_TURN) {
            for (int prev : g[mouse]) {
                prevStates.add(new int[]{prev, cat, prevTurn});
            }
        } else {
            for (int prev : g[cat]) {
                if (prev != 0) {
                    prevStates.add(new int[]{mouse, prev, prevTurn});
                }
            }
        }
        return prevStates;
    }
}
