package Leetcode0987;

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
public class Leetcode0987_1 {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<NodeObject>> map = new HashMap<>();
        Queue<NodeObject> queue = new ArrayDeque<>();
        queue.offer(new NodeObject(root, 0 ,0));
        map.computeIfAbsent(0, k -> new ArrayList<>()).add(new NodeObject(root, 0, 0));
        while(!queue.isEmpty()){
            NodeObject nodeObject = queue.poll();
            if(nodeObject.node.left != null){
                NodeObject lNode = new NodeObject(nodeObject.node.left, nodeObject.x + 1, nodeObject.y - 1);
                queue.offer(lNode);
                map.computeIfAbsent(nodeObject.y-1, k -> new ArrayList<>()).add(lNode);
            }
            if(nodeObject.node.right != null){
                NodeObject rNode = new NodeObject(nodeObject.node.right, nodeObject.x + 1, nodeObject.y + 1);
                queue.offer(rNode);
                map.computeIfAbsent(nodeObject.y+1, k -> new ArrayList<>()).add(rNode);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int x = -1000; x <= 1000; x++){
            if(map.containsKey(x)){
                List<NodeObject> cur = map.get(x);
                Collections.sort(cur, (a, b)->{
                    if(a.x != b.x) return a.x-b.x;
                    else return a.node.val - b.node.val;
                });
                List<Integer> curAns = new ArrayList<>();
                for(NodeObject nodeObject : cur){
                    curAns.add(nodeObject.node.val);
                }
                ans.add(curAns);
            }
        }
        return ans;
    }
    class NodeObject {
        TreeNode node;
        int x;
        int y;

        public NodeObject(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }
}
