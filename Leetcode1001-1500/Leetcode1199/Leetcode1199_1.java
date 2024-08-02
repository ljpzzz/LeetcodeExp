package Leetcode1199;

import java.util.Arrays;

public class Leetcode1199_1 {
    int[] blocks;
    int n;
    public int minBuildTime(int[] blocks, int split) {
        n = blocks.length;
        Arrays.sort(blocks);
        this.blocks = blocks;
        int left = blocks[n-1];
        int right = blocks[n-1]+(n-1)*split;
        int ans = -1;
        while(left <= right){
            int mid = left +(right-left)/2;
            if(isOK(split, mid)){
                right = mid-1;
                ans = mid;
            }
            else left = mid+1;
        }
        return ans;
    }
    public boolean isOK(int split, int mid){
        int cnt = 1;
        int lastRound = 0;
        for(int i = n-1; i >= 0; i--){
            if(cnt == 0) return false;
            int leftTime = mid-blocks[i];
            int curRound = leftTime/split;
            if(curRound-lastRound >= 10) return true;
            cnt <<= (curRound-lastRound);
            if(cnt >= i+1) return true;
            lastRound = curRound;
            cnt--;
        }
        return true;
    }
}
