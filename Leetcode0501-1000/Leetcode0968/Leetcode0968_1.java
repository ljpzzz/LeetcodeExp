package Leetcode0968;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Leetcode0968_1 {
    //int[]三个值
    // - 当前节点安装摄像头，监控当前子树需要的最小摄像头数量
    // - 当前节点可装也可以不装，监控当前子树需要的最小摄像头数量
    // - 不考虑当前节点是否被监控，监控当前子树需要的最小摄像头数量
    Map<TreeNode, int[]> memo = new HashMap<>();
    public int minCameraCover(TreeNode root) {
        int[] ans =  dfs(root);
        return ans[1];
    }
    public int[] dfs(TreeNode cur){
        if(cur == null) return new int[]{0, 0, 0};
        if(cur.left == null && cur.right == null) {
            int[] ans = new int[]{1, 1, 0};
            memo.put(cur, ans);
            return ans;
        }
        if(memo.containsKey(cur)) return memo.get(cur);
        int[] ans = new int[3];
        int[] lAns = dfs(cur.left);
        int[] rAns = dfs(cur.right);
        //当前节点安装摄像头，子节点本身不需要考虑，只需要考虑子节点的子树
        ans[0] = lAns[2] + rAns[2] + 1;
        //当前节点可装也可以不装，装则为ans[0], 不装则左右子树至少有一个要装
        ans[1] = ans[0];
        if(cur.left != null) ans[1] = Math.min(ans[1], lAns[0] + rAns[1]);
        if(cur.right != null) ans[1] = Math.min(ans[1], rAns[0] + lAns[1]);
        //不考虑当前节点是否被监控， 则只要左右子树可以被监控即可, 此时当前节点可以装或者不管
        ans[2] = Math.min(lAns[1] + rAns[1], ans[0]);
        memo.put(cur, ans);
        return ans;
    }
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left  = new TreeNode(4);
        root.left.left.left.right  = new TreeNode(5);
        root.left.left.left.right.left  = new TreeNode(6);
        root.left.left.left.right.right  = new TreeNode(7);
        root.left.left.left.right.right.left  = new TreeNode(8);
        root.left.left.left.right.right.right  = new TreeNode(9);
        System.out.println(new Leetcode0968_1().minCameraCover(root));

    }
}
