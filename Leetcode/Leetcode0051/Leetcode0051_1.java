package Leetcode0051;

import java.util.*;

public class Leetcode0051_1 {
    List<List<Integer>> d = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        dfs(n, 0, new ArrayList<Integer>());
        List<List<String>> ans = new ArrayList<>();
        for(List<Integer> curD : d){
            List<String> curAns = new ArrayList<>();
            for(int col : curD){
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < n; i++){
                    if(i == col) sb.append('Q');
                    else sb.append('.');
                }
                curAns.add(sb.toString());
            }
            ans.add(curAns);
        }
        return ans;
    }
    public void dfs(int n,  int iRow, List<Integer> curD){
        if(iRow == n) d.add(new ArrayList<>(curD));
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
        List<List<String>> ans = new Leetcode0051_1().solveNQueens(4);
        for(List<String> curAns : ans){
            for(String cur : curAns){
                System.out.println(cur);
            }
            System.out.println();
        }
    }
}
