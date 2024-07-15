package Leetcode0668;

public class Leetcode0668_1 {
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        int ans = -1;
        while(l <= r){
            int mid = (l + r) / 2;
            if(isOK(m, n, k, mid)){
                ans = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        return ans;
    }
    public boolean isOK(int m, int n, int k, int mid){
        int cnt = 0;
        for(int i = 1; i <= m; i++){
            cnt += Math.min(mid / i, n);
        }
        return cnt >= k;
    }
}
