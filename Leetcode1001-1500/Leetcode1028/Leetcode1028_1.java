package Leetcode1028;

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

public class Leetcode1028_1 {
    public TreeNode recoverFromPreorder(String traversal) {
        List<int[]> list = new ArrayList<>();
        int val = 0;
        int cnt = 0;
        for (int i = 0; i < traversal.length(); i++) {
            char c = traversal.charAt(i);
            if(traversal.charAt(i) != '-') val = val*10 + c - '0';
            else {
                if (traversal.charAt(i - 1) == '-') cnt++;
                else{
                    list.add(new int[]{val, cnt});
                    val = 0;
                    cnt = 1;
                }
            }
        }
        list.add(new int[]{val, cnt});
        return dfs(list, 0);
    }
    public TreeNode dfs(List<int[]> list, int expectDepth){
        if(list.size() == 0) return null;
        if(expectDepth != list.get(0)[1]) return null;
        int[] arr = list.remove(0);
        TreeNode cur = new TreeNode(arr[0]);
        cur.left = dfs(list, expectDepth + 1);
        cur.right = dfs(list, expectDepth + 1);
        return cur;
    }
    public static void main(String[] args){
        Leetcode1028_1 leetcode1028_1 = new Leetcode1028_1();
        TreeNode root = leetcode1028_1.recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println(root);
    }
}
