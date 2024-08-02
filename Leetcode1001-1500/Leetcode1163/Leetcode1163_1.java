package Leetcode1163;

public class Leetcode1163_1 {
    public String lastSubstring(String s) {
        int n = s.length();
        int left = 0;
        int right = 1;
        while(right < n) {
            int step = 0;
            while(right + step < n && s.charAt(left + step) == s.charAt(right + step)) step++;
            if(right + step == n || s.charAt(left + step) > s.charAt(right + step)) {
                right += step + 1;
                step = 0;
            }
            else {
                left += step + 1;
                if(left >= right) right = left + 1;
                step= 0;
            }
        }
        return s.substring(left);
    }
}
