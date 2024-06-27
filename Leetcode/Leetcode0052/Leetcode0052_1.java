package Leetcode0052;

import Leetcode0051.Leetcode0051_1;

import java.util.ArrayList;
import java.util.List;

public class Leetcode0052_1 {
    int ans = 0;
    public int totalNQueens(int n) {
        dfs(n, 0, new ArrayList<Integer>());
        return ans;
    }
    public void dfs(int n,  int iRow, List<Integer> curD){
        if(iRow == n) ans++;
        for(int iCol = 0; iCol < n; iCol++){
            boolean isOK = true;
            for(int jRow = 0; jRow < curD.size(); jRow++){
                int jCol = curD.get(jRow);
                if(iCol == jCol || iCol - iRow == jCol - jRow || iCol + iRow == jRow + jCol) {
                    isOK = false;
                    break;
                }
            }
            if(isOK){
                curD.add(iCol);
                dfs(n, iRow + 1, curD);
                curD.remove(curD.size() - 1);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0052_1().totalNQueens(4));
    }
}
