package Leetcode0158;
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */
class Reader4 {
    int read4(char[] buf4){
        return 0;
    }
}

public class Leetcode0158_1 extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    String left = "";
    public int read(char[] buf, int n) {
        while(left.length() < n){
            char[] buf2 = new char[4];
            int curLen = read4(buf2);
            for(int i = 0; i < curLen; i++) left += buf2[i];
            if(curLen < 4) break;
        }
        if(left.length() >= n){
            for(int i = 0; i < n; i++) buf[i] = left.charAt(i);
            left = left.substring(n);
            return n;
        }
        else{
            int cnt = left.length();
            for(int i = 0; i < cnt; i++) buf[i] = left.charAt(i);
            left = "";
            return cnt;
        }
    }
}