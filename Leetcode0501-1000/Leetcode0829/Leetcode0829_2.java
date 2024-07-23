package Leetcode0829;

public class Leetcode0829_2 {
    public int consecutiveNumbersSum(int n) {
        //先计算最大的可能组数，从1一开始,1+2...+k = n, k^2+k-2n=0
        //k = (sqrt(1+8n)-1)/2
        int maxK = (int)((Math.sqrt(8L*n+1)-1)/2);
        int total = 1; //默认n自己独立成一组
        //假设连续的i项可以凑成n,即 a+a+1+...+a+i-1 = n
        // a = (2*n/i-i+1)/2;
        for(int i = 2; i <= maxK; i++){
            int a = (int)((2*n/i-i+1)/2);
            if((2*a+i-1)*i == 2*n){
                total++;
                //System.out.println("since " + a + ", with " + i + " continuous items are OK");
            }
        }
        return total;
    }
}
