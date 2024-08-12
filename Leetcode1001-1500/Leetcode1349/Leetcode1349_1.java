package Leetcode1349;

import java.util.Arrays;

public class Leetcode1349_1 {
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        int[] mask = new int[m];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(seats[i][j] == '.'){
                    mask[i] |= 1 << j;
                }
            }
        }
        //dp[i][j]表示前i行，最后一行mask为j时的最大学生数
        int[][] dp = new int[m][1 << n];
        for(int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        int sub = mask[0];
        for(; sub >= 0; sub = (sub - 1) & mask[0]) {
            if((sub&(sub<<1)) != 0 || (sub&(sub>>1)) != 0) continue; //不能有相邻的1
            dp[0][sub] = Integer.bitCount(sub);
            if(sub == 0) break;
        }
        for(int i = 1; i < m; i++){
            int subJ = mask[i];
            for(; subJ >= 0; subJ = (subJ - 1)& mask[i]){
                if((subJ&(subJ<<1)) != 0 || (subJ&(subJ>>1)) != 0) continue; //不能有相邻的1
                int subJ2 = mask[i-1];
                for(; subJ2 >= 0; subJ2 = (subJ2 - 1) & mask[i-1]){
                    if(dp[i-1][subJ2] == -1) continue; //隐含i-1行不能有相邻1
                    if((subJ&(subJ2<<1)) != 0 || (subJ&(subJ2>>1)) != 0) continue; //2行不能有交错1
                    dp[i][subJ] = Math.max(dp[i][subJ], dp[i-1][subJ2] + Integer.bitCount(subJ));
                    if(subJ2 == 0) break;
                }
                if(subJ == 0) break;
            }
        }
        int ans = 0;
        for(int i = 0; i < (1 << n); i++){
            if(dp[m-1][i] != -1) ans = Math.max(ans, dp[m-1][i]);
        }
        return ans;
    }
    public static void main(String[] args){
        char[][] seats = {{'.','.','.'},{'.','.','.'},{'.','.','.'}};
        Leetcode1349_1 test = new Leetcode1349_1();
        System.out.println(test.maxStudents(seats));
    }
}
