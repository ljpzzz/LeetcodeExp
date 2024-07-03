package Leetcode0327;

import java.util.*;

public class Leetcode0327_1 {
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
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] pSum = new long[n + 1];
        Set<Long> sumSet = new HashSet<>();
        sumSet.add(0L);
        sumSet.add(-1L*lower);
        sumSet.add(-1L*upper);
        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
            sumSet.add(pSum[i + 1]);
            sumSet.add(pSum[i + 1] - lower);
            sumSet.add(pSum[i + 1] - upper);
        }
        List<Long> sumList = new ArrayList<>(sumSet);
        Collections.sort(sumList);
        Map<Long, Integer> valPosMap = new HashMap<>();
        int N = sumList.size();
        for (int i = 0; i < sumList.size(); i++) {
            valPosMap.put(sumList.get(i), i+1);
        }
        int ans = 0;
        update(root, 1, N, valPosMap.get(0L), valPosMap.get(0L), 1);
        for(int i = 1; i <= n; i++){
            int curAns = query(root, 1, N, valPosMap.get(pSum[i] - upper), valPosMap.get(pSum[i] - lower));
            ans += curAns;
            update(root, 1, N, valPosMap.get(pSum[i]), valPosMap.get(pSum[i]), 1);
        }
        return ans;
    }
    public static void main(String args[])
    {
        Leetcode0327_1 leetcode0327_1 = new Leetcode0327_1();
        int[] nums = {-2,5,-1};
        int lower = -2;
        int upper = 2;
        int ans = leetcode0327_1.countRangeSum(nums, lower, upper);
        System.out.println(ans);
    }
}
