package Leetcode1157;

public class Leetcode1157_1 {

    // *************** 线段树模版 ***************
    //动态开点，不做惰性更新
    class Node {
        public Node left, right;
        public int l, r; //左右区间
        public int candidate; //摩尔投票候选
        public int count; //摩尔投票没有投出去的票数
        public Node(int l, int r){
            this.l = l;
            this.r = r;
            this.left = null;
            this.right = null;
        }
        public void set(int candidate, int count){
            this.candidate = candidate;
            this.count = count;
        }
    }
    private int N = (int) 1e5;
    private Node root;
    private int[] nums;
    //基于输入数组构建线段树
    public Node build(int l, int r){
        if(l > r) return null;
        Node res = new Node(l, r);
        if(l == r){
            res.set(nums[l], 1);
            return res;
        }
        int mid = l + (r-l)/2;
        res.left = build(l, mid);
        res.right = build(mid+1, r);
        merge(res); //更新当前根节点的摩尔投票结果
        return res;
    }
    public void merge(Node root){
        Node left = root.left;
        Node right = root.right;
        if(left == null && right == null) return;
        if(left != null) root.set(left.candidate, left.count);
        if(right != null){
            if(root.candidate == right.candidate) root.count += right.count;
            else if(root.count > right.count) root.count -= right.count;
            else root.set(right.candidate, right.count-root.count);
        }
    }
    public Node queryTree(Node root, int l, int r){
        if(l > r || root == null) return null;
        if(root.l == l && root.r == r) return root;
        int mid = root.l + (root.r-root.l)/2;
        if(mid >= r) return queryTree(root.left, l, r);
        if(mid < l) return queryTree(root.right, l, r);

        Node leftNode = queryTree(root.left, l, mid);
        Node rightNode = queryTree(root.right, mid+1, r);
        Node res = new Node(l, r);
        res.left = leftNode;
        res.right = rightNode;
        merge(res);
        return res;
    }

    public Leetcode1157_1(int[] arr) {
        nums = arr;
        root = build(0, arr.length-1);
    }

    public int query(int l, int r, int threshold) {
        Node res = queryTree(root, l, r);
        if(res == null) return -1;
        int count = 0;
        for(int i = l; i <= r; i++){
            if(nums[i] == res.candidate) count++;
        }
        if(count >= threshold) return res.candidate;
        else return -1;
    }
}
