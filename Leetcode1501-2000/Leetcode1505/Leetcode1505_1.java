package Leetcode1505;

import java.util.*;

public class Leetcode1505_1 {
    class Node {
        Node left, right;
        int val, add;
    }
    private Node root = new Node();
    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.val += (end - start + 1) * val;
            node.add += val;
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
        node.left.val += node.add * leftNum;
        node.right.val += node.add * rightNum;
        // 对区间进行「加减」的更新操作，下推懒惰标记时需要累加起来，不能直接覆盖
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }

    public String minInteger(String num, int k) {
        int n = num.length();
        List<Integer>[] g = new List[10];
        for(int i = 0; i < 10; i++) g[i] = new ArrayList<>();
        for(int i = 0; i < n; i++){
            g[num.charAt(i) - '0'].add(i+1);
        }
        String ans = "";
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 10; j++){
                if(g[j].isEmpty()) continue;
                int idx = g[j].get(0);
                int addition = query(root, 1, n, idx+1, n);
                if(addition + idx - (i+1) > k) continue;
                ans += (char)(j + '0');
                g[j].remove(0);
                k -= addition + idx - (i+1);
                update(root, 1, n, idx, idx, 1);
                break; //第i位已经找完
            }
        }
        return ans;
    }
}
