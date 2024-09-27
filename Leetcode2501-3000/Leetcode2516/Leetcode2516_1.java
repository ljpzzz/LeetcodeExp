package Leetcode2516;

public class Leetcode2516_1 {
    public int takeCharacters(String s, int k) {
        if(k == 0) return 0;
        int na = 0;
        int nb = 0;
        int nc = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c == 'a') na++;
            else if(c == 'b') nb++;
            else nc++;
        }
        if(na < k || nb < k || nc < k) return -1;
        int ca = na-k;
        int cb = nb-k;
        int cc = nc-k;

        int left = 0;
        int right = 0;
        int ans = 0;
        int cnta = 0;
        int cntb = 0;
        int cntc = 0;
        while(right < n){
            char c = s.charAt(right);
            if(c == 'a') cnta++;
            else if(c == 'b') cntb++;
            else cntc++;
            while(cnta > ca || cntb > cb || cntc > cc){
                ans = Math.max(ans, right - left);
                char leftC = s.charAt(left);
                if(leftC == 'a') cnta--;
                else if(leftC == 'b') cntb--;
                else cntc--;
                left++;
            }
            right++;
        }
        ans = Math.max(ans, right - left);
        return n-ans;
    }
    public static void main(String[] args) {
        Leetcode2516_1 leetcode2516_1 = new Leetcode2516_1();
        System.out.println(leetcode2516_1.takeCharacters("acbcc", 1));
    }
}
