package Leetcode1601;

public class Leetcode1601_1 {
    public int maximumRequests(int n, int[][] requests) {
        int m = requests.length;
        int ans = 0;
        for(int mask = 1; mask < (1 << m); mask++){
            int[] count = new int[n];
            for(int i = 0; i < m; i++){
                if((mask & (1 << i)) != 0){
                    count[requests[i][0]]--;
                    count[requests[i][1]]++;
                }
            }
            boolean ok = true;
            for(int i = 0; i < n; i++){
                if(count[i] != 0) {
                    ok = false;
                    break;
                }
            }
            if(ok) ans = Math.max(ans, Integer.bitCount(mask));
        }
        return ans;
    }
}
