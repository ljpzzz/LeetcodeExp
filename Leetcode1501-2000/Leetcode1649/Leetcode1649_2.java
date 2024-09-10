package Leetcode1649;

public class Leetcode1649_2 {
    int MOD = (int)1e9+7;
    public int createSortedArray(int[] instructions) {
        int N = 100000;
        BitTree root = new BitTree(N);
        long res = 0;
        for(int i = 0; i < instructions.length; i++){
            int cur = instructions[i];
            long leftSum = root.prefixSum(cur-1);
            long rightSum = root.prefixSum(N) - root.prefixSum(cur);
            //System.out.println(leftSum + " vs " + rightSum);
            res += Math.min(leftSum, rightSum);
            res %= MOD;
            root.update(cur);
        }
        return (int)res;
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
        public void update(int x){
            while(x <= maxN){
                treeArray[x]++;
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
}
