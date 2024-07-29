package Leetcode0960;

public class Leetcode0960_1 {
    public int minDeletionSize(String[] strs) {
        int m = strs.length;
        int n = strs[0].length();
        //dp[j]表示最后留下的以第j列结束，前面所需要删除的最小列数
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) dp[i] = i; //默认前面的都要删除
        for(int j = 1; j < n; j++){
            for(int i = 0; i < j; i++){
                boolean flag = true;
                for(int k = 0; k < m; k++){
                    if(strs[k].charAt(i) > strs[k].charAt(j)){
                        flag = false;
                        break;
                    }
                }
                if(flag) dp[j] = Math.min(dp[j], dp[i] + j-i-1);
            }
        }
        int ans = n-1;
        for(int j = 0; j < n; j++) ans = Math.min(ans, dp[j] + n-j-1);
        return ans;
    }
    public static void main(String[] args){
        String[] strs = {"babca","bbazb"};
        Leetcode0960_1 l = new Leetcode0960_1();
        System.out.println(l.minDeletionSize(strs));
    }
}
