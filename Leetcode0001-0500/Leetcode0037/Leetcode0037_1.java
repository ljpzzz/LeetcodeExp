package Leetcode0037;

import java.util.*;

public class Leetcode0037_1 {
    int[] rowMask = new int[9];
    int[] colMask = new int[9];
    int[][] blockMask = new int[3][3];
    List<int[]> pos = new ArrayList<>();
    boolean finish  = false;
    public void solveSudoku(char[][] board) {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '0'-1;
                    rowMask[i] |= 1 << num;
                    colMask[j] |= 1 << num;
                    blockMask[i / 3][j / 3] |= 1 << num;
                }
                else pos.add(new int[]{i, j});
            }
        }
        solve(board, 0);
    }
    public void solve(char[][] board, int posIndex){
        if(finish) return;
        if(posIndex == pos.size()) {
            finish = true;
            return;
        }
        int x = pos.get(posIndex)[0];
        int y = pos.get(posIndex)[1];
        for(int num = 0; num < 9; num++){
            if((rowMask[x] & (1 << num)) == 0 && (colMask[y] & (1 << num)) == 0 && (blockMask[x / 3][y / 3] & (1 << num)) == 0){
                board[x][y] = (char)(num + '1');
                rowMask[x] |= (1<<num);
                colMask[y] |= (1<<num);
                blockMask[x / 3][y / 3] |= (1<<num);
                solve(board, posIndex + 1);
                if(finish) return;
                board[x][y] = '.';
                rowMask[x] ^= (1<<num);
                colMask[y] ^= (1<<num);
                blockMask[x / 3][y / 3] ^= (1<<num);
            }
        }
    }
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
            };
        new Leetcode0037_1().solveSudoku(board);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
