package Leetcode0887;

import java.util.Arrays;

public class Leetcode0887_3 {
    //DP二分优化
    public int superEggDrop(int k, int n) {
        // dp[i][j] 表示 i个楼层，j个鸡蛋，对应的最小操作次数
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
            dp[i][1] = i; //一个鸡蛋只能逐层扔
        }
        Arrays.fill(dp[0], 0); //楼层0不需要操作
        for(int i = 1; i <= n; i++){
            for(int j = 2; j <= k; j++){
                //在i层楼的中的第x层楼扔一个鸡蛋
                // 如果破碎，则剩下的是x-1层楼，剩下的鸡蛋是j-1个, 否则，剩下的是i-x层楼，剩下的鸡蛋是j个
                //for(int x = 1; x <= i; x++){
                //    dp[i][j] = Math.min(dp[i][j], Math.max(dp[x - 1][j - 1], dp[i - x][j]) + 1);
                //}
                int left = 1, right = i;
                while(left <= right){
                    int mid = left + (right - left) / 2;
                    if(dp[mid - 1][j - 1] > dp[i - mid][j]){
                        dp[i][j] = Math.min(dp[i][j], dp[mid - 1][j - 1] + 1);
                        right = mid-1;
                    }
                    else if(dp[mid - 1][j - 1] < dp[i - mid][j]){
                        dp[i][j] = Math.min(dp[i][j], dp[i - mid][j] + 1);
                        left = mid + 1;
                    }
                    else{
                        dp[i][j] = Math.min(dp[i][j], dp[mid - 1][j - 1] + 1);
                        break;
                    }
                }
            }
        }
        return dp[n][k];
    }
}
