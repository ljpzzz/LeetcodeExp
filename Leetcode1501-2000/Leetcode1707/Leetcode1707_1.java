package Leetcode1707;

import java.util.Arrays;

public class Leetcode1707_1 {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        int m = queries.length;
        int[][] q = new int[m][3];
        for(int i = 0; i < m; i++) {
            q[i][0] = queries[i][0];
            q[i][1] = queries[i][1];
            q[i][2] = i;
        }
        Arrays.sort(q, (a, b)->a[1] - b[1]);
        DictTree root = new DictTree();
        int numInx = 0;
        int[] ans = new int[m];
        for(int i = 0; i < m; i++){
            int x = q[i][0];
            int limit = q[i][1];
            int inx = q[i][2];
            while(numInx < n && nums[numInx] <= limit){
                String s = Integer.toBinaryString(nums[numInx]);
                while(s.length() < 30) s = '0' + s;
                root.add(s);
                numInx++;
            }
            String xStr = Integer.toBinaryString(x);
            while(xStr.length() < 30) xStr = '0' + xStr;
            ans[inx] = root.search(xStr);
        }
        return ans;
    }
    public class DictTree {
        public DictTree[] children = new DictTree[2];
        public void add(String word) {
            DictTree cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.children[c - '0'] == null) cur.children[c - '0'] = new DictTree();
                cur = cur.children[c - '0'];
            }
        }
        public int search(String word) {
            DictTree cur = this;
            int xor = 0;
            for (int i = 0; i < word.length(); i++) {
                int pos = word.charAt(i) - '0';
                if(cur.children[1^pos] != null){
                    xor = (xor<<1)+1;
                    cur = cur.children[1^pos];
                }
                else if(cur.children[pos] != null){
                    xor = (xor<<1);
                    cur = cur.children[pos];
                }
                else return -1;
            }
            return xor;
        }
    }
    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4};
        int[][] queries = {{3,1},{1,3},{5,6}};
        Leetcode1707_1 leetcode1707_1 = new Leetcode1707_1();
        int[] ans = leetcode1707_1.maximizeXor(nums, queries);
        for(int tmp : ans) System.out.println(tmp + " ");
    }
}
