package Leetcode1622;

public class Leetcode1622_1 {
    int MOD = (int)1e9+7;
    // *************** 下面是模版 ***************
    class Node {
        Node left, right;
        long mul = 1, add = 0;
    }
    private int N = (int) 1e5;
    int[] nums = new int[N+1];
    int n = 1;
    private Node root = new Node();
    public void updateAdd(Node node, int start, int end, int l, int r, int val) {
        if(l > r) return;
        if (l <= start && end <= r) {
            node.add = (node.add + val)%MOD;
            return;
        }
        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) updateAdd(node.left, start, mid, l, r, val);
        if (r > mid) updateAdd(node.right, mid + 1, end, l, r, val);
        //pushUp(node);
    }
    public void updateMul(Node node, int start, int end, int l, int r, int val) {
        if(l > r) return;
        if (l <= start && end <= r) {
            node.mul = (node.mul * val)%MOD;
            node.add = (node.add * val)%MOD;
            return ;
        }
        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) updateMul(node.left, start, mid, l, r, val);
        if (r > mid) updateMul(node.right, mid + 1, end, l, r, val);
        //pushUp(node);
    }
    public Node query(Node node, int start, int end, int inx) {
        if(start == end) return node;
        int mid = (start + end) >> 1, ans = 0;
        pushDown(node, mid - start + 1, end - mid);
        if (inx <= mid) return query(node.left, start, mid, inx);
        else return query(node.right, mid + 1, end, inx);
    }
    private void pushDown(Node node, int leftNum, int rightNum) {
        if (node.left == null) {
            node.left = new Node();
            node.left.mul = 1;
        }
        if (node.right == null) {
            node.right = new Node();
            node.right.mul = 1;
        }
        node.left.mul = node.mul * node.left.mul % MOD;
        node.right.mul  = node.mul * node.right.mul % MOD;
        node.left.add = node.mul * node.left.add % MOD;
        node.right.add = node.mul * node.right.add % MOD;
        node.mul = 1;

        node.left.add = (node.add + node.left.add)%MOD;
        node.right.add = (node.add + node.right.add)%MOD;
        node.add = 0;
    }
    public Leetcode1622_1() {

    }

    public void append(int val) {
        nums[n++] = val;
    }

    public void addAll(int inc) {
        updateAdd(root, 1, N, 1, n-1, inc);
    }

    public void multAll(int m) {
        updateMul(root, 1, N, 1, n-1, m);
    }

    public int getIndex(int idx) {
        if(idx >= n-1) return -1;
        Node cur = query(root, 1, N, idx+1);
        long curAns = (nums[idx+1] * cur.mul%MOD + cur.add)%MOD;
        return (int)curAns;
    }
}
