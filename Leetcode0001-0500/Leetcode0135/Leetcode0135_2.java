package Leetcode0135;

import java.util.*;

public class Leetcode0135_2 {
    //拓扑排序
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] degrees = new int[n];
        Queue<Integer> q = new ArrayDeque<>();
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            if(i > 0 && ratings[i] > ratings[i-1]) degrees[i]++;
            if(i < n-1 && ratings[i] > ratings[i+1]) degrees[i]++;
            if(degrees[i] == 0) {
                q.add(i);
                ans[i] = 1;
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(cur > 0 && ratings[cur-1] > ratings[cur]) {
                degrees[cur - 1]--;
                if(degrees[cur-1] == 0) {
                    q.add(cur - 1);
                    ans[cur-1] = ans[cur]+1;
                }
            }
            if(cur < n-1 && ratings[cur+1] > ratings[cur]) {
                degrees[cur + 1]--;
                if (degrees[cur + 1] == 0) {
                    q.add(cur + 1);
                    ans[cur + 1] = ans[cur] + 1;
                }
            }
        }
        int sum = 0;
        for(int tmp : ans) sum += tmp;
        return sum;
    }
}
