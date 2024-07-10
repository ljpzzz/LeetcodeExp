package Leetcode0493;

import java.util.*;

public class Leetcode0493_2 {
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

    public int reversePairs(int[] nums) {
        int n = nums.length;
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);
        List<Integer> numList = new ArrayList<>(numSet);
        Collections.sort(numList);
        TreeMap<Long, Integer> encode = new TreeMap<>();
        int cnt = 0;
        for (int num : numList) encode.put(num*1L, ++cnt);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            Long idx = encode.higherKey(2L*x);
            if(idx != null) {
                int pos = encode.get(idx);
                ans += query(root, 1, cnt, pos, cnt);
            }
            update(root, 1, cnt, encode.get(1L*x), encode.get(1L*x), 1);
        }
        return ans;
    }
}
