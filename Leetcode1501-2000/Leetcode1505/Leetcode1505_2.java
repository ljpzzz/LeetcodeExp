package Leetcode1505;

import java.util.*;

public class Leetcode1505_2 {
    public String minInteger(String num, int k) {
        int n = num.length();
        List<Integer>[] cnt = new List[10];
        for(int i = 0; i < 10; i++) cnt[i] = new ArrayList<>();
        for(int i = 0; i < n; i++) cnt[num.charAt(i)-'0'].add(i+1);
        BitTree root = new BitTree(n);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 10; j++){
                if(cnt[j].size() == 0) continue;
                int exchangePos = cnt[j].get(0);
                int additional = root.prefixSum(n)-root.prefixSum(exchangePos);
                int total = exchangePos-1-i+additional;
                if(total <= k){
                    cnt[j].remove(0);
                    root.update(exchangePos);
                    sb.append(j);
                    k -= total;
                    break;
                }
            }
        }
        return sb.toString();
    }
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
