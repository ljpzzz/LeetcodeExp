package Leetcode0440;

public class Leetcode0440_1 {
    public int findKthNumber(int n, int k) {
        int root = 1;
        k--;
        while(k > 0){
            int cnt = getCnt(root, n);
            if(cnt <= k){
                k -= cnt;
                root++;
            }else{
                k--;
                root *= 10;
            }
        }
        return root;
    }
    //以root为根的树有多少个子节点
    public int getCnt(int root, int n){
        int cnt = 0;
        long left = root;
        long right = root;
        while(left <= n){
            cnt += Math.min(n, right) - left+1;
            left *= 10;
            right = right*10+9;
        }
        return cnt;
    }
}
