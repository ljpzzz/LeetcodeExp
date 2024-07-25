package Leetcode0906;

public class Leetcode0906_1 {
    public int superpalindromesInRange(String left, String right) {
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        int cnt = 0;
        //构建奇数回文
        for(int i = 1; i < 100000; i++){
            StringBuilder sb = new StringBuilder(String.valueOf(i));
            for(int j = sb.length()-2; j >= 0; j--) sb.append(sb.charAt(j));
            long val = Long.parseLong(sb.toString());
            val *= val;
            if(val > r) break;
            if(val >= l && isHuiwen(val)) cnt++;
        }
        //构建偶数回文
        for(int i = 1; i < 100000; i++){
            StringBuilder sb = new StringBuilder(String.valueOf(i));
            for(int j = sb.length()-1; j >= 0; j--) sb.append(sb.charAt(j));
            long val = Long.parseLong(sb.toString());
            val *= val;
            if(val > r) break;
            if(val >= l && isHuiwen(val)) cnt++;
        }
        return cnt;
    }
    public long reverse(long x) {
        long ans = 0;
        while (x > 0) {
            ans = 10 * ans + x % 10;
            x /= 10;
        }
        return ans;
    }
    public boolean isHuiwen(long x){
        return x == reverse(x);
    }
}
