package Leetcode1735;

import java.util.*;

public class Leetcode1735_1 {
    public int[] waysToFillArray(int[][] queries) {
        int m = 14;
        int MAX = (int)1e4+1;
        //primeCnt[i]记录数字i的质因数里，每个不同素数的数量
        List<Integer>[] primeCnt= new List[MAX];
        for(int i = 1; i < MAX; i++) {
            primeCnt[i] = new ArrayList<>();
            int val = i;
            for (int j = 2; j * j <= val; j++) {
                if (val % j == 0) {
                    int cnt = 1;
                    for (val /= j; val % j == 0; val /= j) cnt++;
                    //i有质因数j，j的指数为cnt
                    primeCnt[i].add(cnt);
                }
            }
            if (val > 1) primeCnt[i].add(1);
        }

        long[][] c = new long[MAX + m][m];
        int MOD = 1000000007;
        c[0][0] = 1;
        for (int i = 1; i < MAX + m; i++) {
            c[i][0] = 1;
            for (int j = 1; j < m; j++) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
            }
        }
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int n = queries[i][0];
            int k = queries[i][1];
            long curAns = 1;
            for(int cnt : primeCnt[k]) curAns = curAns * c[n + cnt - 1][cnt]%MOD;
            ans[i] = (int)curAns;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] queries = {{2,6},{5,1},{73,660}};
        Leetcode1735_1 leetcode1735_1 = new Leetcode1735_1();
        int[] ans = leetcode1735_1.waysToFillArray(queries);
        for(int i : ans) System.out.println(i);
    }
}
