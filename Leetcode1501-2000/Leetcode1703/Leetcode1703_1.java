package Leetcode1703;

import java.util.*;

public class Leetcode1703_1 {
    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        int cur = 0;
        while(cur < n && nums[cur] == 0) cur++;
        List<Integer> zeroList = new ArrayList<>();//1之间连续0的长度
        while(cur < n) {
            int next = cur+1;
            while(next < n && nums[next] == 0) next++;
            if(next < n) zeroList.add(next - cur-1);
            cur = next;
        }
        int m = zeroList.size();
        int[] pSum = new int[m+1];
        for(int i = 1; i <= m; i++) pSum[i] = pSum[i-1] + zeroList.get(i-1);
        int curAns = 0;
        //k个1，中间有k-1组的0,首先处理第一组
        for(int i = 0; i < k-1; i++) curAns += zeroList.get(i)*Math.min(i+1, k-1-i);
        int ans = curAns;
        //滑动窗口
        int left = 1;
        int right = k-1;
        for(; right < m; left++, right++){
            int mid = (left+right)/2;
            //[left-1, mid-1]的要减掉
            curAns -= pSum[mid] - pSum[left-1];
            //k为偶数，则[mid, right]要加回来
            if(k%2 == 0) curAns += pSum[right+1] - pSum[mid];
            //k为奇数，则[mid+1, right]要加回来
            else curAns += pSum[right+1] - pSum[mid+1];
            ans = Math.min(ans, curAns);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {1,0,0,0,0,0,1,1};
        int k = 3;
        Leetcode1703_1 leetcode1703_1 = new Leetcode1703_1();
        System.out.println(leetcode1703_1.minMoves(nums, k));
    }
}
