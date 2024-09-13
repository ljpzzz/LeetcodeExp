package Leetcode1728;

import java.util.*;

public class Leetcode1728_1 {
    int m;
    int n;
    int catJump;
    int mouseJump;
    String[] grid;
    int catInit;
    int mouseInit;
    int food;
    int MOUSE_TURN = 0;
    int CAT_TURN = 1;
    int DRAW = 0;
    int MOUSE_WIN = 1;
    int CAT_WIN = 2;
    int[][][] degrees;
    int[][][][] res;
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        m = grid.length;
        n = grid[0].length();
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        this.grid = grid;
        catInit = -1;
        mouseInit = -1;
        food = -1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i].charAt(j) == 'M') mouseInit = i*n+j;
                else if(grid[i].charAt(j) == 'C') catInit = i*n+j;
                else if(grid[i].charAt(j) == 'F') food = i*n+j;
            }
        }
        //res[i][j][0]表示老鼠在位置i, 猫在位置j, 当前老鼠行动时的最后结果和行动次数
        //res[i][j][1]表示老鼠在位置i, 猫在位置j, 当前猫行动时的最后结果和行动次数
        res = new int[m*n][m*n][2][2];
        //degress[i][j][0]表示老鼠在位置i, 猫在位置j, 当前老鼠行动时的入度
        //degress[i][j][1]表示老鼠在位置i, 猫在位置j, 当前猫行动时的入度
        degrees = new int[m*n][m*n][2];
        //创建BFS队列, int[]有三个值，第一个是老鼠位置，第二个是猫位置，第三个是当前行动者：0(老鼠)， 1（猫）
        Queue<int[]> queue = new ArrayDeque<>();

        //计算所有状态的入度
        for(int mouse = 0; mouse < m*n; mouse++){
            int mouseX = mouse/n;
            int mouseY = mouse%n;
            if(grid[mouseX].charAt(mouseY) == '#') continue;
            for(int cat = 0; cat < m*n; cat++){
                int catX = cat/n;
                int catY = cat%n;
                if(grid[catX].charAt(catY) == '#') continue;
                degrees[mouse][cat][MOUSE_TURN]++;
                degrees[mouse][cat][CAT_TURN]++;
                for(int[] dir : dirs){
                    for(int jump = 1; jump <= catJump; jump++){
                        int x = catX+dir[0]*jump;
                        int y = catY+dir[1]*jump;
                        if(x < 0 || x >= m || y < 0 || y >= n) break;
                        if(grid[x].charAt(y) == '#') break;
                        degrees[mouse][x*n+y][CAT_TURN]++;
                    }
                    for(int jump = 1; jump <= mouseJump; jump++){
                        int x = mouseX+dir[0]*jump;
                        int y = mouseY+dir[1]*jump;
                        if(x < 0 || x >= m || y < 0 || y >= n) break;
                        if(grid[x].charAt(y) == '#') break;
                        degrees[x*n+y][cat][MOUSE_TURN]++;
                    }
                }
            }
        }
        //猫和老鼠在一个位置，猫胜
        for(int pos = 0; pos < m*n; pos++){
            int x = pos/n;
            int y = pos%n;
            if(grid[x].charAt(y) == '#') continue;
            res[pos][pos][MOUSE_TURN][0] = CAT_WIN;
            res[pos][pos][MOUSE_TURN][1] = 0;
            res[pos][pos][CAT_TURN][0] = CAT_WIN;
            res[pos][pos][CAT_TURN][1] = 0;
            queue.add(new int[]{pos,pos, MOUSE_TURN});
            queue.add(new int[]{pos,pos, CAT_TURN});
        }

        //猫和食物在一个位置，猫胜
        for(int mouse = 0; mouse < m*n; mouse++){
            int mouseX = mouse/n;
            int mouseY = mouse%n;
            if(grid[mouseX].charAt(mouseY) == '#' || mouse == food) continue;
            res[mouse][food][MOUSE_TURN][0] = CAT_WIN;
            res[mouse][food][MOUSE_TURN][1] = 0;
            res[mouse][food][CAT_TURN][0] = CAT_WIN;
            res[mouse][food][CAT_TURN][1] = 0;
            queue.add(new int[]{mouse, food, MOUSE_TURN});
            queue.add(new int[]{mouse, food, CAT_TURN});
        }

        //老鼠和食物在一个位置，且猫不在食物位置，老鼠胜
        for(int cat = 0; cat < m*n; cat++){
            int catX = cat/n;
            int catY = cat%n;
            if(grid[catX].charAt(catY) == '#' || cat == food) continue;
            res[food][cat][MOUSE_TURN][0] = MOUSE_WIN;
            res[food][cat][MOUSE_TURN][1] = 0;
            res[food][cat][CAT_TURN][0] = MOUSE_WIN;
            res[food][cat][CAT_TURN][1] = 0;
            queue.add(new int[]{food, cat, MOUSE_TURN});
            queue.add(new int[]{food, cat, CAT_TURN});
        }

        //拓补排序
        while(queue.size() > 0){
            int[] cur = queue.poll();
            int mouse = cur[0];
            int cat = cur[1];
            int turn = cur[2];
            int result = res[mouse][cat][turn][0];
            int moves = res[mouse][cat][turn][1];
            List<int[]> prevStates = getPrevStates(mouse, cat, turn);
            for(int[] prevState : prevStates){
                int prevMouse = prevState[0];
                int prevCat = prevState[1];
                int prevTurn = prevState[2];
                if(res[prevMouse][prevCat][prevTurn][0] != DRAW) continue;
                boolean canWin = (result == MOUSE_WIN && prevTurn == MOUSE_TURN) || (result == CAT_WIN && prevTurn == CAT_TURN);
                if(canWin){
                    res[prevMouse][prevCat][prevTurn][0] = result;
                    res[prevMouse][prevCat][prevTurn][1] = moves+1;
                    queue.add(new int[]{prevMouse, prevCat, prevTurn});
                }
                else{
                    degrees[prevMouse][prevCat][prevTurn]--;
                    if(degrees[prevMouse][prevCat][prevTurn] == 0){
                        int loseResult = (prevTurn == MOUSE_TURN)?CAT_WIN:MOUSE_WIN;
                        res[prevMouse][prevCat][prevTurn][0] = loseResult;
                        res[prevMouse][prevCat][prevTurn][1] = moves+1;
                        queue.add(new int[]{prevMouse, prevCat, prevTurn});
                    }
                }
            }
        }
        if(res[mouseInit][catInit][MOUSE_TURN][0] == MOUSE_WIN && res[mouseInit][catInit][MOUSE_TURN][1] <= 1000) return true;
        else return false;

    }
    public List<int[]> getPrevStates(int mouse, int cat, int turn){
        List<int[]> resP = new ArrayList<>();
        int mouseX = mouse/n;
        int mouseY = mouse%n;
        int catX = cat/n;
        int catY = cat%n;
        int prevTurn = (turn == CAT_TURN)?MOUSE_TURN:CAT_TURN;
        int maxJump = (prevTurn == MOUSE_TURN)?mouseJump:catJump;
        int startX = (prevTurn == MOUSE_TURN)?mouseX:catX;
        int startY = (prevTurn == MOUSE_TURN)?mouseY:catY;
        //之前不动也是可以的
        resP.add(new int[]{mouse, cat, prevTurn});
        for(int[] dir : dirs){
            for(int jump = 1; jump <= maxJump; jump++){
                int x = startX+dir[0]*jump;
                int y = startY+dir[1]*jump;
                if(x < 0 || x >= m || y < 0 || y >= n) break;
                if(grid[x].charAt(y) == '#') break;
                int prevMouseX = (prevTurn == MOUSE_TURN)?x:mouseX;
                int prevMouseY = (prevTurn == MOUSE_TURN)?y:mouseY;
                int prevCatX =  (prevTurn == MOUSE_TURN)?catX:x;
                int prevCatY =  (prevTurn == MOUSE_TURN)?catY:y;
                resP.add(new int[]{prevMouseX*n+prevMouseY, prevCatX*n+prevCatY, prevTurn});
            }
        }
        return resP;
    }
}
