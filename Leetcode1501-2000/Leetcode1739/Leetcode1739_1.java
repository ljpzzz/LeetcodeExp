package Leetcode1739;

public class Leetcode1739_1 {
    public int minimumBoxes(int n) {
        //最下一层的个数 ai = ai-1 + i  , ai = i(i+1)/2
        //所有层的个数 bi = bi-1 + ai  , bi = i*(i+1)*(i+2)/6
        int left = 1;
        int right = 2000;
        int len = right;
        //寻找最下一层一条的长度, 让bi满足小于等于n的最大值
        while(left <= right){
            int mid = left + (right - left) / 2;
            long curCnt = (long)mid * (mid + 1) * (mid + 2) / 6;
            if(curCnt <= n) {
                len = mid;
                left= mid + 1;
            }
            else right = mid - 1;
        }
        int ak = len*(len+1)/2;  //a[k]
        long bk = (long) len *(len+1)*(len+2)/6; //b[k], b[k] <= n
        int delta = n-(int)bk;
        int ans = ak;
        //x*(x+1)/2 <= delta
        if(delta > 0){
            int x = (int)Math.ceil((Math.sqrt(1+8*delta)-1)/2);
            ans += x;
        }
        return ans;
    }
    public static void main(String[] args) {
        Leetcode1739_1 l = new Leetcode1739_1();
        //System.out.println(l.minimumBoxes(3));
        //System.out.println(l.minimumBoxes(4));
        System.out.println(l.minimumBoxes(100));
    }
}
