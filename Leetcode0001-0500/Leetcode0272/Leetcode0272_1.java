package Leetcode0272;

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

public class Leetcode0272_1 {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        dfs(root);
        int n = ans.size();
        if(target >= ans.get(n-1)) return ans.subList(n-k, n);
        if(target <= ans.get(0)) return ans.subList(0, k);
        Collections.sort(ans, (a,b)->{
            double deltaA = Math.abs(a-target);
            double deltaB = Math.abs(b-target);
            if(deltaA < deltaB) return -1;
            else if(deltaA == deltaB) return 0;
            else return 1;
        });
        return ans.subList(0, k);
    }
    public void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        ans.add(root.val);
        dfs(root.right);
    }
}
