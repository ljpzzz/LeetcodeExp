package Leetcode1665;

import java.util.*;

public class Leetcode1665_1 {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b)->((b[1]-b[0])-(a[1]-a[0])));
        int n = tasks.length;
        int ans = tasks[0][1];
        int remain = tasks[0][1] - tasks[0][0];
        for(int i = 1; i < n; i++){
            if(remain >= tasks[i][1]){
                remain -= tasks[i][0];
            }else{
                ans += tasks[i][1] - remain;
                remain = tasks[i][1] - tasks[i][0];
            }
        }
        return ans;
    }
}
