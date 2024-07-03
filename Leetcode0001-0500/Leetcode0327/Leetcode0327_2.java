package Leetcode0327;

import java.util.*;

public class Leetcode0327_2 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        //先求前缀和数组
        long[] prefixSum = new long[n+1];
        for(int i = 0; i < n; i++) prefixSum[i+1] = prefixSum[i] + nums[i];
        //对前缀和数组离散化编码
        Set<Long> prefixSet = new HashSet<>();
        for(long pre : prefixSum) {
            prefixSet.add(pre);
            prefixSet.add(pre-lower);
            prefixSet.add(pre-upper);
        }
        int codeLen = prefixSet.size();
        long[] prefixCodeArr = new long[codeLen];
        int index = 0;
        for(long tmp : prefixSet) prefixCodeArr[index++] = tmp;
        Arrays.sort(prefixCodeArr);

        //初始化树状数组
        BitTree btree = new BitTree(codeLen+1);
        int res = 0;
        for(int i = 0; i <= n; i++){
            int leftCode = Arrays.binarySearch(prefixCodeArr, prefixSum[i]-upper)+1;
            int rightCode = Arrays.binarySearch(prefixCodeArr, prefixSum[i]-lower)+1;
            int code = Arrays.binarySearch(prefixCodeArr, prefixSum[i])+1;
            res += btree.prefixSum(rightCode) - btree.prefixSum(leftCode-1);
            btree.update(code);
        }
        return res;
    }
    //树状数组模板
    class BitTree {
        private int maxN;
        private int[] treeArray;

        public BitTree(int maxN) {
            this.maxN = maxN;
            treeArray = new int[maxN + 1];
        }

        public int lowBit(int x) {
            return x & (-x);
        }

        public void update(int x) {
            while (x <= maxN) {
                treeArray[x]++;
                x += lowBit(x);
            }
        }

        public int prefixSum(int x) {
            int res = 0;
            while (x >= 1) {
                res += treeArray[x];
                x -= lowBit(x);
            }
            return res;
        }
    }
}
