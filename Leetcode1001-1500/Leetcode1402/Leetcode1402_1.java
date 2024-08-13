package Leetcode1402;

import java.util.Arrays;

public class Leetcode1402_1 {
    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.sort(satisfaction);
        if(satisfaction[n - 1] <= 0) return 0;

        int ans = satisfaction[n-1];
        int postSum = satisfaction[n-1]; //后缀和
        int postAns = satisfaction[n-1]; //上一个结果
        for(int i = n - 2; i >= 0; i--){
            int curAns = postAns + postSum + satisfaction[i];
            if(curAns > ans){
                ans = curAns;
                postSum += satisfaction[i];
                postAns = curAns;
            }
            else break;
        }
        return ans;
    }
}
