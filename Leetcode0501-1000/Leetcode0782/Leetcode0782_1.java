package Leetcode0782;

public class Leetcode0782_1 {
    int n;
    public int movesToChessboard(int[][] board) {
        n = board.length;
        //检查行是不是只有2种行mask, 且1的数量是一半(n偶数)，一半+-1(n为奇数)
        int rowMask = getMask(board, true, 0);
        int rowRevMask = ((1<<n)-1)^rowMask;
        int cnt1 = Integer.bitCount(rowMask);
        if(n%2 == 0 && cnt1 != n/2 || n%2 == 1 && cnt1 != (n+1)/2 && cnt1 != n/2) return -1;
        int rowMaskCnt = 1;
        int rowRevMaskCnt = 0;
        for(int i = 1; i < n; i++){
            int curMask = getMask(board, true, i);
            if(curMask == rowMask) rowMaskCnt++;
            else if(curMask == rowRevMask) rowRevMaskCnt++;
            else return -1;
        }
        //检查2种行mask的数量
        if(n%2 == 0 && rowMaskCnt != n/2 || n%2 == 1 && rowMaskCnt != (n+1)/2 && rowMaskCnt != n/2) return -1;

        //检查列是不是只有2种行mask, 且1的数量是一半(n偶数)，一半+-1(n为奇数)
        int colMask = getMask(board, false, 0);
        int colRevMask = ((1<<n)-1)^colMask;
        int cnt2 = Integer.bitCount(colMask);
        if(n%2 == 0 && cnt2 != n/2 || n%2 == 1 && cnt2 != (n+1)/2 && cnt2 != n/2) return -1;
        int colMaskCnt = 1;
        int colRevMaskCnt = 0;
        for(int i = 1; i < n; i++){
            int curMask = getMask(board, false, i);
            if(curMask == colMask) colMaskCnt++;
            else if(curMask == colRevMask) colRevMaskCnt++;
            else return -1;
        }
        //检查2种列mask的数量
        if(n%2 == 0 && colMaskCnt != n/2 || n%2 == 1 && colMaskCnt != (n+1)/2 && colMaskCnt != n/2) return -1;

        int ansRow = 0;
        if(n%2 == 0){
            int move1 = n/2-Integer.bitCount(rowMask&0xAAAAAAAA); //第一行结果是0101...
            int move2 = n/2-Integer.bitCount(rowMask&0x55555555); //第一行结果是1010...
            ansRow = Math.min(move1, move2);
        }
        else{
            //此时1比0少, 第一行结果是0101...
            if(cnt1 == n/2) ansRow = n/2-Integer.bitCount(rowMask&0xAAAAAAAA);
            //此时1比0多, 第一行结果是1010...
            else ansRow = (n+1)/2-Integer.bitCount(rowMask&0x55555555);
        }

        int ansCol = 0;
        if(n%2 == 0){
            int move1 = n/2-Integer.bitCount(colMask&0xAAAAAAAA); //第一列结果是0101...
            int move2 = n/2-Integer.bitCount(colMask&0x55555555); //第一列结果是1010...
            ansCol = Math.min(move1, move2);
        }
        else{
            //此时1比0少, 第一列结果是0101...
            if(cnt2 == n/2) ansCol = n/2-Integer.bitCount(colMask&0xAAAAAAAA);
            //此时1比0多, 第一列结果是1010...
            else ansCol = (n+1)/2-Integer.bitCount(colMask&0x55555555);
        }
        return ansRow + ansCol;
    }
    public int getMask(int[][] board, boolean isRow, int index){
        int mask = 0;
        if(isRow) {
            for (int i = 0; i < n; i++) mask = mask * 2 + board[index][i];
        }
        else{
            for (int i = 0; i < n; i++) mask = mask * 2 + board[i][index];
        }
        return mask;
    }
    public static void main(String args[])
    {
        Leetcode0782_1 test = new Leetcode0782_1();
        //int[][] board = {{0,1,1,0},{0,1,1,0},{1,0,0,1},{1,0,0,1}};
        int[][] board = {{1,1,0},{0,0,1},{0,0,1}};
        System.out.println(test.movesToChessboard(board));
    }
}
