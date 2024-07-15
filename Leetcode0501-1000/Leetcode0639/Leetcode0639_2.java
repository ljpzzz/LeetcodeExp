package Leetcode0639;

public class Leetcode0639_2 {
    public int numDecodings(String s) {
        int MOD = 1000000007;
        int n = s.length();
        //处理0开头的特殊情况
        if(s.charAt(0) == '0') return 0;
        //dp[i]表示s的以第i个字符结束，对应的解码方法种数
        long[] dp = new long[n+1];
        dp[0] = 1;
        if(s.charAt(0) == '*') dp[1] = 9;
        else dp[1] = 1;
        for(int i = 1; i < n; i++){
            char cur = s.charAt(i);
            //1. 计算第i个字符自己作为单独一个码的时候的解码次数
            //当前是*,独自编码有9种可能,即1-9
            if(cur == '*') dp[i+1] += 9*dp[i];
                //当前是1-9，独自编码只有一种可能
            else if(cur != '0') dp[i+1] += 1*dp[i];
            dp[i+1] %= MOD;

            //2.计算第i个字符和第i-1个字符一起做为一个码的时候的解码次数
            char prev = s.charAt(i-1);
            //如果前面的位置是0或者3-9,是没法一起作为2位数的码的
            if(prev != '*' && prev != '1' && prev != '2') continue;
            //到此处说明前面是*或者1,2

            if(prev == '*'){
                //2个都是*,可以表示11-26(不包括20)共15种情况
                if(cur == '*') dp[i+1] += 15*dp[i-1];
                    //前面是*，当前小于等于6，则有2种情况，即10+cur，20+cur
                else if(cur <= '6') dp[i+1] += 2*dp[i-1];
                    //前面是*，当前大于6，则有1种情况, 即10+cur
                else dp[i+1] += 1*dp[i-1];
            }
            else if(prev == '1'){
                //前面是1，当前*，则有9种情况，即11-19
                if(cur == '*') dp[i+1] += 9*dp[i-1];
                    //前面是1，当前也是数字，则有1种情况, 即10+cur
                else dp[i+1] += 1*dp[i-1];
            }
            else{
                //前面是2，当前*，则有6种情况，即21-26
                if(cur == '*') dp[i+1] += 6*dp[i-1];
                    //前面是2，当前小于等于6，则有一种情况，即20+cur
                else if(cur <= '6') dp[i+1] += 1*dp[i-1];
            }
            dp[i+1] %= MOD;
            //System.out.println("len = " + (i+1) + ", dp = " + dp[i+1]);
        }
        return (int)dp[n];
    }
}
