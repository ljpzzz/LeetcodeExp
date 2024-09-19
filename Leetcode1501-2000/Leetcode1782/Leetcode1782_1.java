package Leetcode1782;

import java.util.*;

public class Leetcode1782_1 {
    class Node {
        Node left, right;
        int val, add;
    }
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
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int m = edges.length;
        int N = 0; //最大的度
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        int[] degrees = new int[n + 1];
        // key: a#b(a < b), value: edge cnt
        Map<String, Integer> pairEdgeCntMap = new HashMap<>();
        List<int[]> edgesUnique = new ArrayList<>();
        for(int[] edge: edges){
            int x = edge[0];
            int y = edge[1];
            g[x].add(y);
            g[y].add(x);
            degrees[x]++;
            degrees[y]++;
            N = Math.max(N, degrees[x]);
            N = Math.max(N, degrees[y]);
            String key = x < y ? x + "#" + y : y + "#" + x;
            pairEdgeCntMap.put(key, pairEdgeCntMap.getOrDefault(key, 0) + 1);
            if(pairEdgeCntMap.get(key) == 1) edgesUnique.add(edge);
        }
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int limit = queries[i];
            Node root = new Node(); //用线段树计算
            //先不考虑多计算的边，统计degrees[j] + degrees[k] > limit的k的数量
            for(int j = 1; j <= n; j++){
                if(limit > 2*N) ans[i] += 0;
                else if(degrees[j] > limit) ans[i] += j-1;
                else if(limit-degrees[j]+1 <= N) ans[i] +=  query(root, 1, N,limit-degrees[j]+1, N);
                if(degrees[j] > 0) update(root, 1, N, degrees[j], degrees[j], 1);
            }
            //剔除不符合要求的对数，即满足degrees[j] + degrees[k] > limit, degrees[j] + degrees[k] - edgeCnt[j, k] <= limit
            for(int[] edge : edgesUnique){
                int x = edge[0];
                int y = edge[1];
                if(x > y){
                    int tmp = x; x = y; y = tmp;
                }
                if(degrees[x] + degrees[y] > limit && degrees[x] + degrees[y] - pairEdgeCntMap.get(x + "#" + y) <= limit){
                    ans[i]--;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1,5},{1,5},{3,4},{2,5},{1,3},{5,1},{2,3},{2,5}};
        int[] queries = {3};
        Leetcode1782_1 solution = new Leetcode1782_1();
        int[] ans = solution.countPairs(n, edges, queries);
        System.out.println(Arrays.toString(ans));
    }
}
