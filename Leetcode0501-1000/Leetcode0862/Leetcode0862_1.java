package Leetcode0862;

import java.util.*;

public class Leetcode0862_1 {
    // *************** 下面是模版 ***************
    class Node {
        Node left, right;
        int val, add;
    }
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
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        Set<Long> set = new HashSet<>();
        long[] pSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
            set.add(pSum[i + 1]);
            set.add(pSum[i + 1] - k);
        }
        List<Long> list = new ArrayList<>(set);
        Collections.sort(list);
        int N = list.size();
        Map<Long, Integer> codeMap = new HashMap<>();
        for (int i = 0; i < N; i++) codeMap.put(list.get(i), i + 1);
        int ans = 0x3f3f3f3f;
        for(int i = 0; i < n; i++){
            if(pSum[i+1] >= k) ans = Math.min(ans, i + 1);
            int code = codeMap.get(pSum[i + 1]-k);
            int j = query(root, 1, N, 1, code);
            if(j > 0) ans = Math.min(ans, i+1-j);
            update(root, 1, N, codeMap.get(pSum[i + 1]), codeMap.get(pSum[i + 1]), i+1);
        }
        return ans == 0x3f3f3f3f ? -1 : ans;
    }
    public static void main(String[] args) {
        Leetcode0862_1 test = new Leetcode0862_1();
        int[] nums = {77,19,35,10,-14};
        int k = 3;
        System.out.println(test.shortestSubarray(nums, k));
    }
}
