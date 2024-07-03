package Leetcode0354;

import java.util.*;

public class Leetcode0354_1 {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        //第一维度升序，第二维度降序
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        List<Integer> d = new ArrayList<>(); // 存储第二维度
        d.add(0);
        for(int i = 0; i < n; i++){
            if(d.get(d.size() - 1) < envelopes[i][1]){
                d.add(envelopes[i][1]);
                continue;
            }
            int inx = bSearch(d, envelopes[i][1]);
            d.set(inx, envelopes[i][1]);
        }
        return d.size() - 1;
    }
    public int bSearch(List<Integer> d, int target){
        int left = 0;
        int right = d.size() - 1;
        int ans = 0;
        while(left <= right){
            int mid = (left + right) / 2;
            if(d.get(mid) >= target){
                ans = mid;
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String args[]){
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        Leetcode0354_1 leetcode0354_1 = new Leetcode0354_1();
        System.out.println(leetcode0354_1.maxEnvelopes(envelopes));
    }
}
