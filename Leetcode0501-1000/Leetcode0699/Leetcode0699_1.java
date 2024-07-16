package Leetcode0699;

import java.util.*;

public class Leetcode0699_1 {
    // *************** 下面是模版 ***************
    class Node {
        Node left, right;
        int val, add;
    }
    private int N = (int) 1e9;
    private Node root = new Node();
    //在[start, end]范围，更新[l,r]区间的值为val,惰性覆盖更新
    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.val = val; // 不需要累加
            node.add = val; // 不需要累加
            return ;
        }
        pushDown(node);
        int mid = (start + end) >> 1;
        if (l <= mid) update(node.left, start, mid, l, r, val);
        if (r > mid) update(node.right, mid + 1, end, l, r, val);
        pushUp(node);
    }
    //在[start, end]范围，查询[l,r]区间的值的数量
    public int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) return node.val;
        pushDown(node);
        int mid = (start + end) >> 1, ans = 0;
        if (l <= mid) ans = query(node.left, start, mid, l, r);
        if (r > mid) ans = Math.max(ans, query(node.right, mid + 1, end, l, r));
        return ans;
    }
    private void pushUp(Node node) {
        node.val = Math.max(node.left.val, node.right.val);
    }
    private void pushDown(Node node) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return ;
        node.left.val = node.add;  // 不需要累加
        node.right.val = node.add; // 不需要累加
        node.left.add = node.add;  // 不需要累加
        node.right.add = node.add; // 不需要累加
        node.add = 0;
    }
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        for(int[] p : positions){
            int left = p[0];
            int right = p[0] + p[1];
            int height = p[1];
            int preH = query(root, 1, N, left, right - 1);
            update(root, 1, N, left, right - 1, height + preH);
            ans.add(query(root, 1, N, 1, N));
        }
        return ans;
    }
    public static void main(String[] args){
        System.out.println(new Leetcode0699_1().fallingSquares(new int[][]{{100,100},{200,100}}));
    }
}
