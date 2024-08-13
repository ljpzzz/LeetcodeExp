package Leetcode1373;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Leetcode1373_1 {
    Map<TreeNode, int[]> iSBSTMap = new HashMap<>();
    Map<TreeNode, Integer> nodeSumMap = new HashMap<>();
    int MIN = -40001;
    int MAX = 40001;
    int ans = 0;
    public int maxSumBST(TreeNode root) {
        dfs1(root);
        dfs2(root);
        dfsAns(root);
        return ans;
    }
    //int[] 三个值，最小值，最大值，是否BST(1,-1)
    public int[] dfs1(TreeNode root) {
        if (root == null) return new int[]{MAX,MIN,1};
        if (iSBSTMap.get(root) != null) return iSBSTMap.get(root);
        int[] ans1 = dfs1(root.left);
        int[] ans2 = dfs1(root.right);
        if (root.val > ans1[1] && root.val < ans2[0] && ans1[2] == 1 && ans2[2] == 1) {
            int lAns = ans1[0] == MAX ? root.val : ans1[0];
            int rAns = ans2[1] == MIN ? root.val : ans2[1];
            iSBSTMap.put(root, new int[]{lAns, rAns, 1});
        }
        else iSBSTMap.put(root, new int[]{ans1[0], ans2[1], -1});
        return iSBSTMap.get(root);
    }
    public int dfs2(TreeNode root){
        if(root == null) return 0;
        if(nodeSumMap.containsKey(root)) return nodeSumMap.get(root);
        int ans = root.val + dfs2(root.left) + dfs2(root.right);
        nodeSumMap.put(root, ans);
        return ans;
    }
    public void dfsAns(TreeNode root){
        if(root == null) return;
        if(iSBSTMap.get(root)[2] == 1) ans = Math.max(ans, nodeSumMap.get(root));
        dfsAns(root.left);
        dfsAns(root.right);
    }
}