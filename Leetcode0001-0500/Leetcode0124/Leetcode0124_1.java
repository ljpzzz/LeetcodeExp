package Leetcode0124;

import java.util.*;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

public class Leetcode0124_1 {
    Map<TreeNode, Integer> nodeMaxSumMap = new HashMap<>();
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }
    public int dfs(TreeNode root){
        if(root == null) return 0;
        if(nodeMaxSumMap.containsKey(root)) return nodeMaxSumMap.get(root);
        int leftSum = Math.max(0,dfs(root.left));
        int rightSum = Math.max(0,dfs(root.right));
        ans = Math.max(ans, leftSum + rightSum + root.val);
        return Math.max(leftSum, rightSum) + root.val;
    }
}
