package Leetcode1439;

import java.util.*;

public class Leetcode1439_1 {
    int BASE = 5001;
    int MOD = (int) 1e9 + 7;
    int[][] mat;
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        this.mat = mat;
        Set<Long> vis = new HashSet<>();
        PriorityQueue<Obj> pq = new PriorityQueue<>((a,b)->(a.sum-b.sum));
        int[] inxArr = new int[m];
        int sum = 0;
        for(int i = 0; i < m; i++) sum += mat[i][0];
        pq.add(new Obj(inxArr, sum));
        vis.add(0L);
        while (--k > 0) {
            Obj cur = pq.poll();
            for(int i = 0; i < m; i++){
                //已经到尽头的不用处理
                if(cur.inxArr[i] == n-1) continue;
                int[] curInxArr = cur.inxArr.clone();
                curInxArr[i]++;
                int curSum = cur.sum + mat[i][curInxArr[i]]-mat[i][curInxArr[i]-1];
                long curHash = hashVal(curInxArr);
                if(vis.contains(curHash)) continue;
                vis.add(curHash);
                pq.add(new Obj(curInxArr, curSum));
            }
        }
        return pq.peek().sum;
    }
    public long hashVal(int[] inxArr){
        long res = 0;
        for(int i = 0; i < inxArr.length; i++) res = (res * BASE + inxArr[i]) % MOD;
        return res;
    }
    class Obj{
        int[] inxArr;
        int sum;
        public Obj(int[] inxArr, int sum){
            this.inxArr = inxArr;
            this.sum = sum;
        }
    }
    public static void main(String[] args) {
        int[][] mat = {{1,3,11},{2,4,6}};
        int k = 5;
        System.out.println(new Leetcode1439_1().kthSmallest(mat, k));
    }
}
