package Leetcode0715;

public class Leetcode0715_1 {
    class Node {
        Node left, right;
        int val, add;
    }
    private int N = (int) 1e9;
    private Node root = new Node();
    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.val = val == 1 ? (end - start + 1) * val: 0;
            node.add = val == 1?1:-1;
            return ;
        }
        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) update(node.left, start, mid, l, r, val);
        if (r > mid) update(node.right, mid + 1, end, l, r, val);
        pushUp(node);
    }
    public int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) return node.val;
        int mid = (start + end) >> 1, ans = 0;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) ans += query(node.left, start, mid, l, r);
        if (r > mid) ans += query(node.right, mid + 1, end, l, r);
        return ans;
    }
    private void pushUp(Node node) {
        node.val = node.left.val + node.right.val;
    }
    private void pushDown(Node node, int leftNum, int rightNum) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return ;
        node.left.val = node.add == 1 ? leftNum : 0;
        node.right.val = node.add == 1 ? rightNum : 0;
        // 对区间进行「加减」的更新操作，下推懒惰标记时需要累加起来，不能直接覆盖
        node.left.add = node.add;
        node.right.add = node.add;
        node.add = 0;
    }

    public Leetcode0715_1() {

    }

    public void addRange(int left, int right) {
        update(root, 1, N, left, right-1, 1);
    }

    public boolean queryRange(int left, int right) {
        int ans = query(root, 1, N, left, right-1);
        return ans == right - left;
    }

    public void removeRange(int left, int right) {
        update(root, 1, N, left, right-1, 0);
    }
    public static void main(String[] args) {
        Leetcode0715_1 obj = new Leetcode0715_1();
        obj.addRange(10, 20);
        obj.removeRange(14, 16);
        System.out.println(obj.queryRange(10, 14));
        System.out.println(obj.queryRange(13, 15));
        System.out.println(obj.queryRange(16, 17));

    }
}
