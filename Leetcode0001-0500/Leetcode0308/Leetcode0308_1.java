package Leetcode0308;

public class Leetcode0308_1 {
    int m;
    int n;
    BitTree[] btList;
    int[][] matrix;
    public Leetcode0308_1(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        btList = new BitTree[m];
        for(int i = 0; i < m; i++) {
            btList[i] = new BitTree(n);
            for(int j = 0; j < n; j++) btList[i].update(j+1, matrix[i][j]);
        }
    }

    public void update(int row, int col, int val) {
        int delta = val-matrix[row][col];
        if(delta != 0) btList[row].update(col+1, delta);
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ans = 0;
        for(int i = row1; i <= row2; i++){
            ans += btList[i].prefixSum(col2+1)-btList[i].prefixSum(col1);
        }
        return ans;
    }
    //树状数组模板
    class BitTree{
        private int maxN;
        private int[] treeArray;
        public BitTree(int maxN){
            this.maxN = maxN;
            treeArray = new int[maxN+1];
        }
        public int lowBit(int x){
            return x&(-x);
        }
        public void update(int x, int val){
            while(x <= maxN){
                treeArray[x] += val;
                x += lowBit(x);
            }
        }
        public int prefixSum(int x){
            int res = 0;
            while(x >= 1){
                res += treeArray[x];
                x -= lowBit(x);
            }
            return res;
        }
    }
    public static void main(String args[]){
        Leetcode0308_1 nm = new Leetcode0308_1(new int[][]{
                {3,0,1,4,2}, {5,6,3,2,1}, {1,2,0,1,5}, {4,1,0,1,7}, {1,0,3,0,5},
        });
        System.out.println(nm.sumRegion(2,1,4,3));
        nm.update(3,2,2);
        System.out.println(nm.sumRegion(2,1,4,3));
    }
}

