package Leetcode1611;

public class Leetcode1611_2 {
    public int minimumOneBitOperations(int n) {
        if(n <= 1) return n;
        int len = Integer.toBinaryString(n).length()-1;
        return (1<<len) + g(n^(1<<len), len-1);
    }
    //将二进制长度为len+1的十进制数字变成0x10..0的最小次数
    public int g(int n ,int len){
        if(len == 0) return 1-n;
        //最高位是0
        if((n&(1<<len)) == 0){
            return (1<<len) + g(n, len-1);
        }
        //最高位是1, 将len-1长度都变成0即可
        else{
            return minimumOneBitOperations(n^(1<<len));
        }
    }
}
