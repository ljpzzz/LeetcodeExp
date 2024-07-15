package Leetcode0660;

public class Leetcode0660_1 {
    public int newInteger(int n) {
        if(n <= 8) return n;
        int left = n;
        int right = Integer.MAX_VALUE;
        int ans = 0;
        while(left <= right){
            int mid = left + (right-left)/2;
            int cnt = getDpCnt(mid);
            //System.out.println("mid:" + mid +", cnt: " + cnt);
            if(cnt == n+1) {
                ans = mid;
                right = mid-1;
            }
            else if(cnt > n+1) right = mid-1;
            else left = mid+1;
        }
        return ans;
    }
    public int getDpCnt(int num){
        String nStr = Integer.toString(num);
        int len = nStr.length();
        return getCnt(nStr, 0, true, new Integer[len]);
    }
    //dp[index]
    public int getCnt(String s, int index, boolean limit, Integer[] dp) {
        int n = s.length();
        if (index == n) return 1;
        if (!limit && dp[index]!= null) return dp[index];
        int res = 0;
        int up = limit ? (s.charAt(index) - '0') : 9;
        for (int i = 0; i <= up; i++) {
            boolean newLimit = limit && (i == up);
            if(i != 9) res += getCnt(s, index + 1, newLimit, dp);
        }
        if(!limit) dp[index] = res;
        return res;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0660_1().newInteger(9));
        System.out.println(new Leetcode0660_1().newInteger(10));
        System.out.println(new Leetcode0660_1().newInteger(21));
    }
}
