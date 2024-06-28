package Leetcode0085;

public class Leetcode0085_1 {
    //right数组+枚举
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] right1 = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = n - 1; j >= 0; j--){
                if(matrix[i][j] == '1'){
                    right1[i][j] = 1;
                    if(j < n-1) right1[i][j] += right1[i][j+1];
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '0') continue;
                int width = right1[i][j];
                for(int i1 = i; i1 < m; i1++){
                    if(matrix[i1][j] == '0') break;
                    width = Math.min(width, right1[i1][j]);
                    ans = Math.max(ans, width * (i1 - i + 1));
                }
            }
        }
        return ans;
    }
}
